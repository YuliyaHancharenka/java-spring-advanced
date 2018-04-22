import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class RestAssuredTestPost {

    private CloseableHttpClient httpclient;

    @BeforeMethod(description = "Init HttpClient")
    public void initHttpClient() {
        httpclient = HttpClients.createDefault();
    }

    @Test(description = "Check status code")
    public void checkStatusCode() throws IOException {
        HttpPost postRequest = new HttpPost(Constants.RESOURCE_URL);
        HttpResponse response = httpclient.execute(postRequest);
        int statusCodeAct = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCodeAct, Constants.STATUS_CODE, "Actual status code value isn't equal to expected value");
    }
}
