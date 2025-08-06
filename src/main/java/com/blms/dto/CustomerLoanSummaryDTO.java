// src/main/java/com/blms/dto/CustomerLoanSummaryDTO.java
package com.blms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoanSummaryDTO {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private double totalLoanAmount;
}