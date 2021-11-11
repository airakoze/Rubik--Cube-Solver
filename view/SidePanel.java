package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class SidePanel {
	private final JButton next;
	private final JButton saveAndRun;
	private final JLabel title;
	private final Color[] defaultColors = {Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE};
	private final RubikCube side;
	private final FileWriter fileSave;
	private final FileWriter currentFile;
	private int numSide = 1;

	public SidePanel(String fileName) throws IOException {
		JFrame mainFrame = new JFrame("Rubik's Cube Solver");
		fileSave = new FileWriter(fileName);
		currentFile = new FileWriter("current.csv");
		mainFrame.setPreferredSize(new Dimension(340, 400));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel view = new JPanel();
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		title = new JLabel("Side " + numSide);
		side = new RubikCube(defaultColors);
		next = new JButton("Next");
		saveAndRun = new JButton("Save and Run");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		side.setAlignmentX(Component.CENTER_ALIGNMENT);
		next.setAlignmentX(Component.CENTER_ALIGNMENT);
		view.add(title);
		view.add(side);
		buttonPanel.add(next);
		buttonPanel.add(saveAndRun);
		saveAndRun.setEnabled(false);
		view.add(buttonPanel);

		mainFrame.add(view);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}

	public void onNext(){
		try {
			saveColors();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}

		if ( numSide < 6) {
			numSide++;
			title.setText("Side " + numSide);
		}
		if (numSide == 6) {
			next.setEnabled(false);
			saveAndRun.setEnabled(true);
		}
		side.setColors(defaultColors);
	}

	public void onSaveAndRun(){
		try {
			saveColors();
			fileSave.close();
			currentFile.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void saveColors() throws IOException {
		Color[] colors = side.getColors();
		for (Color color : colors) {
			fileSave.append(getColorName(color));
			fileSave.append(",");
			currentFile.append(getColorName(color));
			currentFile.append(",");
		}
		fileSave.append("\n");
		fileSave.flush();
		currentFile.append("\n");
		currentFile.flush();
	}

	public String getColorName(Color color){
		if (Color.BLUE.equals(color)) {
			return "B";
		} else if (Color.YELLOW.equals(color)) {
			return "Y";
		} else if (Color.RED.equals(color)) {
			return "R";
		} else if (Color.ORANGE.equals(color)) {
			return "O";
		} else if (Color.WHITE.equals(color)) {
			return "W";
		} else if (Color.GREEN.equals(color)) {
			return "G";
		} else {
			return null;
		}
	}

	public void nextButtonListener(ActionListener l){
		next.addActionListener(l);
	}

	public void saveAndRunButtonListener(ActionListener l){
		saveAndRun.addActionListener(l);
	}
}