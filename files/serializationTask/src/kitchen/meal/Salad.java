package kitchen.meal;

import products.Product;

import java.util.ArrayList;
import java.util.List;

public class Salad extends Meal {

    private final static double MARGIN = 1.15;

    public Salad(List<Product> products) {
        this.setProducts(new ArrayList<>());
        this.getProducts().addAll(products);
    }

    @Override
    public double getPrice() {
        return getProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum() * MARGIN;
    }
}
