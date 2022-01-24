import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Semaphore[] mutex_x = new Semaphore[5];
        for (int i = 0; i < mutex_x.length; i++) {
            mutex_x[i] = new Semaphore(1);
        }
        Semaphore[] mutex_y = new Semaphore[4];
        for (int i = 0; i < mutex_y.length; i++) {
            mutex_y[i] = new Semaphore(1);
        }

        Semaphore light_x = new Semaphore(1);
        Semaphore light_y = new Semaphore(0);

        XLight xlight = new XLight();
        YLight ylight = new YLight();
        XCar xcar;
        YCar ycar;

        BackGround crossRoad = new BackGround("Carrefour");
        
        crossRoad.add(xlight);
        crossRoad.add(ylight);

        Traffic traffic = new Traffic(xlight, ylight, light_x, light_y);
        traffic.start();

        boolean flag = true;
        while (flag) {
            xcar = new XCar(light_x, mutex_x);
            crossRoad.add(xcar);
            new Thread(xcar).start();
            try {
                Thread.sleep((long)(Math.random()*4000)+1);
            } catch (InterruptedException e) {
                //TODO: handle exception
            }

            ycar = new YCar(light_y, mutex_y);
            crossRoad.add(ycar);
            new Thread(ycar).start();
            try {
                Thread.sleep((long)(Math.random()*4000)+1);
            } catch (InterruptedException e) {
                //TODO: handle exception
            }
        }

    }
}