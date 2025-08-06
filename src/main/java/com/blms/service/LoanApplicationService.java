package com.blms.service;

import com.blms.dto.LoanDetailsDTO;
import com.blms.dto.CustomerLoanSummaryDTO;
import com.blms.model.LoanApplication;
import com.blms.model.Customer;
import com.blms.repository.LoanApplicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LoanApplicationService {

    private final LoanApplicationRepository repository;

    public LoanApplicationService(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    public LoanApplication applyForLoan(LoanApplication application) {
        application.setApplicationDate(LocalDate.now());
        application.setApprovalStatus(LoanApplication.ApprovalStatus.PENDING);
        return repository.save(application);
    }

    public LoanApplication getApplicationStatus(int id) {
        return repository.findById(id).orElse(null);
    }

    public LoanApplication processLoanApplication(int id, LoanApplication.ApprovalStatus status) {
        LoanApplication application = repository.findById(id).orElse(null);
        if (application != null) {
            application.setApprovalStatus(status);
            return repository.save(application);
        }
        return null;
    }

    public List<LoanApplication> getAllApplications() {
        return repository.findAll();
    }

    // Add this method to your LoanApplicationService class
    public LoanApplication saveApplication(LoanApplication application) {
        return repository.save(application);
    }

//    public List<LoanDetailsDTO> getAllLoanDetails() {
//        List<LoanApplication> applications = repository.findAll();
//        return applications.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    // Helper method to perform the mapping
//    private LoanDetailsDTO convertToDto(LoanApplication application) {
//        return new LoanDetailsDTO(
//                application.getApplicationId(),
//                application.getCustomer().getCustomerId(),
//                application.getCustomer().getName(),
//                application.getCustomer().getAddress(),
//                application.getLoanProduct().getProductName(),
//                application.getLoanAmount()
//        );
//    }

    public List<CustomerLoanSummaryDTO> getCustomerLoanSummaries() {
        List<LoanApplication> applications = repository.findAll();

        // Step 1: Group by customerId and calculate the total loan amount
        Map<Integer, Double> totalLoansByCustomerId = applications.stream()
                .collect(Collectors.groupingBy(
                        app -> app.getCustomer().getCustomerId(),
                        Collectors.summingDouble(LoanApplication::getLoanAmount)
                ));

        // Step 2: Get a map of customerId to the Customer object for details
        Map<Integer, Customer> customerDetails = applications.stream()
                .collect(Collectors.toMap(
                        app -> app.getCustomer().getCustomerId(),
                        LoanApplication::getCustomer,
                        (existing, replacement) -> existing // Keep the first customer object found
                ));

        // Step 3: Combine the two maps into a list of DTOs
        return totalLoansByCustomerId.entrySet().stream()
                .map(entry -> {
                    Integer customerId = entry.getKey();
                    Double totalLoanAmount = entry.getValue();
                    Customer customer = customerDetails.get(customerId);

                    return new CustomerLoanSummaryDTO(
                            customerId,
                            customer.getName(),
                            customer.getAddress(),
                            totalLoanAmount
                    );
                })
                .collect(Collectors.toList());
    }
}
