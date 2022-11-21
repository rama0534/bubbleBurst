import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MakeBubble {
    private int count;
    private int round = 0;
    public MakeBubble(MouseEvent e, Graphics g, ArrayList<Point> mouseClicks, JLabel  statusbar, JPanel circlePanel,
                      int round){
        this.round = round;
        if(e.getX() < 20 || e.getY() < 20 || e.getX() > 700 || e.getY() > 680){
            statusbar.setText("Radius less than 25 please click somewhere else.");
            JOptionPane.showMessageDialog( circlePanel,"Click 25 away from the boarder and existing bubble(s) to make a Bubble ", "Error", JOptionPane.ERROR_MESSAGE );
        }else {
            if(mouseClicks.size() == 0){
                Bubble bubble = new Bubble(e.getX(), e.getY(), 50,"#00FF00", g);
                mouseClicks.add(new Point(e.getX(), e.getY()));
            }
            else {
                count = 1;
                for(Point i : mouseClicks){
                    if(Math.abs(e.getX()-i.getX()) > 100 + round*18 || Math.abs(e.getY()-i.getY()) >100 + round*18){
                        count = count+1;
                    }
                }
                if(count > mouseClicks.size()){
                    Bubble bubble = new Bubble(e.getX(), e.getY(), 50,"#00FF00", g);
                    mouseClicks.add(new Point(e.getX(), e.getY()));
                }
                else {
                    JOptionPane.showMessageDialog( circlePanel,"Click 50 away from the boarder and existing bubble(s) to make a Bubble ", "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }

    }
}
