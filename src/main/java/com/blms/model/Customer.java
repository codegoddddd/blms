package com.blms.model;

import com.blms.KYCStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
@NoArgsConstructor
public class Customer
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String name;
    private String email;
    private String phone;
    private String address;
    @Enumerated(EnumType.STRING)
    private KYCStatus kycStatus;
}



