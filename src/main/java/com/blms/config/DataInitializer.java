package com.blms.config;

import com.blms.model.Customer;
import com.blms.model.LoanProduct;
import com.blms.model.LoanApplication;
import com.blms.model.LoanApplication.ApprovalStatus;
import com.blms.KYCStatus;
import com.blms.repository.CustomerRepository;
import com.blms.repository.LoanApplicationRepository;
import com.blms.repository.LoanProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(CustomerRepository customerRepo,
                                      LoanProductRepository loanProductRepo,
                                      LoanApplicationRepository loanApplicationRepo) {
        return args -> {
            // 1. Pre-populate Customer data
            Customer alice = customerRepo.save(new Customer(0, "Alice", "alice@example.com", "1234567890", "123 Main St", KYCStatus.VERIFIED));
            Customer bob = customerRepo.save(new Customer(0, "Bob", "bob@example.com", "9876543210", "456 Elm St", KYCStatus.PENDING));

            // 2. Pre-populate LoanProduct data
            LoanProduct personalLoan = loanProductRepo.save(new LoanProduct(0, "Personal Loan", 8.5, 5000.0, 50000.0, 36));
            LoanProduct homeLoan = loanProductRepo.save(new LoanProduct(0, "Home Loan", 7.0, 100000.0, 500000.0, 240));
            LoanProduct carLoan = loanProductRepo.save(new LoanProduct(0, "Car Loan", 9.2, 10000.0, 80000.0, 60));

            // 3. Pre-populate LoanApplication data
            // These variables are accessible here because they are in the same scope
            loanApplicationRepo.save(new LoanApplication(0, alice, personalLoan, 25000.0, LocalDate.now(), ApprovalStatus.PENDING));
            loanApplicationRepo.save(new LoanApplication(0, bob, carLoan, 15000.0, LocalDate.now().minusDays(5), ApprovalStatus.PENDING));
            loanApplicationRepo.save(new LoanApplication(0, alice, homeLoan, 250000.0, LocalDate.now().minusMonths(2), ApprovalStatus.APPROVED));
            loanApplicationRepo.save(new LoanApplication(0, bob, personalLoan, 60000.0, LocalDate.now().minusWeeks(3), ApprovalStatus.REJECTED));
        };
    }
}