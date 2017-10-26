package service;

import org.apache.log4j.Logger;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import java.io.*;
import java.nio.file.*;

import static java.nio.file.Files.newDirectoryStream;

@WebService
public class FileTransfererImpl implements FileTransferer {
    final static Logger logger = Logger.getLogger(FileTransfererImpl.class);
    @WebMethod
    public void upload(String fileName, byte[] imageBytes) {

        String filePath = "Test/Server/Storage/" + fileName;

        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            BufferedOutputStream outputStream = new BufferedOutputStream(fos);
            outputStream.write(imageBytes);
            outputStream.close();

            System.out.println("Received file: " + filePath);

        } catch (IOException ex) {
            System.err.println(ex);
            throw new WebServiceException(ex);
        }
    }

    @WebMethod
    public byte[] download(String fileName) {
        String filePath = "Test/Server/Storage/" + fileName;
        System.out.println("Sending file: " + filePath);

        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(fis);
            byte[] fileBytes = new byte[(int) file.length()];
            inputStream.read(fileBytes);
            inputStream.close();

            return fileBytes;
        } catch (IOException ex) {
            System.err.println(ex);
            throw new WebServiceException(ex);
        }
    }

    public void showUpload() {
        Path dir = Paths.get("Test/Server/Storage");
        try (DirectoryStream<Path> stream = newDirectoryStream(dir)) {
            for (Path file : stream) {
                System.out.printf("Readable: %b, Writable: %b, Executable: %b ",
                        Files.isReadable(file), Files.isWritable(file),
                        Files.isExecutable(file));
                logger.info(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        }

    }

    @Override
    public void delete(String fileName) {
       File file = new File(fileName);
        if(file.delete()){
            logger.info(System.out.format("File %s was deleted!",fileName));
        }else logger.info(System.out.format("File %s was not found!",fileName));
    }


}
