package kernel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TimerTest {
    public static void main(String[] args) {
        ActionListener listener = new TimePrinter();

        Timer t = new Timer(5000, listener);
        t.start();

        JOptionPane.showMessageDialog(null, "quit program");
        System.exit(0);
    }
}


class TimePrinter  implements ActionListener{
    public void actionPerformed(ActionEvent event) {
        Date now = new Date();
        System.out.println("At the tone, the time is " + now);
        Toolkit.getDefaultToolkit().beep();
    }
}