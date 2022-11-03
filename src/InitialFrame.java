import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class InitialFrame extends JFrame{
    private JPanel initialPanel;
    private JLabel easy;
    private JLabel medium;
    private JLabel hard;
    private JButton start, restart;
    private JSlider level;
    GridBagConstraints c = new GridBagConstraints();



    public InitialFrame(){
        super("Bubble Burst");

        initialPanel = new JPanel();
        initialPanel.setLayout(new GridBagLayout());
        initialPanel.setBackground(Color.WHITE);
        add(initialPanel);

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
        initialPanel.add(start, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        initialPanel.add(restart, c);

        //Row two
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;

        c.gridx = 0;
        c.gridy = 1;
        initialPanel.add(easy, c);

        c.gridx = 1;
        c.gridy = 1;
        initialPanel.add(medium, c);

        c.gridx = 2;
        c.gridy = 1;
        initialPanel.add(hard, c);


        //Row three
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        initialPanel.add(level, c);


        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BubbleBurst bb = new BubbleBurst();
                bb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                bb.setSize(800, 800);
                bb.setVisible(true);
            }
        });

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        level.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            }
        });


    }



}
