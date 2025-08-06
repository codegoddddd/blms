package com.blms.controller;

import com.blms.model.LoanProduct;
import com.blms.service.LoanProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan-products")
public class LoanProductController {

    private final LoanProductService service;

    public LoanProductController(LoanProductService service) {
        this.service = service;
    }

    @PostMapping
    public LoanProduct addLoanProduct(@RequestBody LoanProduct product) {
        return service.addLoanProduct(product);
    }

    @PutMapping("/{id}")
    public LoanProduct updateLoanProduct(@PathVariable int id, @RequestBody LoanProduct product) {
        product.setLoanProductId(id);
        return service.updateLoanProduct(product);
    }

    @GetMapping
    public List<LoanProduct> getAllLoanProducts() {
        return service.getAllLoanProducts();
    }

    @GetMapping("/{id}")
    public LoanProduct getLoanProductById(@PathVariable int id) {
        return service.getLoanProductById(id);
    }
}
