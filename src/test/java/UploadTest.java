import client.WebServiceClient;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UploadTest {
    WebServiceClient serviceClient;

    @BeforeMethod
    public void init(){
        serviceClient = new WebServiceClient();
    }

    @Test
    public void uploadTest(){
        serviceClient.upload("test.jpg");
        Assert.assertTrue(serviceClient.isFileUpload("test.jpg"));

    }

}
