package company.animals.animalsType;

import company.animals.Animal;
import company.animals.logger.Log;

public class Dog extends Animal {

    @Override
    public void play() {
        Log.info("I play with the bones");
    }

    @Override
    public void voice() {
        Log.info("Gaaav");
    }
}
