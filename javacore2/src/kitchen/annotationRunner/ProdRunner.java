package kitchen.annotationRunner;

import kitchen.annotations.ProdCode;
import kitchen.annotationsHandler.ThisCodeSmellsAnnotationHandler;
import kitchen.meal.Salad;
import products.BeetRoot;
import products.Packaging;
import products.Potato;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ProdRunner {

    private List<Class> classesList = ThisCodeSmellsAnnotationHandler.getAllClasses();

    public ProdRunner() throws IOException, ClassNotFoundException {
    }

    public void runProdeCode() throws InvocationTargetException, IllegalAccessException {
        for (Class k : classesList) {
            Method methods[] = k.getDeclaredMethods();
            for (Method m : methods) {
                Annotation[] annotations = m.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof ProdCode) {
                        Salad salad = createObjectForProdCodeRun();
                        System.out.println("\nInvocation of method " + m.getName() + " with result of price:  " + m.invoke(salad));
                    }
                }
            }
        }
    }

    private Salad createObjectForProdCodeRun() {
        Potato potato = new Potato(76, 2, 0, 16, 1, 150, Packaging.CELLOPHANE);
        BeetRoot beetRoot = new BeetRoot(50, 2, 0, 11, 150, 100, Packaging.TETRAPAC);
        List products = new ArrayList();
        products.add(potato);
        products.add(beetRoot);
        return new Salad(products);
    }
}
