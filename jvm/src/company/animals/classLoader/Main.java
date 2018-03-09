package company.animals.classLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

        CustomClassLoader loader = new CustomClassLoader();
        Class<?> loaderClass = loader.findClass("company.animals.classLoader.AnimalsPrinterTest");
        Object ob = loaderClass.newInstance();
        Method md = loaderClass.getMethod("showAnimals");
        md.invoke(ob);
    }
}

