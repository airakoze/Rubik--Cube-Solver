package view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RubikCube extends JPanel {
	private final SquareButton firstButton;
	private final SquareButton secondButton;
	private final SquareButton thirdButton;
	private final SquareButton fourthButton;
	private final SquareButton fifthButton;
	private final SquareButton sixthButton;
	private final SquareButton seventhButton;
	private final SquareButton eighthButton;
	private final SquareButton ninthButton;

	public RubikCube(Color[] colors) {
		firstButton = new SquareButton(colors[0]);
		secondButton = new SquareButton(colors[1]);
		thirdButton = new SquareButton(colors[2]);
		fourthButton = new SquareButton(colors[3]);
		fifthButton = new SquareButton(colors[4]);
		sixthButton = new SquareButton(colors[5]);
		seventhButton = new SquareButton(colors[6]);
		eighthButton = new SquareButton(colors[7]);
		ninthButton = new SquareButton(colors[8]);

		this.setLayout(new GridLayout(3,3));
		this.add(firstButton);
		this.add(secondButton);
		this.add(thirdButton);
		this.add(fourthButton);
		this.add(fifthButton);
		this.add(sixthButton);
		this.add(seventhButton);
		this.add(eighthButton);
		this.add(ninthButton);
		this.setBorder(new EmptyBorder(30, 40, 30, 30));
	}

	public void setColors(Color[] colors){
		firstButton.setColor(colors[0]);
		secondButton.setColor(colors[1]);
		thirdButton.setColor(colors[2]);
		fourthButton.setColor(colors[3]);
		fifthButton.setColor(colors[4]);
		sixthButton.setColor(colors[5]);
		seventhButton.setColor(colors[6]);
		eighthButton.setColor(colors[7]);
		ninthButton.setColor(colors[8]);
	}

	public Color[] getColors(){
		return new Color[]{
			firstButton.getColor(),
			secondButton.getColor(),
			thirdButton.getColor(),
			fourthButton.getColor(),
			fifthButton.getColor(),
			sixthButton.getColor(),
			seventhButton.getColor(),
			eighthButton.getColor(),
			ninthButton.getColor()
		};
	}
}
