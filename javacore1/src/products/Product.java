package products;

import java.util.ArrayList;

public abstract class Product implements FoodEnergy, Cost {

	protected int caloricity;
	protected int proteins;
	protected int fats;
	protected int carbonates;
	protected double price;
	protected int weight;
	protected Packaging packaging;

	public Product(int caloricity, int proteins, int fats, int carbonates, double price, int weight, Packaging packaging) {
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

	public Packaging getPackaging() {
		return packaging;
	}

	public int getWeight() {
		return weight;
	}

	public int getCaloricity() {
		return caloricity;
	}

	public int getProteins() {
		return proteins;
	}

	public int getFats() {
		return fats;
	}

	public int getCarbohydrates() {
		return carbonates;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public String getProductName(){
		String name = "";
		name = this.getClass().getSimpleName();
		return name;
	}
	public ArrayList<Product> getProducts() {
		return new ArrayList<>();
	}
}
