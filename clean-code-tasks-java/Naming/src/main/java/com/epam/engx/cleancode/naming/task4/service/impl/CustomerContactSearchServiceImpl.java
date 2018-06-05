package com.epam.engx.cleancode.naming.task4.service.impl;

import com.epam.engx.cleancode.naming.task4.service.CustomerContactSearchService;
import com.epam.engx.cleancode.naming.task4.thirdpartyjar.CustomerContact;
import com.epam.engx.cleancode.naming.task4.thirdpartyjar.CustomerContactDAO;

public class CustomerContactSearchServiceImpl implements CustomerContactSearchService {

    private CustomerContactDAO customerContactDAO;

    public CustomerContact findCustomerContactDetailsByCustomerId(Long customerId) {
        return customerContactDAO.findById(customerId);
    }
}
