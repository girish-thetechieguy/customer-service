package com.techstarters.customer.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddress {
    private Long addressId;

    private Long customerId;

    private String country;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String zipcode;

    private boolean isDefault;
}
