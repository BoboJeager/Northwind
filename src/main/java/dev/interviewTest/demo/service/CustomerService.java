package dev.interviewTest.demo.service;

import dev.interviewTest.demo.model.Customer;
import dev.interviewTest.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){return  customerRepository.findAll();}

    public  Optional<Customer> getCustomerById(Long id){return  customerRepository.findById(id);}

    public  Customer createCustomer(Customer newCustomer) {return  customerRepository.save(newCustomer);}

    public  Customer updateCustomer(Long id, Customer customerDetails){
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        if (customerDetails.getCompanyname() != null) {
            existingCustomer.setCompanyname(customerDetails.getCompanyname());
        }
        if (customerDetails.getContactname() != null) {
            existingCustomer.setContactname(customerDetails.getContactname());
        }
        if (customerDetails.getContactTitle() != null) {
            existingCustomer.setContactTitle(customerDetails.getContactTitle());
        }
        if (customerDetails.getAddress() != null) {
            existingCustomer.setAddress(customerDetails.getAddress());
        }
        if (customerDetails.getCity() != null) {
            existingCustomer.setCity(customerDetails.getCity());
        }
        if (customerDetails.getRegion() != null) {
            existingCustomer.setRegion(customerDetails.getRegion());
        }
        if (customerDetails.getPostalCode() != null) {
            existingCustomer.setPostalCode(customerDetails.getPostalCode());
        }
        if (customerDetails.getCountry() != null) {
            existingCustomer.setCountry(customerDetails.getCountry());
        }
        if (customerDetails.getPhone() != null) {
            existingCustomer.setPhone(customerDetails.getPhone());
        }
        if (customerDetails.getFax() != null) {
            existingCustomer.setFax(customerDetails.getFax());
        }

        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }


}
