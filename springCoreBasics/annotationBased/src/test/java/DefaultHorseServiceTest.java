import config.ApplicationConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.HorseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class DefaultHorseServiceTest {

     @Autowired
     HorseService horseService;

    @Test
    public void test_numberOfHorsesForRace() {
        Assert.assertTrue(!horseService.getRaceHorses().isEmpty());
        Assert.assertEquals(horseService.getRaceHorses().size(), (int) horseService.getNumberOfHorsesForRace());
    }
}
