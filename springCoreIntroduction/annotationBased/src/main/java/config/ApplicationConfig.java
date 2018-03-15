package config;

import domain.Breed;
import domain.Horse;
import domain.Race;
import domain.Rider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("service")
public class ApplicationConfig {

    @Bean
    public Horse horseOne() {
        Horse horse = new Horse();
        horse.setName("Arrow");
        horse.setStartNumber(1);
        horse.setBreed(new Breed("American breed"));
        horse.setRider(new Rider("Bob", "USA"));
        return horse;
    }

    @Bean
    public Horse horseTwo() {
        Horse horse = new Horse();
        horse.setName("Bullet");
        horse.setStartNumber(2);
        horse.setBreed(new Breed("German breed"));
        horse.setRider(new Rider("Hahn", "Germany"));
        return horse;
    }

    @Bean
    public Horse horseThree() {
        Horse horse = new Horse();
        horse.setName("Strike");
        horse.setStartNumber(3);
        horse.setBreed(new Breed("Polish"));
        horse.setRider(new Rider("Boguslav", "Poland"));
        return horse;
    }

    @Bean
    public Horse horseFour() {
        Horse horse = new Horse();
        horse.setName("Storm");
        horse.setStartNumber(4);
        horse.setBreed(new Breed("Russian"));
        horse.setRider(new Rider("Petya", "Russia"));
        return horse;
    }

    @Bean
    public Race todayRace() {
        return new Race();
    }
}
