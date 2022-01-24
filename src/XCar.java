import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.JPanel;
import java.awt.Color;

public class XCar extends JPanel implements Runnable{
    
    Semaphore[] mutex_x;
    Semaphore light_x;

    Random random = new Random();
    float r, g, b;
    final static int LIGHT_ACQUIRE = 606;
    final static int LIGHT_RELEASE = 320;
    final static int Y_AXIS = 300;
    //int x_axis = 918;
    int x_axis = 985;

    public XCar(Semaphore light_x, Semaphore[] mutex_x) {
        super();
        r = random.nextFloat();
        g = random.nextFloat();
        b = random.nextFloat();
        Color color = new Color(r, g, b);

        this.light_x = light_x;
        this.mutex_x = mutex_x;

        this.setBackground(color);
        this.setSize(70, 59);
        this.setLocation(x_axis, Y_AXIS);
        
    }

    public void moveForward() {
        try {
            mutex_x[4].acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
        }
        for (int i = 4; i > 0; i--) {
            while (x_axis > LIGHT_ACQUIRE+78*i) {
                x_axis--;
                this.setLocation(x_axis, Y_AXIS);
                try {
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    //TODO: handle exception
                }
            }
            try {
                mutex_x[i-1].acquire();
                mutex_x[i].release();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
            }
        }
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
        mutex_x[0].release();
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
