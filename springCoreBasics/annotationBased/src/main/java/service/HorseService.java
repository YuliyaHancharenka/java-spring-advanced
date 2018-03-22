package service;

import domain.Horse;

import java.util.List;

public interface HorseService {

    public List<Horse> getRaceHorses();
    public Integer getNumberOfHorsesForRace();
}
