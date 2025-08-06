package com.blms.service;

import com.blms.model.LoanProduct;
import com.blms.repository.LoanProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanProductService {

    private final LoanProductRepository repository;

    public LoanProductService(LoanProductRepository repository) {
        this.repository = repository;
    }

    public LoanProduct addLoanProduct(LoanProduct product) {
        return repository.save(product);
    }

    public LoanProduct updateLoanProduct(LoanProduct product) {
        return repository.save(product);
    }

    public List<LoanProduct> getAllLoanProducts() {
        return repository.findAll();
    }

    public LoanProduct getLoanProductById(int id) {
        return repository.findById(id).orElse(null);
    }
}
