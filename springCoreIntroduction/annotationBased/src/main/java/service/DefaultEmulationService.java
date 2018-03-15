package service;

import domain.Horse;
import domain.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service(value = "emulationService")
public class DefaultEmulationService implements EmulationService {

    private static final int RACE_LENGTH = 15;
    private RaceService raceService;

    @Autowired
    public DefaultEmulationService(RaceService raceService) {
        this.raceService = raceService;
    }

    public RaceService getRaceService() {
        return raceService;
    }

    public void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }

    @Override
    public void startRace() {
        try {
            Race race = raceService.getRace();
            System.out.println("Hi! It is a time for race: " + race.getRaceName() + "\nWe have the following hourses participating:");

            for (int i = 0; i < race.getHorses().size(); i++) {
                Horse horse = race.getHorses().get(i);
                System.out.print("\n" + (i + ". ") + horse.getName() + " (" + horse.getBreed().getName() + ")," +
                        " start number: " + horse.getStartNumber() + ")" + " Rider: " + horse.getRider().getName() +
                        "(" + horse.getRider().getCountry() + ")");
            }
            System.out.print("\nChoose the horse to make a bet! Type the horse start number: ");
            Scanner scanner = new Scanner(System.in);
            int chosenHorseNumber = scanner.nextInt();
            System.out.println("You chose: " + race.getHorses().get(chosenHorseNumber).getName());
            System.out.println("Race is starting!");

            running(race.getHorses(), chosenHorseNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void running(List<Horse> horses, int chosenHorseNumber) throws InterruptedException {
        int maxLength = horses.stream().mapToInt(horse -> horse.getName().length())
                .max().getAsInt();
        List<StringBuilder> positionsList = new ArrayList<>();
        for (int i = 0; i < horses.size(); i++) {
            positionsList.add(new StringBuilder());
        }
        Random random = new Random();
        String raceTitle = getRaceStart(maxLength) + "Start" + getRaceFinish(RACE_LENGTH) + "Finish";

        Thread.sleep(1000);
        System.out.println(raceTitle);
        for (int i = 0; i < horses.size(); i++) {
            System.out.println(horses.get(i).getName() + ":" + getHorseStart(horses.get(i), maxLength)
                    + positionsList.get(i).toString() + "♘");
        }

        int position = 0;
        while (position < RACE_LENGTH) {
            for (StringBuilder pos : positionsList) {
                if (random.nextBoolean()) {
                    pos.append(" ");
                }
            }

            Thread.sleep(1000);
            System.out.println(raceTitle);
            for (int i = 0; i < horses.size(); i++) {
                int horsePosition = positionsList.get(i).toString().length();
                if (horsePosition > position) {
                    position = positionsList.get(i).toString().length();
                }
                System.out.println(horses.get(i).getName() + ":" + getHorseStart(horses.get(i), maxLength)
                        + positionsList.get(i).toString() + "♘");
            }
        }

        for (int i = 0; i < positionsList.size(); i++) {
            if (positionsList.get(i).toString().length() == RACE_LENGTH) {
                if (chosenHorseNumber == i) {
                    System.out.println("You win");
                } else {
                    System.out.println("Winner is: " + horses.get(i).getName());
                }
                break;
            }
        }
    }

    private String getHorseStart(Horse horse, int maxNameLength) {
        StringBuilder sb = new StringBuilder();
        int horseNameLength = horse.getName().length();
        while (horseNameLength++ < maxNameLength) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private String getRaceStart(int maxLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < maxLength; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private String getRaceFinish(int raceLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 5; i < raceLength; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
