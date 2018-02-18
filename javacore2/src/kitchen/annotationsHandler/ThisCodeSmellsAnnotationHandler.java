package kitchen.annotationsHandler;

import kitchen.annotations.ThisCodeSmells;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.net.URL;
import java.util.*;


public class ThisCodeSmellsAnnotationHandler {

    public static List<Class> classesList;
    Map<String, Integer> reviewerVotesMap = new HashMap<>();

    public static List getAllClasses() throws IOException, ClassNotFoundException {
        Class[] kitchenClasses = getClasses("kitchen");
        Class[] productClasses = getClasses("products");
        classesList = new ArrayList(Arrays.asList(kitchenClasses));
        classesList.addAll(Arrays.asList(productClasses));
        return classesList;
    }

    public void annotationHandle() throws IOException, ClassNotFoundException {
        classesList = getAllClasses();
        getAnnotatedInfo(classesList.toArray());
        for (Class cl : classesList) {
            getAnnotatedInfo(cl.getDeclaredFields());
            getAnnotatedInfo(cl.getDeclaredMethods());
        }
    }

    public void getAnnotatedInfo(Object[] objects) {
        for (Object o : objects) {
            AnnotatedElement annotatedElement = (AnnotatedElement) o;
            Annotation[] annotations = annotatedElement.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof ThisCodeSmells) {
                    ThisCodeSmells myAnnotation = (ThisCodeSmells) annotation;
                    System.out.println("\n@ThisCodeSmells annotation for:");
                    if (o instanceof Class) {
                        System.out.println("Smelt code name: " + ((Class) o).getSimpleName());
                    } else if (o instanceof Member) {
                        System.out.println("Smelt code name: " + ((Member) o).getName());
                    }
                    System.out.println("Reviewer: " + myAnnotation.reviewer());
                    if (!reviewerVotesMap.containsKey(myAnnotation.reviewer())) {
                        reviewerVotesMap.put(myAnnotation.reviewer(), 0);
                    }
                    reviewerVotesMap.put(myAnnotation.reviewer(), reviewerVotesMap.get(myAnnotation.reviewer()) + 1);
                }
            }
        }
    }

    public void printReviewerStatistic() {
        System.out.println("\nReviewer Statistic:");
        reviewerVotesMap.entrySet()
                .stream()
                .sorted((o1, o2) -> {
                    return o1.getValue().compareTo(o2.getValue()) * -1;
                })
                .forEach(reviewerVotesEntry -> {
                    System.out.println("Reviewer " + reviewerVotesEntry.getKey() + " productivity: " + reviewerVotesEntry.getValue());
                });
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }


    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
