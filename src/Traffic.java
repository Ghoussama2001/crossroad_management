import java.util.concurrent.Semaphore;

public class Traffic extends Thread{
    Semaphore light_x;
    Semaphore light_y;
    XLight xlight;
    YLight ylight;
    char greenLight = 'x';

    public Traffic(XLight xlight, YLight ylight, Semaphore light_x, Semaphore light_y) {
        this.xlight = xlight;
        this.ylight = ylight;
        this.light_x = light_x;
        this.light_y = light_y;
    }

    public void run() {
        while (true) {
            try {
                sleep(6000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
            }

            if (greenLight == 'x') {
                try {
                    light_x.acquire();
                    light_y.release();

                    xlight.toggleLight();
                    ylight.toggleLight();

                    greenLight = 'y';
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                try {
                    light_y.acquire();
                    light_x.release();

                    ylight.toggleLight();
                    xlight.toggleLight();
    
                    greenLight = 'x';
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }
}
