package service;

import domain.Race;
import domain.RacesUpcoming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service(value = "raceService")
public class DefaultRaceService implements RaceService {

    @Autowired
    @Qualifier("horseService")
    private HorseService horseService;
    @Autowired
    private Race todayRace;

    public DefaultRaceService(HorseService horseService, Race todayRace) {
        this.horseService = horseService;
        this.todayRace = todayRace;
    }

    @Override
    public Race getRace() {
        todayRace.setHorses(horseService.getRaceHorses());
        todayRace.setRaceName(generateRaceName());
        return todayRace;
    }

    private String generateRaceName() {
        List<RacesUpcoming> races = Arrays.asList(RacesUpcoming.values());
        Collections.shuffle(races);
        return races.get(0).getName();
    }

    public HorseService getHorseService() {
        return horseService;
    }

    public void setHorseService(HorseService horseService) {
        this.horseService = horseService;
    }

    public Race getTodayRace() {
        return todayRace;
    }

    public void setTodayRace(Race todayRace) {
        this.todayRace = todayRace;
    }
}
