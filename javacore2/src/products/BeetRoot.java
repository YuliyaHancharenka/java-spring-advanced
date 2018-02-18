package products;

import kitchen.annotations.ThisCodeSmells;

public class BeetRoot extends Vegetable {
    @ThisCodeSmells()
    public BeetRoot(int caloricity, int proteins, int fats, int carbonates, double price, int weight, Packaging packaging) {
        super(caloricity, proteins, fats, carbonates, price, weight, packaging);
    }
}
