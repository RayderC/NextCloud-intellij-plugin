package rayder.xyz;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Base64;

public class NextcloudAPI {
    private static final String NEXTCLOUD_API_URL = "https://nc.rayder.xyz/remote.php/webdav/";

    public String getFileContents(String filePath, String username, String password) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(NEXTCLOUD_API_URL + filePath);

        // Set authentication headers
        httpGet.addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));

        HttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200) {
            // Successfully retrieved the file
            return EntityUtils.toString(response.getEntity());
        } else {
            // Handle error cases
            throw new Exception("Failed to retrieve file: " + statusCode);

        }
    }
}
