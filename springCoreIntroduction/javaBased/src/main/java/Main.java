import config.ApplicationConfig;
import config.HorseConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.EmulationService;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HorseConfig.class, ApplicationConfig.class);
        EmulationService emulationService = context.getBean("emulationService", EmulationService.class);
        emulationService.startRace();
    }
}
