package com.blms.controller;// src/main/java/com/blms/controller/LoanApplicationRestController.java

import com.blms.dto.CustomerLoanSummaryDTO;
import com.blms.dto.LoanDetailsDTO;
import com.blms.service.LoanApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/loan-summaries") // The endpoint your JavaScript will call
public class LoanApplicationRestController {

    private final LoanApplicationService service;

    public LoanApplicationRestController(LoanApplicationService service) {
        this.service = service;
    }

//    @GetMapping
//    public List<LoanDetailsDTO> getLoanDetails() {
//        return service.getAllLoanDetails();
//    }

    @GetMapping
    public List<CustomerLoanSummaryDTO> getLoanSummaries() {
        return service.getCustomerLoanSummaries();
    }
}