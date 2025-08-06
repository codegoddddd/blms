package com.blms.controller;

import com.blms.service.LoanProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoanProductViewController {

    private final LoanProductService service;

    public LoanProductViewController(LoanProductService service) {
        this.service = service;
    }

    @GetMapping("/loan-products/view")
    public String showLoanProductPage(Model model) {
        model.addAttribute("products", service.getAllLoanProducts());
        return "loan-products"; // This should match your HTML file name
    }
}
