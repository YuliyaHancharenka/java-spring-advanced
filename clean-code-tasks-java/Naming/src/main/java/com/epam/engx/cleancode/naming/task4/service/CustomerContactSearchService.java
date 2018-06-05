package com.epam.engx.cleancode.naming.task4.service;


import com.epam.engx.cleancode.naming.task4.thirdpartyjar.CustomerContact;

public interface CustomerContactSearchService {

    CustomerContact findCustomerContactDetailsByCustomerId(Long customerId);

}
