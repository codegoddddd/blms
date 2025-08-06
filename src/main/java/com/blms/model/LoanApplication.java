package com.blms.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "loanProductId")
    private LoanProduct loanProduct;

    private double loanAmount;
    private LocalDate applicationDate;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    public enum ApprovalStatus {
        PENDING, APPROVED, REJECTED
    }

    public LoanApplication() {
        this.customer = new Customer();
        this.loanProduct = new LoanProduct();
    }
}
