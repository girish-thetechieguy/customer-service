package com.techstarters.customer.service.controller;

import com.techstarters.customer.service.entity.Customer;
import com.techstarters.customer.service.entity.CustomerAddressResponseTemplate;
import com.techstarters.customer.service.service.CustomerService;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
//import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private static final String CUSTOMER_GET_ADDRESS_API = "customerAddressApi";

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer){
        log.info("Inside CustomerController and method registerCustomer");
        Customer savedCustomer = customerService.registerCustomer(customer);
        if(savedCustomer.getCustomerId() == null){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"A new customer not been created");
        }
        return ResponseEntity.ok(savedCustomer);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Customer> getProfileDetails(@PathVariable("id") Long customerId){
        log.info("Inside CustomerController and method getProfileDetails");
        Customer gotCustomer = customerService.getProfileById(customerId);
        if(gotCustomer != null)
            return ResponseEntity.ok(gotCustomer);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Selected customer not found!");
    }

    @GetMapping("/address/{id}")
   // @CircuitBreaker(name = CUSTOMER_GET_ADDRESS_API, fallbackMethod = "customerAddressApiFallBack")
    public CustomerAddressResponseTemplate getCustomerDetailsByCustomerId(@PathVariable("id") Long customerId){
        log.info("Inside CustomerController and method getCustomerDetailsByCustomerId");
        return customerService.getCustomerDetailsById(customerId);
    }

//    private Mono<String> customerAddressApiFallBack(String param1, IllegalArgumentException e) {
//        return Mono.just("test");
//    }
//
//    private Mono<String> customerAddressApiFallBack(String param1, RuntimeException e) {
//        return Mono.just("test");
//    }
//    public List<CustomerAddressResponseTemplate> customerAddressApiFallBack(RuntimeException e){
//        log.info("This is the fallback method from customerAddressApi");
//        return null;
//    }
}
