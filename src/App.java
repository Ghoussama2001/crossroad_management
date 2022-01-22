

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        XCar xcar;
        YCar ycar;
        BackGround backGround = new BackGround("Carrefour");

        while (true) {
            xcar = new XCar();
            backGround.add(xcar);
            new Thread(xcar).start();
            try {
                Thread.sleep((long)(Math.random()*3000)+1);
            } catch (InterruptedException e) {
                //TODO: handle exception
            }

            ycar = new YCar();
            backGround.add(ycar);
            new Thread(ycar).start();
            try {
                Thread.sleep((long)(Math.random()*3000)+1);
            } catch (InterruptedException e) {
                //TODO: handle exception
            }
        }

    }
}