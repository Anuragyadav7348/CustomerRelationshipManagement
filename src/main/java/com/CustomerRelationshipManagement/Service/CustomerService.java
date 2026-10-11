package com.CustomerRelationshipManagement.service;

import com.CustomerRelationshipManagement.Customer;
import com.CustomerRelationshipManagement.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Constructor Injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Create Customer
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get All Customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get Customer By ID
    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    // Update Customer
    public Customer updateCustomer(int id, Customer customer) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setMobileNumber(customer.getMobileNumber());
        existingCustomer.setAge(customer.getAge());

        return customerRepository.save(existingCustomer);
    }

    // Delete Customer
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}