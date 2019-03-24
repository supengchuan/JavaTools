package ThreadTest;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallComponent extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<Ball>();

    public void add(Ball b) {
        balls.add(b);
    }

    public void paintComponet(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(Ball b : balls) {
            g2.fill(b.getShap());
        }
    }
}

