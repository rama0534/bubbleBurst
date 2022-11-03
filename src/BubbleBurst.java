import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;



public class BubbleBurst extends JFrame{
    private JPanel circlePanel;
    private JLabel statusbar, statusbarBottom;



    public BubbleBurst(){
        super("Bubble Burst");

        circlePanel = new JPanel();
        circlePanel.setBackground(Color.WHITE);
        add(circlePanel, BorderLayout.CENTER);

        statusbar = new JLabel(" ");
        statusbarBottom = new JLabel("Once you draw a circle next mouse click will draw a line if you want new circle restart the application");
        add(statusbar, BorderLayout.NORTH);
        add(statusbarBottom, BorderLayout.SOUTH);

        Handlerclass handler = new Handlerclass();
        circlePanel.addMouseListener(handler);
        circlePanel.addMouseMotionListener((handler));
    }


    private class Handlerclass implements MouseListener, MouseMotionListener {
        //        private int circle_x, circle_y;
        Point[] mouseClicks = new Point[500];
        int count = 0;
        public void mouseClicked(MouseEvent e) {
            Graphics g = getGraphics();
            statusbar.setText(String.format("Clicked at %d, %d", e.getX(), e.getY()));

            if(e.getX() < 50 || e.getY() < 50 || e.getX() > 750 || e.getY() > 750){
                statusbar.setText("Radius less than 50 please click somewhere else.");
            }else {
                mouseClicks[count] = new Point(e.getX(), e.getY());
                g.setColor(Color.green);
                g.drawOval( e.getX(), e.getY(), 100, 100);
                mouseClicks[count++] = new Point(e.getX(), e.getY());
//                System.out.println("Circle x:"+mouseClicks[0].x + " circle Y:"+mouseClicks[0].y);
//                System.out.println("lane ..."+ mouseClicks[1].x+"...."+ mouseClicks[1].y );
            }

        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseDragged(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {
            statusbar.setText("Click 50 away from the boarder to make a circle");
        }
    }

}
