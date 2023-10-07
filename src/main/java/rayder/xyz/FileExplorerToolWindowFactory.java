package rayder.xyz;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

public class FileExplorerToolWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        // Create your custom file explorer UI component here
        // For example, you can use a JTree or a JList to display files and directories
        // Add your UI component to the toolWindow's content
        FileExplorerPanel fileExplorerPanel = new FileExplorerPanel();
        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(fileExplorerPanel, "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
