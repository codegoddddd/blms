package com.blms.controller;

import com.blms.model.*;
import com.blms.service.CustomerService;
import com.blms.service.LoanApplicationService;
import com.blms.service.LoanProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.time.LocalDate;

@Controller
@RequestMapping("/loan-applications")
public class LoanApplicationViewController {
    private final LoanApplicationService service;
    private final CustomerService customerService;
    private final LoanProductService loanProductService;

    public LoanApplicationViewController(LoanApplicationService service, CustomerService customerService, LoanProductService loanProductService) {
        this.service = service;
        this.customerService = customerService;
        this.loanProductService = loanProductService;
    }

    @GetMapping("/view")
    public String showLoanApplicationPage(Model model) {
        model.addAttribute("applications", service.getAllApplications());
        return "loan-applications"; // This should match your HTML file name
    }

    // This is the missing piece that handles your form submission
    @PostMapping // Handles POST requests to /loan-applications
    public String submitApplication(@ModelAttribute LoanApplication loanApplication) {

        // --- This is the corrected code ---
        Customer customer = customerService.getCustomerDetails(loanApplication.getCustomer().getCustomerId());
        LoanProduct loanProduct = loanProductService.getLoanProductById(loanApplication.getLoanProduct().getLoanProductId());

        // Check if both the customer and the loan product exist
        if (customer != null && loanProduct != null) {
            loanApplication.setCustomer(customer);
            loanApplication.setLoanProduct(loanProduct);
            loanApplication.setApplicationDate(LocalDate.now());
            loanApplication.setApprovalStatus(LoanApplication.ApprovalStatus.PENDING);

            service.saveApplication(loanApplication);
        } else {
            // Handle error case (e.g., show an error message on the page)
            System.err.println("Error: Customer or Loan Product not found.");
        }

        return "redirect:/loan-applications/view"; // Redirect to the view page
    }
}
