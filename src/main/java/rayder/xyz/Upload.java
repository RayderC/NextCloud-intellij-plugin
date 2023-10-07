package rayder.xyz;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.io.IOException;

public class Upload extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        String filePath = "Vault/Passwords.json";
        String username = "Rayder";
        String password = "Rayder0817";

        System.out.println("Hello world! Up");
        try {
            FileDownloader.uploadFile("C:\\Users\\Rayder\\IdeaProjects\\untitled3\\src\\downloaded-file1.txt", "/Vault/downloaded-file1.txt", username, password);
        } catch (IOException f) {
            f.printStackTrace();
        }
    }
}
