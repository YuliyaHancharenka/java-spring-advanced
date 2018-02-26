package company.persons;

@FunctionalInterface
public interface AdultAge {

    boolean isAdult(Person person);

    default boolean isMalePerson(Person person) {
        return person.getGender().equals("male");
    }

    default boolean isFemalePerson(Person person) {
        return person.getGender().equals("female");
    }

    static String getPersonName(Person p) {
        return p.getName();
    }
}
