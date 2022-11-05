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
    int radius = 50;



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
        if(e.getX() < 50 || e.getY() < 50 || e.getX() > 700 || e.getY() > 680){
            statusbar.setText("Radius less than 50 please click somewhere else.");
            JOptionPane.showMessageDialog( circlePanel,"Click 50 away from the boarder and existing bubble(s) to make a Bubble ", "Error", JOptionPane.ERROR_MESSAGE );
        }else {
            if(mouseClicks.size() == 0){
                g.setColor(Color.green);
                g.drawOval( e.getX(), e.getY(), 100, 100);
                mouseClicks.add(new Point(e.getX(), e.getY()));

            }
            else {
                count = 1;
                for(Point i : mouseClicks){

                    if(Math.abs(e.getX()-i.getX()) > 200 + round || Math.abs(e.getY()-i.getY()) >200 + round){
                        System.out.println("Math logic is true");
                        count = count+1;
                        System.out.println(count);
                    }
                }
                if(count > mouseClicks.size()){
                    System.out.println("Size is ,......"+mouseClicks.size());
                    g.setColor(Color.green);
                    g.drawOval( e.getX(), e.getY(), 100, 100);
                    mouseClicks.add(new Point(e.getX(), e.getY()));
                }
                else {
                    JOptionPane.showMessageDialog( circlePanel,"Click 50 away from the boarder and existing bubble(s) to make a Bubble ", "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
        for(Point i: mouseClicks){
            System.out.println("X....."+i.getX()+".....Y......."+i.getY());
        }

    }




    private class Handlerclass implements MouseListener, MouseMotionListener {

        ArrayList<Point> mouseClicks = new ArrayList<>();
        public void mouseClicked(MouseEvent e) {
            Graphics g = getGraphics();
            statusbar.setText(String.format("Clicked at %d, %d", e.getX(), e.getY()));
            if(gameLevel < 30){
                statusbarBottom.setText("Easy level");
                if(mouseClicks.size() < 4){
                    MakeBubbles(e, g, mouseClicks );
                }else {
                    statusbarBottom.setText("Mouse clicks exceeded");
                    for(Point i : mouseClicks){
                        System.out.println("testing");
                        pointLocation = Math.sqrt(Math.pow((i.getX() - e.getX()), 2) + Math.pow((i.getY() - e.getY()), 2));
                        if(pointLocation < radius){
                            System.out.println("testing");
                            System.out.println("clicked inside the bubble"+i.getX()+"......."+i.getY());
                        }
                    }
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
