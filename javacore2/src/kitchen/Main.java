package kitchen;

import kitchen.annotationRunner.ProdRunner;
import kitchen.annotationsHandler.ThisCodeSmellsAnnotationHandler;
import kitchen.exceptions.ProductNotFoundException;
import kitchen.exceptions.RecipeNotFoundException;
import kitchen.meal.Meal;
import products.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException, ClassNotFoundException, NoSuchFieldException, IOException {

        System.out.println("Welcome to my kitchen!");

        List<Product> productsInFridge = new ArrayList<>();
        Meal salad = null;
        Meal soup = null;

        //Reflections were used to instantiate all objects in client code via constructors (with or without arguments) below
        Class potatoClass = Class.forName("products.Potato");
        Constructor potatoConstructor = potatoClass
                .getDeclaredConstructor(int.class, int.class, int.class, int.class, double.class, int.class, Packaging.class);
        potatoConstructor.setAccessible(true);
        Potato potato = (Potato) potatoConstructor.newInstance(76, 2, 0, 16, 1, 150, Packaging.CELLOPHANE);

        //Reflections were used to fill protected fields without setters
        Field caloricityField = Class.forName("products.Product").getDeclaredField("caloricity");
        caloricityField.setAccessible(true);
        caloricityField.set(potato, 88);

        //Reflections were used to instantiate all objects in client code via constructors (with or without arguments) below
        Class beetRootClass = Class.forName("products.BeetRoot");
        Constructor beetRootConstructor = beetRootClass
                .getDeclaredConstructor(int.class, int.class, int.class, int.class, double.class, int.class, Packaging.class);
        beetRootConstructor.setAccessible(true);
        BeetRoot beetRoot = (BeetRoot) beetRootConstructor.newInstance(50, 2, 0, 11, 150, 100, Packaging.TETRAPAC);

        //Reflections were used to instantiate all objects in client code via constructors (with or without arguments) below
        Class cheeseClass = Class.forName("products.Cheese");
        Constructor cheeseConstructor = cheeseClass
                .getDeclaredConstructor(int.class, int.class, int.class, int.class, double.class, int.class, Packaging.class);
        cheeseConstructor.setAccessible(true);
        Cheese cheese = (Cheese) cheeseConstructor.newInstance(290, 17, 24, 0, 3, 100, Packaging.CELLOPHANE);

        productsInFridge.add(potato);
        productsInFridge.add(cheese);
        productsInFridge.add(beetRoot);

        Class mealFactoryClass = Class.forName("kitchen.MealFactory");
        Method method = mealFactoryClass.getDeclaredMethod("create", String.class, List.class);


        try {
            //Reflections were used to change method calls from direct call on reflection call in client code
            salad = (Meal) method.invoke(salad, MealFactory.SALAD, productsInFridge);
            System.out.println("I made " + salad.getClass().getSimpleName() + ". It costs $" + salad.getPrice());
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage() + "for salad");
        }

        try {
            //Reflections were used to change method calls from direct call on reflection call in client code
            soup = (Meal) method.invoke(soup, MealFactory.SOUP, productsInFridge);
            System.out.println("I made " + soup.getClass().getSimpleName() + ". It costs $" + soup.getPrice());
        } catch (InvocationTargetException ex) {
            try {
                Throwable tx = ex.getCause();
                if (tx instanceof ProductNotFoundException) {
                    throw (ProductNotFoundException) ex.getCause();
                }
            } catch (ProductNotFoundException e) {
                System.out.println(e.getMessage() + " for soup");
            }
        }

        try {
            Meal coffee = MealFactory.create("coffee", productsInFridge);
        } catch (RecipeNotFoundException e) {
            System.out.println(e.getMessage());
        }

        //Handle annotations
        ThisCodeSmellsAnnotationHandler handler = new ThisCodeSmellsAnnotationHandler();
        handler.annotationHandle();
        //Print Reviewer Statistic
        handler.printReviewerStatistic();

        //Run method with @ProdCode
        new ProdRunner().runProdeCode();
    }
}
