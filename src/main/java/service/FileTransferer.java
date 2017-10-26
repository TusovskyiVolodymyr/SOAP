package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface FileTransferer {
    @WebMethod
     void upload(String fileName, byte[] imageBytes);

    @WebMethod
     byte[] download(String fileName);

    @WebMethod
    void showUpload();

    @WebMethod
    void delete(String fileName);
}
