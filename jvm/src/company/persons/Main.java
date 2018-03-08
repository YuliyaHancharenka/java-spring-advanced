package company.persons;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class Main {

    public static void main(String[] args) {

        Runnable greeting = () -> System.out.println("Welcome to my company!");
        Runnable recruiting = () -> Stream.of(new Person(18, "female", "Valya"),
                new Person(12, "male", "Vitya"))
                .sorted(comparing(Person::getAge)
                        .thenComparing(Person::getName))
                .filter(p -> p.getAge() >= 18)
                .map(Person::getName)
                .forEach(System.out::println);

        new Thread(greeting).start();
        new Thread(recruiting).start();

        //Supplier
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();

        Person p1 = new Person(16, "male", "Vasya");
        Person p2 = new Person(33, "male", "Petya");
        Person p3 = new Person(40, "male", "Vanya");
        Person p4 = new Person(25, "female", "Yuliya");

        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);

        //Comparator
        Comparator<Person> comparatorByName = comparing(Person::getName);
        System.out.println("\nCompare by name: \n" + persons
                .stream()
                .sorted(comparatorByName)
                .collect(Collectors.toList()));

        Comparator<Person> comparatorByAge = comparing(Person::getAge);
        System.out.println("Compare by age: \n" + persons
                .stream()
                .sorted(comparatorByAge)
                .collect(Collectors.toList()));

        //Predicate
        System.out.println("\nPredicate example:");
        persons.forEach(p -> System.out.println(p.getName() + " is adult: " + isAdultPredicate.test(p)));

        //Functions
        Function<Person, String> isAdultCheck = p -> p.getName() + " is adult? : " + isAdultPredicate.test(p);

        //Consumer
        Consumer<Person> checkIsAdult = (p) -> System.out.println("\nFunction and Consumer examples: \n"
                + isAdultCheck.apply(p));
        checkIsAdult.accept(p1);

        //Operators
        List<String> personNames = persons
                .stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        personNames.replaceAll(String::toUpperCase);
        System.out.println("\nOperator example: \n" + personNames);

        //Adult age
        System.out.println("\nAdult age example:");
        persons.forEach(p -> System.out.println("Belarusian low: " + p.getName() + " is adult: " + belarusian.isAdult(p)));
        persons.forEach(p -> System.out.println("American low: " + p.getName() + " is adult: " + american.isAdult(p)));
        persons.forEach(p -> System.out.println("Dominican low: " + p.getName() + " is adult: " + dominican.isAdult(p)));

        //Usage of default methods from AdultAge functional interface
        System.out.println("\nUsage of default methods from AdultAge functional interface - is Person male:");
        persons.forEach(p -> System.out.println(p.getName() + " is " + belarusian.isMalePerson(p)));
        System.out.println("\nUsage of default methods from AdultAge functional interface - is Person female:");
        persons.forEach(p -> System.out.println(p.getName() + " is " + american.isFemalePerson(p)));

        //Usage of static method from AdultAge functional interface
        System.out.println("\nUsage of static method from AdultAge functional interface in order to get Person name:");
        persons.forEach(p -> System.out.println(AdultAge.getPersonName(p)));

        //Lambda usage examples:
        Integer totalAge = persons
                .stream()
                .mapToInt(Person::getAge)
                .sum();

        Integer totalAgeReduce = persons
                .stream()
                .map(Person::getAge)
                .reduce(
                        0,
                        (a, b) -> a + b); //totalAge should be the same value

        List<String> namesOfMaleMembersCollect = persons
                .stream()
                .filter(p -> p.getGender().equals("male"))
                .map(Person::getName)
                .collect(Collectors.toList());

        Map<String, List<Person>> byGender =
                persons
                        .stream()
                        .collect(
                                Collectors.groupingBy(Person::getGender));

        Map<String, List<String>> namesByGender =
                persons
                        .stream()
                        .collect(
                                Collectors.groupingBy(
                                        Person::getGender,
                                        Collectors.mapping(
                                                Person::getName,
                                                Collectors.toList())));

        Map<String, Integer> totalAgeByGender =
                persons
                        .stream()
                        .collect(
                                Collectors.groupingBy(
                                        Person::getGender,
                                        Collectors.reducing(
                                                0,
                                                Person::getAge,
                                                Integer::sum)));

        Map<String, Double> averageAgeByGender = persons
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getGender,
                                Collectors.averagingInt(Person::getAge)));

        System.out.println("\nLambda usage examples: \nTotal age: " + totalAge + "; Total age reduce: " + totalAgeReduce
                + " - should be equals");
        System.out.println("Names of Male Members Collect: " + namesOfMaleMembersCollect);
        System.out.println("Grouping by gender: " + byGender);
        System.out.println("Grouping names by gender: " + namesByGender);
        System.out.println("Total Age By Gender" + totalAgeByGender);
        System.out.println("Average Age By Gender" + averageAgeByGender);
    }

    static Predicate<Person> isAdultPredicate = (p) -> p.getAge() > 18;

    static AdultAge belarusian = LowHelper.belarusianAdultAge;
    static AdultAge american = LowHelper.americanAdultAge;
    static AdultAge dominican = LowHelper.dominicanAdultAge;
}
