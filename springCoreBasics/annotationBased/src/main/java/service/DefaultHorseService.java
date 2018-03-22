package service;

import domain.Horse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@PropertySource("classpath:application.properties")
@Service("horseService")
public class DefaultHorseService implements HorseService {

    @Value("${numberOfHorsesForRace}")
    private Integer numberOfHorsesForRace;
    private List<Horse> horses;

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

    @Override
    public Integer getNumberOfHorsesForRace() {
        return numberOfHorsesForRace;
    }

    public void setNumberOfHorsesForRace(Integer numberOfHorsesForRace) {
        this.numberOfHorsesForRace = numberOfHorsesForRace;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    @Autowired
    public void setHorses(List<Horse> horses) {
        this.horses = horses;
    }


}
