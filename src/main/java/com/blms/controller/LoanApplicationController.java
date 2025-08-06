package com.blms.controller;

import com.blms.model.LoanApplication;
import com.blms.service.LoanApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-applications")
public class LoanApplicationController {

    private final LoanApplicationService service;

    public LoanApplicationController(LoanApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public LoanApplication applyForLoan(@RequestBody LoanApplication application) {
        return service.applyForLoan(application);
    }

    @GetMapping("/{id}")
    public LoanApplication getApplicationStatus(@PathVariable int id) {
        return service.getApplicationStatus(id);
    }

    @PutMapping("/{id}/status")
    public LoanApplication processLoanApplication(@PathVariable int id,
                                                  @RequestParam LoanApplication.ApprovalStatus status) {
        return service.processLoanApplication(id, status);
    }

    @GetMapping
    public List<LoanApplication> getAllApplications() {
        return service.getAllApplications();
    }
}
