package rayder.xyz;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class Download extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        try {
            NextcloudAPI.getFileContents("Vault", "Rayder", "Rayder0817");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
