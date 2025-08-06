package com.blms.service;

import com.blms.model.Customer;
import com.blms.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(Customer customer) {
       return customerRepository.save(customer);
    }

    public Customer getCustomerDetails(int customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    public Customer updateCustomerDetails(Customer customer) {
        return customerRepository.save(customer);
    }
}
