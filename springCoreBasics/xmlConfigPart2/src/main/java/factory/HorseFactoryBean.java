package factory;

import domain.Breed;
import domain.Horse;
import domain.Rider;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

public class HorseFactoryBean implements FactoryBean {

    private String name;
    private Integer startNumber;
    private Breed breed;
    private Rider rider;

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

    @Nullable
    @Override
    public Horse getObject() throws Exception {
        return new Horse(name, startNumber, breed, rider);
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return Horse.class;
    }
}
