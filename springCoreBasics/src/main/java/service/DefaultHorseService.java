package service;

import domain.Horse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service(value = "horseService")
public class DefaultHorseService implements HorseService {

    @Value("3")
    private int numberOfHorsesForRace;
    private List<Horse> horses;

    @Autowired
    public DefaultHorseService(List<Horse> horses) {
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
