package kitchen;

import kitchen.annotations.UseArrayList;
import kitchen.exceptions.ProductNotFoundException;
import kitchen.exceptions.RecipeNotFoundException;
import kitchen.meal.Meal;
import kitchen.meal.Salad;
import kitchen.meal.Soup;
import products.Product;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

class MealFactory {

    static final String SALAD = "salad";
    static final String SOUP = "soup";

    private static final List saladRecipe = List.of("Cheese", "Potato");
    private static final List soupRecipe = List.of("BeetRoot", "Potato", "SourCream");

    static Meal create(String mealName, List<Product> products) throws RecipeNotFoundException, IllegalAccessException,
            InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException {
        Meal meal;
        switch (mealName.toLowerCase()) {
            case SALAD:
                //Reflections were used to instantiate all objects in client code via constructors (with or without arguments) below
                Class saladClass = Class.forName("kitchen.meal.Salad");
                Constructor saladConstructor = saladClass.getDeclaredConstructor(List.class);
                saladConstructor.setAccessible(true);
                meal = (Salad) saladConstructor.newInstance(takeRequiredProductsByRecipe(products, saladRecipe));

                Field[] fields = saladClass.getDeclaredFields();
                for (Field field : fields) {
                    if (field.getName().startsWith("MAR")) {
                        System.out.printf("I access via reflection API %s %s %s%n",
                                Modifier.toString(field.getModifiers()),
                                field.getType().getSimpleName(),
                                field.getName());
                    }
                }
                break;
            case SOUP:
                //Reflections were used to instantiate all objects in client code via constructors (with or without arguments) below
                Class soupClass = Class.forName("kitchen.meal.Soup");
                Constructor soupConstructor = soupClass.getDeclaredConstructor(List.class);
                soupConstructor.setAccessible(true);
                meal = (Soup) soupConstructor.newInstance(takeRequiredProductsByRecipe(products, soupRecipe));
                break;
            default:
                throw new RecipeNotFoundException("No such a recipe");
        }
        return meal;
    }

    @UseArrayList
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
