package com.epam.engx.cleancode.naming.task4.service.impl;

import com.epam.engx.cleancode.naming.task4.service.CustomerContactUpdateService;
import com.epam.engx.cleancode.naming.task4.thirdpartyjar.CustomerContact;
import com.epam.engx.cleancode.naming.task4.thirdpartyjar.CustomerContactDAO;

public class CustomerContactUpdateServiceImpl implements CustomerContactUpdateService {

    private CustomerContactDAO customerContactDAO;

    public void updateCustomerContactDetails(CustomerContact customerContactDetails) {
        customerContactDAO.update(customerContactDetails);
    }
}
