import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.JPanel;
import java.awt.Color;

public class XCar extends JPanel implements Runnable{
    Semaphore light_x;

    Random random = new Random();
    float r, g, b;
    final static int LIGHT_ACQUIRE = 510;
    final static int LIGHT_RELEASE = 220;
    final static int Y_AXIS = 300;
    int x_axis = 715;

    public XCar(Semaphore light_x) {
        super();
        r = random.nextFloat();
        g = random.nextFloat();
        b = random.nextFloat();
        Color color = new Color(r, g, b);

        this.light_x = light_x;

        this.setBackground(color);
        this.setSize(70, 59);
        this.setLocation(x_axis, Y_AXIS);
        
    }

    public void moveForward() {
        while (x_axis > LIGHT_ACQUIRE) {
            x_axis--;
            this.setLocation(x_axis, Y_AXIS);
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                //TODO: handle exception
            }
        }
        
    }

    public void crossIntersection() {
        while (x_axis > LIGHT_RELEASE) {
            x_axis--;
            this.setLocation(x_axis, Y_AXIS);
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                //TODO: handle exception
            }
        }
    }

    public void keepMoving() {
        while (x_axis > -70) {
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
        try {
            light_x.acquire();
            crossIntersection();
            light_x.release();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        keepMoving();
        //this.destroy if exists
    }
}
