package kitchen;

import kitchen.exceptions.ProductNotFoundException;
import kitchen.exceptions.RecipeNotFoundException;
import kitchen.meal.Meal;
import kitchen.meal.Salad;
import kitchen.meal.Soup;
import products.Product;

import java.util.ArrayList;
import java.util.List;

class MealFactory {

    static final String SALAD = "salad";
    static final String SOUP = "soup";

    private static final List saladRecipe = List.of("Cheese", "Potato");
    private static final List soupRecipe = List.of("BeetRoot", "Potato", "SourCream");

    static Meal create(String mealName, List<Product> products) throws RecipeNotFoundException {
        Meal meal;
        switch (mealName.toLowerCase()) {
            case SALAD:
                meal = new Salad(takeRequiredProductsByRecipe(products, saladRecipe));
                break;
            case SOUP:
                meal = new Soup(takeRequiredProductsByRecipe(products, soupRecipe));
                break;
            default:
                throw new RecipeNotFoundException("No such a recipe");
        }
        return meal;
    }

    private static List<Product> takeRequiredProductsByRecipe(List<Product> availableProducts, List recipe) {
        List mealProducts = new ArrayList();
        recipe.forEach(recipeProduct -> {
            Product productForDish = availableProducts
                    .stream()
                    .filter(product -> recipeProduct.equals(product.getClass().getSimpleName()))
                    .findFirst()
                    .orElseThrow(() -> new ProductNotFoundException("No such a product: " + recipeProduct));
            mealProducts.add(productForDish);
        });
        return mealProducts;
    }
}
