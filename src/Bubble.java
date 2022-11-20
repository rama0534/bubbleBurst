import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class Bubble extends JPanel {
    int x, y, diameter;
    String color;

    public  Bubble( int x, int y, int diameter, String color, Graphics g){
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = color;
        g.setColor(Color.decode(color));
        g.drawOval( x-diameter/2, y, diameter, diameter);
        g.fillOval(x-diameter/2, y, diameter, diameter);
    }

}
