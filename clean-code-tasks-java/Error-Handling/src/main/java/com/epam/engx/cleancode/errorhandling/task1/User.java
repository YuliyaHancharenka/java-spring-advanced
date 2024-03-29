package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.exception.InvalidAddressDaoException;
import com.epam.engx.cleancode.errorhandling.task1.exception.InvalidOrderDaoException;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Address;

import java.sql.SQLException;
import java.util.List;

public class User {

    private AddressDao addressDao;
    private OrderDao orderDao;
    private String userId;
    private Address defaultAddress;

    public Address getPreferredAddress() {
        try {
            List<Address> deliveryAddresses = null;
            List<Address> orderAddresses = null;
            try {
                deliveryAddresses = addressDao.getDeliveryAddresses(userId);
                orderAddresses = orderDao.getOrderAddresses(userId);
            } catch (InvalidAddressDaoException e) {
                System.out.println("Address dao is invalid or empty");
            } catch (InvalidOrderDaoException e) {
                System.out.println("Order dao is invalid or empty");
            }
            if (!deliveryAddresses.isEmpty())
                return deliveryAddresses.get(0);
            else if (!orderAddresses.isEmpty())
                return orderAddresses.get(orderAddresses.size() - 1);
            else
                return addressDao.getHomeAddress(userId);
        } catch (SQLException e) {
            return defaultAddress;
        }
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDefaultAddress(Address defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
