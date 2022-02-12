import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class YCar extends JLabel implements Runnable {

    Semaphore[] mutex_y;
    Semaphore light_y;

    Random random = new Random();
    ImageIcon Ycars[] = { new ImageIcon(XCar.class.getClassLoader().getResource("CarY1.PNG")) ,
    		new ImageIcon(XCar.class.getClassLoader().getResource("BusY.PNG")),
    		new ImageIcon(XCar.class.getClassLoader().getResource("PoliceY.PNG")),
    		new ImageIcon(XCar.class.getClassLoader().getResource("TruckY.PNG"))};
    final static int LIGHT_ACQUIRE = 195;
    final static int LIGHT_RELEASE = 481;
    final static int X_AXIS = 411;
    //int y_axis = -39;
    int y_axis = -95;

    public YCar(Semaphore light_y, Semaphore mutex_y[]) {
        super();
        this.light_y = light_y;
        this.mutex_y = mutex_y;
        this.setIcon(Ycars[random.nextInt((3)+1)]);
        this.setSize(46,93);
        this.setLocation(X_AXIS, y_axis);
        
    }
    public void moveForward() throws InterruptedException{
    	for (int i=3 ; i>=0;i--){
    		mutex_y[i].acquire();
    		if (i<3) mutex_y[i+1].release();
    		while (y_axis < LIGHT_ACQUIRE-78*i) {
              y_axis++;
              this.setLocation(X_AXIS, y_axis);
              try {
                  Thread.sleep(7);
              } catch (InterruptedException e) {}
          }
              
    	}
   }

    public void crossIntersection() {
        mutex_y[0].release();
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
        try {
            moveForward();

            light_y.acquire();
            crossIntersection();
            light_y.release();
            
            keepMoving();
        } catch (InterruptedException e) {}
    }
}
