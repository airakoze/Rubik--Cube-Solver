import view.*;
import controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Driver {
   
    public static void main(String[] args) throws IOException {
        MainPanel mainPanel = null;
        try {
            mainPanel = new MainPanel();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        RubikCubeController controller = new RubikCubeController(mainPanel);
        if (mainPanel != null) {
            mainPanel.startButtonListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.onStart();
                }
            });
            mainPanel.loadButtonListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.onLoad();
                }
            });
        }
    }
}
