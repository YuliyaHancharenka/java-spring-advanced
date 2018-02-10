package kitchen;

import kitchen.exceptions.ProductNotFoundException;
import kitchen.exceptions.RecipeNotFoundException;
import kitchen.meal.Meal;
import products.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to my kitchen!");

        List<Product> productsInFridge = new ArrayList<>();
        Meal salad;
        Meal soup;

        Potato potato = new Potato(76, 2, 0, 16, 1, 150, Packaging.CELLOPHANE);
        BeetRoot beetRoot = new BeetRoot(50, 2, 0, 11, 150, 100, Packaging.TETRAPAC);
        Cheese cheese = new Cheese(290, 17, 24, 0, 3, 100, Packaging.CELLOPHANE);

        productsInFridge.add(potato);
        productsInFridge.add(cheese);
        productsInFridge.add(beetRoot);

        try {
            salad = MealFactory.create(MealFactory.SALAD, productsInFridge);
            System.out.println("I made " + salad.getClass().getSimpleName() + ". It costs $" + salad.getPrice());
        } catch (ProductNotFoundException | RecipeNotFoundException e) {
            System.out.println(e.getMessage() + "for salad");
        }

        try {
            soup = MealFactory.create(MealFactory.SOUP, productsInFridge);
            System.out.println("I made " + soup.getClass().getSimpleName() + ". It costs $" + soup.getPrice());
        } catch (ProductNotFoundException | RecipeNotFoundException e) {
            System.out.println(e.getMessage() + " for soup");
        }

        try {
            Meal coffee = MealFactory.create("coffee", productsInFridge);
        } catch (RecipeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
