package service;

import domain.Horse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultHorseService implements HorseService {

    private int numberOfHorsesForRace;
    private List<Horse> horses;

    public DefaultHorseService(int numberOfHorsesForRace, List<Horse> horses) {
        this.numberOfHorsesForRace = numberOfHorsesForRace;
        this.horses = horses;
    }

    @Override
    public List<Horse> getRaceHorses() {
        List<Horse> horsesForRace = new ArrayList<>(numberOfHorsesForRace);
        Collections.shuffle(horses);
        for (int i = 0; i < numberOfHorsesForRace; i++) {
            horsesForRace.add(horses.get(i));
        }

        return horsesForRace;
    }

    public int getNumberOfHorsesForRace() {
        return numberOfHorsesForRace;
    }

    public void setNumberOfHorsesForRace(int numberOfHorsesForRace) {
        this.numberOfHorsesForRace = numberOfHorsesForRace;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void setHorses(List<Horse> horses) {
        this.horses = horses;
    }
}
