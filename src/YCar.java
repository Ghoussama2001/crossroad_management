import java.util.Random;

import javax.swing.JPanel;
import java.awt.Color;

public class YCar extends JPanel implements Runnable {
    Random random = new Random();
    float r, g, b;
    final static int X_AXIS = 311;
    int y_axis = 15;

    public YCar() {
        super();
        r = random.nextFloat();
        g = random.nextFloat();
        b = random.nextFloat();
        Color color = new Color(r, g, b);

        this.setBackground(color);
        this.setSize(59, 70);
        this.setLocation(X_AXIS, y_axis);
        
    }

    public void moveForward() {
        while (y_axis < 762) {
            y_axis++;
            this.setLocation(X_AXIS, y_axis);
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                //TODO: handle exception
            }
        }
        
    }

    public void run() {
        moveForward();
        //this.destroy if exists
    }
}
