package rayder.xyz;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class MyCustomAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        System.out.println("Hello world!");
        NextcloudAPI apiService = new NextcloudAPI();
        try {
            String fileUrl = apiService.getFileContents("Vault/Passwords.json", "Rayder", "0817");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
