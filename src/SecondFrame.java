import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.util.ArrayList;


public class SecondFrame extends JFrame{
    public JPanel circlePanel;
    private JLabel statusbar, statusbarBottom;
    private int numberOfBubbles;
    private int count;
    private int round = 0;
    double pointLocation;
    int diameter = 50;



    public SecondFrame(int nOfB, String gameLevel){
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

    public void Game(ArrayList<Point> mouseClicks, ArrayList<Point> burstedBubles, MouseEvent e, Graphics g){
        if(mouseClicks.size() < numberOfBubbles){
            MakeBubble makeBubble =  new MakeBubble(e,  g, mouseClicks, statusbar, circlePanel);
        }else {
            BurstBubble burstBubble = new BurstBubble( g,  mouseClicks,  burstedBubles,  e );
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
