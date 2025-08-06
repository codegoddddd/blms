package com.blms.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class LoanProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanProductId;

    private String productName;
    private double interestRate;
    private double minAmount;
    private double maxAmount;
    private int tenure; // in months
}
