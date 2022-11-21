import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class SecondFrame extends JFrame{
    public JPanel circlePanel;
    JFrame secondFrame;
    private JLabel statusbar, statusbarBottom;
    public int numberOfBubbles;
    public int timeCount = 15;
    public int round;
    ArrayList<Point> mouseClicks = new ArrayList<>();
    ArrayList<Point> burstedBubbles = new ArrayList<>();
    Timer gameTimer = new Timer();
    public SecondFrame(int nOfB, String gameLevel, int round, int timeCount){
        super("Bubble Burst");
        numberOfBubbles = nOfB;
        this.round = round;
        this.timeCount = timeCount;
        secondFrame = new JFrame();
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

    public void counting(){
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(timeCount);
                statusbar.setText(""+timeCount);

                timeCount--;
                if(timeCount == 0){
                    gameTimer.cancel();
                    statusbar.setText("");
                    JOptionPane.showMessageDialog( circlePanel,"Timeup", "Error", JOptionPane.ERROR_MESSAGE );

                }
                if(mouseClicks.size() == burstedBubbles.size()){
                    mouseClicks.clear();
                    burstedBubbles.clear();
                    System.out.println("WON");
                    boolean x = cancel();
                    System.out.println(x);
                    gameTimer.purge();
                    timeCount= 15-round;
                }
            }

        },0 , 1000);

    }
    public void Game(ArrayList<Point> mouseClicks, ArrayList<Point> burstedBubles, MouseEvent e, Graphics g){

        if(mouseClicks.size() < numberOfBubbles){
            MakeBubble makeBubble =  new MakeBubble(e,  g, mouseClicks, statusbar, circlePanel, round);
        }else {
            BurstBubble burstBubble = new BurstBubble( g,  mouseClicks,  burstedBubles,  e );
            if(mouseClicks.size() == numberOfBubbles){
                counting();
            }

        }

        if(burstedBubles.size() == numberOfBubbles){
            JOptionPane.showMessageDialog( circlePanel,"Round  "+round+" finished", "Information", JOptionPane.INFORMATION_MESSAGE );
            round++;
        }

    }

    private class Handlerclass implements MouseListener, MouseMotionListener {
        public void mouseClicked(MouseEvent e) {
            Graphics g = getGraphics();
            if(numberOfBubbles == 4){
                statusbarBottom.setText("Easy level");
                Game(mouseClicks, burstedBubbles, e, g);
            }else if(numberOfBubbles == 5){
                statusbarBottom.setText("Medium level");
                Game(mouseClicks, burstedBubbles, e, g);
            }else{
                statusbarBottom.setText("Hard level");
                Game(mouseClicks, burstedBubbles, e, g);
            }
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseDragged(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
    }

}
