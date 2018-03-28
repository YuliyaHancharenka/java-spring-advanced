package kitchen.meal;

import products.Product;

import java.util.List;

public class Soup extends Meal {

    private final static double MARGIN = 1.10;

    public Soup(List<Product> products) {
        super();
        products.addAll(products);
    }

    @Override
    public double getPrice() {
        return getProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum() * MARGIN;
    }
}
