import client.WebServiceClient;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteTest {
    WebServiceClient serviceClient;

    @BeforeMethod
    public void init(){
        serviceClient = new WebServiceClient();
    }

    @Test
    public void uploadTest(){
        serviceClient.deleteFile("deleteTest.jpg");
        Assert.assertTrue(serviceClient.isFileDeleted("deleteTest.jpg"));

    }
}
