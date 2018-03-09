package company.animals.classLoader;

import company.animals.Animal;
import company.animals.animalsType.Cat;
import company.animals.animalsType.Dog;

import java.util.ArrayList;
import java.util.List;

public class AnimalsPrinterTest {

    List<Animal> animalList = new ArrayList();

    public void showAnimals() {
        animalList.add(new Dog());
        animalList.add(new Cat());

        animalList.forEach(animal -> {
            animal.play();
            animal.voice();
        });
    }
}
