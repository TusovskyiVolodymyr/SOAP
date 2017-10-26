import client.WebServiceClient;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DownloadTest {
    WebServiceClient serviceClient;

    @BeforeMethod
    public void init(){
        serviceClient = new WebServiceClient();
    }

    @Test
    public void uploadTest(){
        serviceClient.download("test.jpg");
        Assert.assertTrue(serviceClient.isFileDownload("test.jpg"));

    }
}
