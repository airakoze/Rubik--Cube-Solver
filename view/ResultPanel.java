package view;
import javax.swing.*;
import java.awt.*;

public class ResultPanel {

    public ResultPanel(String[] Solution){
        JPanel resultPanel = new JPanel(new GridLayout(2, 2));
        JFrame resultFrame = new JFrame("Result of Rubik's Cube Solver");
        resultFrame.setPreferredSize(new Dimension(250, 300));
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
        JLabel text;
        for (String move : Solution)
          if (move != null){
              text = new JLabel(move);
              textFieldPanel.add(text);
          }
        resultPanel.add(textFieldPanel);
        resultFrame.add(resultPanel);
        resultFrame.pack();
        resultFrame.setResizable(false);
        resultFrame.setVisible(true);
    }
}
