package domain;

public class Horse {

    private String name;
    private Integer startNumber;
    private Breed breed;
    private Rider rider;

    public Horse(String name, Integer startNumber, Breed breed, Rider rider) {
        this.name = name;
        this.startNumber = startNumber;
        this.breed = breed;
        this.rider = rider;
    }

    public Horse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }
}
