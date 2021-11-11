package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainPanel {

    private final JButton startNewCube;
    private final JButton loadPreviousCubes;
    private final JTextField textField;
    private JFrame mainFrame;

    public MainPanel() throws IOException {
        mainFrame = new JFrame("Rubik's Cube Solver");
        textField = new JTextField(20);
       
        JLabel text = new JLabel("Enter Your Name:");
        mainFrame.setPreferredSize(new Dimension(400, 100));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel view = new JPanel();
        view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.X_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        startNewCube = new JButton("Start New Cube");
        loadPreviousCubes = new JButton("Load Previous Cubes");
        buttonPanel.add(startNewCube);
        buttonPanel.add(loadPreviousCubes);
        startNewCube.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadPreviousCubes.setAlignmentX(Component.CENTER_ALIGNMENT);
        textFieldPanel.add(text);
        textFieldPanel.add(textField);
        view.add(textFieldPanel);
        view.add(buttonPanel);
     
        mainFrame.add(view);
        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    public void closeWindow(){
        mainFrame.setVisible(false);
        mainFrame.dispose();
    }

    public String getTitle(){
        return textField.getText();
    }

    public void startButtonListener(ActionListener l){
        startNewCube.addActionListener(l);
    }

    public void loadButtonListener(ActionListener l){
        loadPreviousCubes.addActionListener(l);
    }
}
