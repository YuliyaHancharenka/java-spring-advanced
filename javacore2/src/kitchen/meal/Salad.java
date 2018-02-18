package kitchen.meal;

import kitchen.annotations.ProdCode;
import kitchen.annotations.ThisCodeSmells;
import products.Product;

import java.util.ArrayList;
import java.util.List;

public class Salad extends Meal {

    private final static double MARGIN = 1.15;

    public Salad(List<Product> products) {
        this.setProducts(new ArrayList<>());
        this.getProducts().addAll(products);
    }

    @ProdCode
    @ThisCodeSmells()
    @SuppressWarnings({"unchecked"})
    @Override
    public double getPrice() {
        return getProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum() * MARGIN;
    }
}
