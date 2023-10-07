package rayder.xyz;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FileExplorerPanel extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, false, false, false, false);
        VirtualFile file = FileChooser.chooseFile(descriptor, null, null);

        System.out.println(file);
        String OrinalFile = String.valueOf(file).replace(" ", "+");
        System.out.println(OrinalFile);
        String filepath = OrinalFile.replace("file:////", "/");
        System.out.println(filepath);

        // You can do something with the selected file here

        String username = "Rayder";
        String password = "Rayder0817";

        System.out.println("Hello world! Up");
        try {
            FileDownloader.uploadFile("C:\\Users\\Test\\Desktop\\New+Text+Document.txt", "/Vault/downloaded-file1new.txt", username, password);
        } catch (IOException f) {
            f.printStackTrace();
        }
    }
}
