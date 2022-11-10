import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.util.ArrayList;


public class BubbleBurst extends JFrame{
    public JPanel circlePanel;
    private JLabel statusbar, statusbarBottom;
    private int gameLevel;
    private int count;
    private int round = 0;
    double pointLocation;
    int diameter = 50;

    public BubbleBurst(int gl){
        super("Bubble Burst");
        gameLevel = gl;
        circlePanel = new JPanel();
        circlePanel.setBackground(Color.WHITE);
        add(circlePanel, BorderLayout.CENTER);
        statusbar = new JLabel(" ");
        statusbarBottom = new JLabel(""+gameLevel);
        add(statusbar, BorderLayout.NORTH);
        add(statusbarBottom, BorderLayout.SOUTH);
        Handlerclass handler = new Handlerclass();
        circlePanel.addMouseListener(handler);
        circlePanel.addMouseMotionListener((handler));
    }

    public void MakeBubbles(MouseEvent e, Graphics g, ArrayList<Point> mouseClicks ){
        if(e.getX() < 20 || e.getY() < 20 || e.getX() > 700 || e.getY() > 680){
            statusbar.setText("Radius less than 25 please click somewhere else.");
            JOptionPane.showMessageDialog( circlePanel,"Click 25 away from the boarder and existing bubble(s) to make a Bubble ", "Error", JOptionPane.ERROR_MESSAGE );
        }else {
            if(mouseClicks.size() == 0){
                g.setColor(Color.green);
                g.drawOval( e.getX()-diameter/2, e.getY(), diameter, diameter);
                g.fillOval(e.getX()-diameter/2, e.getY(), diameter, diameter);
                mouseClicks.add(new Point(e.getX(), e.getY()));
            }
            else {
                count = 1;
                for(Point i : mouseClicks){
                    if(Math.abs(e.getX()-i.getX()) > 100 + round || Math.abs(e.getY()-i.getY()) >100 + round){
                        count = count+1;
                    }
                }
                if(count > mouseClicks.size()){
                    g.setColor(Color.green);
                    g.drawOval( e.getX()-diameter/2, e.getY(), diameter, diameter);
//                    g.drawRect(e.getX()-diameter, e.getY()-diameter/2, diameter*2, diameter*2);
                    g.fillOval(e.getX()-diameter/2, e.getY(), diameter, diameter);
                    mouseClicks.add(new Point(e.getX(), e.getY()));
                }
                else {
                    JOptionPane.showMessageDialog( circlePanel,"Click 50 away from the boarder and existing bubble(s) to make a Bubble ", "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
    }

    public void BurstBubble( Graphics g, ArrayList<Point> mouseClicks, ArrayList<Point> burstedBubles, MouseEvent e ){
        for(Point i : mouseClicks){
            pointLocation = Math.sqrt(Math.pow((i.getX() - e.getX()), 2) + Math.pow((i.getY() - e.getY()), 2));
            if(pointLocation < diameter/2){
                burstedBubles.add(new Point((int) i.getX(), (int) i.getY()));
                g.setColor(Color.WHITE);
                g.drawOval( i.x-diameter/2, i.y, diameter, diameter);
                g.fillOval(i.x-diameter/2, i.y, diameter, diameter);
            }
        }

    }

    private class Handlerclass implements MouseListener, MouseMotionListener {
        ArrayList<Point> mouseClicks = new ArrayList<>();
        ArrayList<Point> burstedBubles = new ArrayList<>();

        public void mouseClicked(MouseEvent e) {
            Graphics g = getGraphics();
            statusbar.setText(String.format("Clicked at %d, %d", e.getX(), e.getY()));
            if(gameLevel < 30){
                statusbarBottom.setText("Easy level");

                if(mouseClicks.size() < 4){
                    MakeBubbles(e, g, mouseClicks );
                }else {
                    BurstBubble(  g,  mouseClicks, burstedBubles, e );
                }
                if(burstedBubles.size() == 4){
                    JOptionPane.showMessageDialog( circlePanel,"Game Finish", "Information", JOptionPane.INFORMATION_MESSAGE );
                }


            }else if(gameLevel < 70){
                statusbarBottom.setText("Medium level");
                if(mouseClicks.size() < 5){
                    MakeBubbles(e, g, mouseClicks );
                }else {
                    statusbarBottom.setText("Mouse clicks exceeded");
                }
            }else{
                statusbarBottom.setText("Hard level");
                if(mouseClicks.size() < 6){
                    MakeBubbles(e, g, mouseClicks );
                }else {
                    statusbarBottom.setText("Mouse clicks exceeded");
                }
            }
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseDragged(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {
            statusbar.setText("Click 50 away from the boarder and existing circle(s) to make a circle");
        }
    }

}
