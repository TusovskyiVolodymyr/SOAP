import client.WebServiceClient;

public class Main {
    public static void main(String[] args) {
        WebServiceClient serviceClient = new WebServiceClient();
        serviceClient.showAllFiles();
        serviceClient.upload("dodge.jpg");
        serviceClient.showAllFiles();
        serviceClient.download("dodge.jpg");
        serviceClient.upload("deleteTest.jpg");




    }


}
