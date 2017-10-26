package client;

import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.Files.newDirectoryStream;

public class WebServiceClient {
final static Logger logger = Logger.getLogger(WebServiceClient.class);

    FileTransfererImplService client = new FileTransfererImplService();
    FileTransfererImpl service = client.getFileTransfererImplPort();

    public void upload(String fileName) {
        String filePath = "Test/Client/Upload/" + fileName;
        File file = new File(filePath);
        try {
            logger.info(String.format("Starting upload file: %s",filePath));
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(fis);
            byte[] imageBytes = new byte[1024];
            inputStream.read(imageBytes);
            service.upload(file.getName(), imageBytes);
            inputStream.close();
            logger.info(String.format("File uploaded: %s",filePath));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void download(String fileName) {
      String  filePath = "Test/Client/Download/" + fileName;
        byte[] fileBytes = service.download(fileName);
        logger.info(String.format("Starting download file: %s",filePath));
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            BufferedOutputStream outputStream = new BufferedOutputStream(fos);
            outputStream.write(fileBytes);
            outputStream.close();
            logger.info(String.format("File downloaded: %s",filePath));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    public void showAllFiles(){
        Path dir = Paths.get("Test/Server/Storage");
        try (DirectoryStream<Path> stream = newDirectoryStream(dir)) {
            for (Path file : stream) {
//                System.out.printf("Readable: %b, Writable: %b, Executable: %b ",
//                        Files.isReadable(file), Files.isWritable(file),
//                        Files.isExecutable(file));
                logger.info(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        }
    }

    public void deleteFile(String fileName){
        String path = "Test/Server/Storage/"+fileName;
       File file = new File(path);
        if(file.delete()) {
            logger.info(String.format("File %s was deleted", fileName));
        }
        else  logger.info(String.format("File %s not found",fileName));
    }
    public  boolean isFileUpload(String fileName){
        String path = "Test/Server/Storage/"+fileName;
        File file = new File(path);
        if (file.exists()){
            logger.info(String.format("File exist : %s",fileName));
            return true;
        }
        else return false;
    }
    public  boolean isFileDownload(String fileName){
        String path = "Test/Client/Download/"+fileName;
        File file = new File(path);
        if (file.exists()){
            logger.info(String.format("File exist : %s",fileName));
            return true;
        }
        else return false;
    }
    public  boolean isFileDeleted(String fileName){
        String path = "Test/Server/Storage/"+fileName;
        File file = new File(path);
        if (!file.exists()){
            logger.info(String.format("File is deleted : %s",fileName));
            return true;
        }
        else return false;
    }
}

