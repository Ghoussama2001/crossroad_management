import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.LayoutManager;

public class XCar extends JLabel implements Runnable{

	Semaphore[] mutex_x;
    Semaphore light_x;

    Random random = new Random();
    ImageIcon Xcars[] = { new ImageIcon(XCar.class.getClassLoader().getResource("TruckX.PNG")) ,
    		new ImageIcon(XCar.class.getClassLoader().getResource("BusX.PNG")),
    		new ImageIcon(XCar.class.getClassLoader().getResource("CarX.PNG")),
    		new ImageIcon(XCar.class.getClassLoader().getResource("PoliceX.PNG"))};
    final static int LIGHT_ACQUIRE = 606;
    final static int LIGHT_RELEASE = 320;
    final static int Y_AXIS = 300;
    //int x_axis = 918;
    int x_axis = 985 ;

    public XCar(Semaphore light_x, Semaphore[] mutex_x) {
    	super();
        this.light_x = light_x;
        this.mutex_x = mutex_x;
        this.setIcon(Xcars[random.nextInt((3)+1)]);
        this.setSize(93, 46);
        this.setLocation(x_axis, Y_AXIS);
        
    }
    /*improved move Forward method */
      public void moveForward() throws InterruptedException{
      	for (int i=4 ; i>=0;i--){
      		mutex_x[i].acquire();
      		if (i<4) mutex_x[i+1].release();
      		while (x_axis > LIGHT_ACQUIRE+93*i) {
                x_axis--;
                this.setLocation(x_axis, Y_AXIS);
                try {
                    Thread.sleep(7);
                } catch (InterruptedException e) {}
            }
                
      	}
     }
     
    /*public void moveForward() {
        try {
            mutex_x[4].acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
        }
        for (int i = 4; i > 0; i--) {
            while (x_axis > LIGHT_ACQUIRE+95*i) {
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
    }*/

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
        while (x_axis > -100) {
            x_axis--;
            this.setLocation(x_axis, Y_AXIS);
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {}
        }
    }


    public void run() {
        try {
			moveForward();
			light_x.acquire();
            crossIntersection();
            light_x.release();
            keepMoving();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
