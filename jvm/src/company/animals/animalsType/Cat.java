package company.animals.animalsType;

import company.animals.Animal;
import company.animals.logger.Log;

public class Cat extends Animal {

    @Override
    public void play() {
        Log.info("I play with the mouse");
    }

    @Override
    public void voice() {
        Log.info("Muuur");
    }
}
