import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String args[]){
        InitialFrame IF = new InitialFrame();
        IF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        IF.setSize(800, 800);
        IF.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
        IF.setVisible(true);
    }
}

