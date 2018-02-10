package kitchen.meal;

import products.Cost;
import products.Product;

import java.util.ArrayList;
import java.util.List;

public abstract class Meal implements Cost{

    private ArrayList<Product> products;

    Meal() {
    }

    public Meal(List<Product> products) {
        products.addAll(products);
    }

    final public ArrayList<Product> getProducts() {
        return products;
    }

    final public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    final public void addProduct(Product product) {
        products.add(product);
    }

    final public void removeProduct(Product product) {
        products.remove(product);
    }
}
