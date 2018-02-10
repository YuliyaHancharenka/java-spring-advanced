package products;

public abstract class Vegetable extends Product {

	public Vegetable(int caloricity, int proteins, int fats, int carbonates, int price, int weight, Packaging packaging) {
		super(caloricity, proteins, fats, carbonates, price, weight, packaging);
	}
}
