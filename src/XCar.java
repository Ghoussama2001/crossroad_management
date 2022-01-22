import java.util.Random;

import javax.swing.JPanel;
import java.awt.Color;

public class XCar extends JPanel implements Runnable{
    Random random = new Random();
    float r, g, b;
    final static int Y_AXIS = 300;
    int x_axis = 715;

    public XCar() {
        super();
        r = random.nextFloat();
        g = random.nextFloat();
        b = random.nextFloat();
        Color color = new Color(r, g, b);

        this.setBackground(color);
        this.setSize(70, 59);
        this.setLocation(x_axis, Y_AXIS);
        
    }

    public void moveForward() {
        while (x_axis > - 70) {
            x_axis--;
            this.setLocation(x_axis, Y_AXIS);
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
