package kitchen.meal;

import kitchen.annotations.ThisCodeSmells;
import products.Product;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@ThisCodeSmells()
public class Soup extends Meal {

    private final static double MARGIN = 1.10;

    public Soup(List<Product> products) {
        this.setProducts(new ArrayList<>());
        this.getProducts().addAll(products);
    }

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
