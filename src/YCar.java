import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.JPanel;
import java.awt.Color;

public class YCar extends JPanel implements Runnable {
    Semaphore light_y;
    
    Random random = new Random();
    float r, g, b;
    final static int LIGHT_ACQUIRE = 195;
    final static int LIGHT_RELEASE = 485;
    final static int X_AXIS = 311;
    int y_axis = 15;

    public YCar(Semaphore light_y) {
        super();
        r = random.nextFloat();
        g = random.nextFloat();
        b = random.nextFloat();
        Color color = new Color(r, g, b);

        this.light_y = light_y;

        this.setBackground(color);
        this.setSize(59, 70);
        this.setLocation(X_AXIS, y_axis);
        
    }

    public void moveForward() {
        while (y_axis < LIGHT_ACQUIRE) {
            y_axis++;
            this.setLocation(X_AXIS, y_axis);
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                //TODO: handle exception
            }
        }
        
    }

    public void crossIntersection() {
        while (y_axis < LIGHT_RELEASE) {
            y_axis++;
            this.setLocation(X_AXIS, y_axis);
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                //TODO: handle exception
            }
        }
        
    }

    public void keepMoving() {
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
        try {
            light_y.acquire();
            crossIntersection();
            light_y.release();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        keepMoving();
        //this.destroy if exists
    }
}
