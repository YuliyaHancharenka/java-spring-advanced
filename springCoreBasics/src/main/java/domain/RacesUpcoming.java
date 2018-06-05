package domain;

public enum RacesUpcoming {

    LOCAL("Race is among horses inside the country"),
    INTERNATIONAL("Race is among horses from different countries"),
    INTERCONTINENTAL("Race is among horses from different continentals");

    String name;

    RacesUpcoming(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

