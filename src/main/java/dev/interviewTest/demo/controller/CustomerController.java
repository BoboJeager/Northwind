package dev.interviewTest.demo.controller;

import dev.interviewTest.demo.model.Customer;
import dev.interviewTest.demo.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/countries/**")
    public ResponseEntity<List<Customer>> getCustomersByMultipleCountries(HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        AntPathMatcher apm = new AntPathMatcher();
        String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);

        List<String> countries = Arrays.asList(finalPath.split("/"));
        List<Customer> customers = customerService.getAllCustomersByCountries(countries);
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }



    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Map<String, Object> customerDetails){
        try {
            Customer customerToUpdate = convertMapToCustomer(customerDetails);
            Customer updatedCustomer = customerService.updateCustomer(id, customerToUpdate);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private Customer convertMapToCustomer(Map<String, Object> customerDetails) {
        Customer customer = new Customer();

        Optional.ofNullable(customerDetails.get("companyName")).ifPresent(v -> customer.setCompanyName((String) v));
        Optional.ofNullable(customerDetails.get("contactTitle")).ifPresent(v -> customer.setContactTitle((String) v));
        Optional.ofNullable(customerDetails.get("address")).ifPresent(v -> customer.setAddress((String) v));
        Optional.ofNullable(customerDetails.get("city")).ifPresent(v -> customer.setCity((String) v));
        Optional.ofNullable(customerDetails.get("region")).ifPresent(v -> customer.setRegion((String) v));
        Optional.ofNullable(customerDetails.get("postalCode")).ifPresent(v -> customer.setPostalCode((String) v));
        Optional.ofNullable(customerDetails.get("country")).ifPresent(v -> customer.setCountry((String) v));
        Optional.ofNullable(customerDetails.get("phone")).ifPresent(v -> customer.setPhone((String) v));
        Optional.ofNullable(customerDetails.get("fax")).ifPresent(v -> customer.setFax((String) v));

        return customer;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
