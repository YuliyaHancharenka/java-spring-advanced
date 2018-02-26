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

}
