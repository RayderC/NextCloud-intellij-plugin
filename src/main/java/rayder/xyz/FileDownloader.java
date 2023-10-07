package rayder.xyz;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class FileDownloader {
    private static final String NEXTCLOUD_API_URL = "https://nc.rayder.xyz/remote.php/webdav/";

    public static void uploadFile(String localFilePath, String remoteFilePath, String username, String password) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(NEXTCLOUD_API_URL + remoteFilePath);
            httpPut.addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));

            File localFile = new File(localFilePath);
            FileEntity fileEntity = new FileEntity(localFile);
            httpPut.setEntity(fileEntity);

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
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(NEXTCLOUD_API_URL + remoteFilePath);
            httpGet.addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));

            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                try (InputStream inputStream = entity.getContent();
                     FileOutputStream outputStream = new FileOutputStream(localFilePath)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                System.out.println("File downloaded successfully.");
            } else {
                System.err.println("Failed to download file. Status code: " + statusCode);
            }
        }
    }
}
