import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTestGet {

    private Response response;

    @BeforeClass(description = "Create RestAssured and get response")
    public void createRestAssuredAndGetResponse() {
        response = given().get(Constants.RESOURCE_URL).andReturn();
    }

    @Test(description = "Check status code")
    public void checkStatusCode() {
        int actualStatusCode = response.getStatusCode();
        System.out.println("Actual status code value is: " + actualStatusCode);
        Assert.assertEquals(actualStatusCode, Constants.STATUS_CODE,
                "Actual status code value isn't equal to expected value");
    }
}
