package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;

import java.util.List;

public class Order {

    private static final double ORDER_PRICE = 0.0;
    private List<Product> products;

    public Double getPriceOfAvailableProducts() {
        List<Product> products = getAvailableProducts();
        return getOrderPrice(ORDER_PRICE, products);
    }

    private List<Product> getAvailableProducts() {
        products.removeIf(p -> !p.isAvailable());
        return products;
    }

    private double getOrderPrice(double orderPrice, List<Product> products) {
        for (Product p : products) {
            orderPrice += p.getProductPrice();
        }
        return orderPrice;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
