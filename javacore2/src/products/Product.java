package products;

import kitchen.annotations.ThisCodeSmells;

import java.util.ArrayList;

public abstract class Product implements FoodEnergy, Cost {

    @ThisCodeSmells()
    protected int caloricity;
    protected int proteins;
    protected int fats;
    protected int carbonates;
    protected double price;
    protected int weight;
    protected Packaging packaging;

    public Product(@ThisCodeSmells() int caloricity, int proteins, int fats, int carbonates, double price, int weight, Packaging packaging) {
        if (caloricity < 1) throw new IllegalArgumentException("Caloricity can't be less than 1");
        if (packaging == null) throw new NullPointerException("Packaging can't be null");

        this.caloricity = caloricity;
        this.proteins = proteins;
        this.fats = fats;
        this.carbonates = carbonates;
        this.price = price;
        this.weight = weight;
        this.packaging = packaging;
    }

    @ThisCodeSmells(reviewer = "Yuliya")
    public Packaging getPackaging() {
        return packaging;
    }

    public int getWeight() {
        return weight;
    }

    /**
     * Gets product caloricity
     * @return caloricity - calorific value
     */
    @Override
    public int getCaloricity() {
        return caloricity;
    }

    /**
     * Gets product proteins
     * @return proteins - proteins value
     */
    @Override
    public int getProteins() {
        return proteins;
    }

    /**
     * Gets product fats
     * @return fats - fats value
     */
    @Override
    public int getFats() {
        return fats;
    }

    /**
     * Gets product carbonates
     * @return carbonates - carbonates value
     */
    @Override
    public int getCarbohydrates() {
        return carbonates;
    }

    /**
     * Gets product price
     * @return price - price value
     */
    @Override
    public double getPrice() {
        return price;
    }

    @ThisCodeSmells(reviewer = "Yuliya")
    public void setPrice(double price) {
        this.price = price;
    }

    @ThisCodeSmells(reviewer = "Aliaksei")
    public String getProductName() {
        String name = "";
        name = this.getClass().getSimpleName();
        return name;
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<>();
    }
}
