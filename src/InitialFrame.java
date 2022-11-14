import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class InitialFrame extends JFrame{
    private JPanel panel_01;
    private JLabel easy;
    private JLabel medium;
    private JLabel hard;
    private JButton start, restart;
    private JSlider level;
    private String gameLevel = "";
    private int numberOfBubbles;
    GridBagConstraints c = new GridBagConstraints();



    public InitialFrame() {
        super("Bubble Burst");
        panel_01 = new JPanel();
        panel_01.setLayout(new GridBagLayout());
        panel_01.setBackground(Color.WHITE);
        add(panel_01);

        easy = new JLabel("Easy");
        medium = new JLabel("Medium");
        hard = new JLabel("Hard");
        start = new JButton("Start");
        restart = new JButton("Restart");
        level = new JSlider(JSlider.HORIZONTAL,0,100,10);

        //https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
        //Row one
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        panel_01.add(start, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        panel_01.add(restart, c);

        //Row two
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;

        c.gridx = 0;
        c.gridy = 1;
        panel_01.add(easy, c);

        c.gridx = 1;
        c.gridy = 1;
        panel_01.add(medium, c);

        c.gridx = 2;
        c.gridy = 1;
        panel_01.add(hard, c);


        //Row three
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        panel_01.add(level, c);




        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                levelFinder();
                JOptionPane.showMessageDialog( start,gameLevel+ " level selected please make  "+numberOfBubbles+" bubbles by Click 25 away from the boarder and existing bubble(s) to make a Bubble", "Information", JOptionPane.INFORMATION_MESSAGE );
                SecondFrame bb = new SecondFrame(numberOfBubbles, gameLevel);
                bb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                bb.setSize(800, 800);
                bb.setVisible(true);
            }
        });


        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                levelFinder();
                JOptionPane.showMessageDialog( restart,gameLevel+ " level selected please make  "+numberOfBubbles+" bubbles by Click 25 away from the boarder and existing bubble(s) to make a Bubble", "Information", JOptionPane.INFORMATION_MESSAGE );
                SecondFrame bb = new SecondFrame(numberOfBubbles, gameLevel);
                bb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                bb.setSize(800, 800);
                bb.setVisible(true);
            }
        });

        level.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            }
        });


    }
    public void levelFinder(){
        if(level.getValue() < 30){
            gameLevel = "Easy";
            numberOfBubbles = 4;
        }else if(level.getValue() < 70){
            gameLevel = "Medium";
            numberOfBubbles = 5;
        }else{
            gameLevel = "Hard";
            numberOfBubbles = 6;
        }
    }
}
