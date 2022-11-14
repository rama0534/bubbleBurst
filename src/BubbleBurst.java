import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.util.ArrayList;


public class BubbleBurst extends JFrame{
    public JPanel circlePanel;
    private JLabel statusbar, statusbarBottom;
    private int numberOfBubbles;
    private int count;
    private int round = 0;
    double pointLocation;
    int diameter = 50;



    public BubbleBurst(int nOfB, String gameLevel){
        super("Bubble Burst");
        numberOfBubbles = nOfB;
        circlePanel = new JPanel();
        circlePanel.setBackground(Color.WHITE);
        add(circlePanel, BorderLayout.CENTER);
        statusbar = new JLabel(" ");
        statusbarBottom = new JLabel(""+ numberOfBubbles);
        add(statusbar, BorderLayout.NORTH);
        add(statusbarBottom, BorderLayout.SOUTH);
        Handlerclass handler = new Handlerclass();
        circlePanel.addMouseListener(handler);
        circlePanel.addMouseMotionListener((handler));
    }

    public void MakeBubbles(MouseEvent e, Graphics g, ArrayList<Point> mouseClicks, int numberOfBubbles ){
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
                    if(Math.abs(e.getX()-i.getX()) > 100 + round || Math.abs(e.getY()-i.getY()) >100 + round){
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

    public void BurstBubble( Graphics g, ArrayList<Point> mouseClicks, ArrayList<Point> burstedBubles, MouseEvent e ){
        for(Point i : mouseClicks){
            pointLocation = Math.sqrt(Math.pow((i.getX() - e.getX()), 2) + Math.pow((i.getY() - e.getY()), 2));
            if(pointLocation < diameter/2){
                burstedBubles.add(new Point((int) i.getX(), (int) i.getY()));
                Bubble bubble = new Bubble(i.x, i.y, 50,"#FFFFFF", g);
            }
        }

    }
    public void Game(ArrayList<Point> mouseClicks, ArrayList<Point> burstedBubles, MouseEvent e, Graphics g){
        if(mouseClicks.size() < numberOfBubbles){
            MakeBubbles(e, g, mouseClicks, numberOfBubbles );
        }else {
            BurstBubble(  g,  mouseClicks, burstedBubles, e );
        }
        if(burstedBubles.size() == numberOfBubbles){
            JOptionPane.showMessageDialog( circlePanel,"Game Finish", "Information", JOptionPane.INFORMATION_MESSAGE );
        }

    }

    private class Handlerclass implements MouseListener, MouseMotionListener {
        ArrayList<Point> mouseClicks = new ArrayList<>();

        ArrayList<Point> burstBubbles = new ArrayList<>();

        public void mouseClicked(MouseEvent e) {
            Graphics g = getGraphics();
            statusbar.setText(String.format("Clicked at %d, %d", e.getX(), e.getY()));
            if(numberOfBubbles == 4){
                statusbarBottom.setText("Easy level");
                Game(mouseClicks, burstBubbles, e, g);
            }else if(numberOfBubbles == 5){
                statusbarBottom.setText("Medium level");
                Game(mouseClicks, burstBubbles, e, g);
            }else{
                statusbarBottom.setText("Hard level");
                Game(mouseClicks, burstBubbles, e, g);
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
