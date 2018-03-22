import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.EmulationService;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        EmulationService emulationService = context.getBean("emulationService", EmulationService.class);
        emulationService.startRace();
    }
}
