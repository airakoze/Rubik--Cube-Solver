package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SquareButton extends JComponent implements MouseListener {
    private Color color;
    private int count = 1;
    private boolean mouseEntered = false;

    public SquareButton(Color color) {
        super();
        this.color = color;
        enableInputMethods(true);
        addMouseListener(this);
        Dimension size = new Dimension(50, 50);
        setSize(size.width, size.height);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, 80, 80);

        if(mouseEntered)
            g.setColor(Color.decode("#ADD8E6").brighter());
        else
            g.setColor(Color.BLACK);

        g.drawRect(0, 0, 80, 80);
    }

    public void update(Color color){
        this.color = color;
        if (count < 6)
            count++;
        else
            count = 1;
    }

    public void setColor(Color color){
        this.color = color;
        repaint();
    }
    public Color getColor(){
        return this.color;
    }

    public void mousePressed(MouseEvent e) {
        switch (count) {
            case 1:
                update(Color.RED);
                break;
            case 2:
                update(Color.GREEN);
                break;
            case 3:
                update(Color.BLUE);
                break;
            case 4:
                update(Color.ORANGE);
                break;
            case 5:
                update(Color.YELLOW);
                break;
            case 6:
                update(Color.WHITE);
                break;
        }
        repaint();
    }

    public void mouseEntered(MouseEvent e) {
        mouseEntered = true;
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        repaint();
    }

    public void mouseExited(MouseEvent e) {
        mouseEntered = false;
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

}
