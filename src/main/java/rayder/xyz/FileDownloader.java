package rayder.xyz;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.*;
import java.util.Base64;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;


public class FileDownloader {
    private static final String NEXTCLOUD_API_URL = "https://nc.rayder.xyz/remote.php/webdav/";

    public static void uploadFile(String localFilePath, String remoteFilePath, String username, String password) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(NEXTCLOUD_API_URL + remoteFilePath);
            httpPut.addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));

            File localFile = new File(localFilePath);
            FileEntity fileEntity = new FileEntity(localFile);
            httpPut.setEntity(fileEntity);

            System.out.println("File upload " + httpPut);

            HttpResponse response = httpClient.execute(httpPut);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 201) {
                System.out.println("File uploaded successfully.");
            } else {
                System.err.println("Failed to upload file. Status code: " + statusCode);
            }
        }
    }

    public static void downloadFile(String remoteFilePath, String localFilePath, String username, String password) throws IOException {

    }
}
