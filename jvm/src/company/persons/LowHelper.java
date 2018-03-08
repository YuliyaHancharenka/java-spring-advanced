package company.persons;

public class LowHelper {

    public static AdultAge belarusianAdultAge = (p) -> {
        return p.getAge() >= 18;
    };

    public static AdultAge americanAdultAge = (p) -> {
        return p.getAge() >= 21;
    };

    public static AdultAge dominicanAdultAge = (p) -> {
        return p.getAge() >= 16;
    };

    public static AdultAge belarusianAdultAge2 = new AdultAge() {
        @Override
        public boolean isAdult(Person person) {
            return person.getAge() >= 18;
        }
    };

    public static AdultAge americanAdultAge2 = new AdultAge() {
        @Override
        public boolean isAdult(Person person) {
            return person.getAge() >= 21;
        }
    };

    public static AdultAge dominicanAdultAge2 = new AdultAge() {
        @Override
        public boolean isAdult(Person person) {
            return person.getAge() >= 16;
        }
    };

}
