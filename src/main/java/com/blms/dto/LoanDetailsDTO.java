// src/main/java/com/blms/dto/LoanDetailsDTO.java
package com.blms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // This provides getters, setters, toString, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
public class LoanDetailsDTO {
    private int applicationId;
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String loanProductName;
    private double loanAmount;
}