import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BurstBubble {
    int diameter = 50;
    double pointLocation;
    public  BurstBubble(Graphics g, ArrayList<Point> mouseClicks, ArrayList<Point> burstedBubles, MouseEvent e ){
        for(Point i : mouseClicks){
            pointLocation = Math.sqrt(Math.pow((i.getX() - e.getX()), 2) + Math.pow((i.getY() - e.getY()), 2));
            if(pointLocation < diameter/2){
                burstedBubles.add(new Point((int) i.getX(), (int) i.getY()));
                Bubble bubble = new Bubble(i.x, i.y, 50,"#FFFFFF", g);
            }
        }

    }
}
