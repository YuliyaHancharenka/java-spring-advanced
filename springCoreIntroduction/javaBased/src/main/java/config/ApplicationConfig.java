package config;

import domain.Horse;
import domain.Race;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.*;

import java.util.List;

@Configuration
public class ApplicationConfig {

    private static final int HORSES_NUMBER = 3;

    @Bean
    public Race race() {
        return new Race();
    }

    @Bean
    public HorseService horseService(List<Horse> horseList) {
        return new DefaultHorseService(HORSES_NUMBER, horseList);
    }

    @Bean
    public RaceService raceService(HorseService horseService, Race todayRace) {
        return new DefaultRaceService(horseService, todayRace);
    }

    @Bean
    public EmulationService emulationService(RaceService todayRace) {
        return new DefaultEmulationService(todayRace);
    }
}
