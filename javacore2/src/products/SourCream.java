package products;

import kitchen.annotations.ThisCodeSmells;

@ThisCodeSmells()
public class SourCream extends Dairy {

	public SourCream(int caloricity, int proteins, int fats, int carbonates, double price, int weight, Packaging packaging) {
		super(caloricity, proteins, fats, carbonates, price, weight, packaging);
	}
}
