package com.blms.controller;


import com.blms.model.Customer;
import com.blms.KYCStatus;
import com.blms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("default",true);
        model.addAttribute("updateForm",false);
        model.addAttribute("isRegistered",false);
        return "home";
    }

    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute Customer customer,Model model) {
        customer.setKycStatus(KYCStatus.PENDING);
        Customer c1=customerService.registerCustomer(customer);
        model.addAttribute("message","Customer registered Successfully.");
        model.addAttribute("updateForm",false);
        model.addAttribute("default",true);
        model.addAttribute("isRegistered",true);
        model.addAttribute("customerId",c1.getCustomerId());
        return "home.html";
    }

    @GetMapping("/customerDetails/{customerId}")
    public String  getCustomerDetails(@PathVariable int customerId,Model model) {
        Customer customer=customerService.getCustomerDetails(customerId);
        model.addAttribute("updateForm",false);
        model.addAttribute("default",false);
        model.addAttribute("isRegistered",true);
        model.addAttribute("customer",customer);
        return "home.html";
    }

    @GetMapping("/updateProfile/{customerId}")
    public String updateProfile(@PathVariable int customerId,Model model)
    {
        model.addAttribute("updateForm",true);
        model.addAttribute("default",false);
        model.addAttribute("isRegistered",true);
        model.addAttribute("customer",customerService.getCustomerDetails(customerId));
        return "home.html";
    }

    @PostMapping("/updateCustomer")
    public String updateCustomerDetails(@ModelAttribute Customer customer, Model model) {

        Customer c1=customerService.updateCustomerDetails(customer);

        model.addAttribute("updateForm",false);
        model.addAttribute("default",true);
        model.addAttribute("isRegistered",true);
        model.addAttribute("customerId",c1.getCustomerId());
        model.addAttribute("message","Customer details updated Successfully.");
        return "home.html";
    }

    @GetMapping("/logout")
    public String logout(Model model)
    {
        model.addAttribute("isRegistered",false);
        model.addAttribute("default",true);
        return "home.html";
    }

}

