package com.techstarters.customer.service.service;

import com.techstarters.customer.service.entity.Customer;
import com.techstarters.customer.service.entity.CustomerAddress;
import com.techstarters.customer.service.entity.CustomerAddressList;
import com.techstarters.customer.service.entity.CustomerAddressResponseTemplate;
import com.techstarters.customer.service.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Customer registerCustomer(Customer customer) {
        log.info("Inside CustomerService and method registerCustomer()");
        return customerRepository.save(customer);
    }

    public Customer getProfileById(Long customerId) {
        log.info("Inside CustomerService and method getProfileById()");
        return customerRepository.findByCustomerId(customerId);
    }

    public CustomerAddressResponseTemplate getCustomerDetailsById(Long customerAddressId) {
        log.info("Inside CustomerService and method getCustomerDetailsById()");
        Customer customerDetails = customerRepository.findByCustomerId(customerAddressId);
        CustomerAddressList customerAddressList =
                restTemplate.getForObject("http://CUSTOMER-ADDRESS-SERVICE/address/customer/"+customerDetails.getCustomerId(),
                        CustomerAddressList.class);
        CustomerAddressResponseTemplate customerAddressResponseTemplate = new CustomerAddressResponseTemplate();
        customerAddressResponseTemplate.setCustomerAddressList(customerAddressList);
        customerAddressResponseTemplate.setCustomer(customerDetails);
        return customerAddressResponseTemplate;
    }
}
