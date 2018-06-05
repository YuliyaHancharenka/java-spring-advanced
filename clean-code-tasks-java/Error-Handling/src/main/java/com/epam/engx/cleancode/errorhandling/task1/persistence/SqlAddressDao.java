package com.epam.engx.cleancode.errorhandling.task1.persistence;

import com.epam.engx.cleancode.errorhandling.task1.AddressDao;
import com.epam.engx.cleancode.errorhandling.task1.exception.InvalidUserException;
import com.epam.engx.cleancode.errorhandling.task1.persistence.thirdpartyjar.SqlService;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Address;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlAddressDao implements AddressDao {

    private SqlService sqlService;

    public Address getHomeAddress(String userId) throws SQLException {
        Address address = null;
         try {
             address = new Address(sqlService.queryUserHomeAddress(userId));
         }catch (InvalidUserException e){
             System.out.println("User id is invalid or empty");
         }
         return address;
    }

    public List<Address> getDeliveryAddresses(String userId) throws SQLException {
        List<Address> addresses = new ArrayList<>();
        try {
        for (Map addressData : sqlService.queryUserDeliveryAddress(userId)) {
            addresses.add(new Address(addressData));
        }
        }catch (InvalidUserException e){
            System.out.println("User id is invalid or empty");
        }
        return addresses;
    }
}
