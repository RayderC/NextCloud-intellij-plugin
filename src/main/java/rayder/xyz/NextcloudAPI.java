package rayder.xyz;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class NextcloudAPI {
    static String NEXTCLOUD_API_URL = "https://nc.rayder.xyz/remote.php/webdav/";

    public static void getFileContents(String filePath, String username, String password) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(NEXTCLOUD_API_URL);

        // Set up basic authentication
        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        request.setHeader("Authorization", "Basic " + base64Credentials);

        Sardine sardine = SardineFactory.begin(username, password);

        try {
            listFilesRecursively(sardine, NEXTCLOUD_API_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listFilesRecursively(Sardine sardine, String directoryUrl) throws IOException {
        List<DavResource> resources = sardine.list(directoryUrl);

        for (DavResource resource : resources) {
            System.out.println(resource.getDisplayName());

            // Check if the resource is a directory
            if (resource.isDirectory()) {
                // Recursively list files in subdirectories
                listFilesRecursively(sardine, resource.getHref().toString());
            }
        }
    }
}
