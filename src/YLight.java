import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class YLight extends JLabel {
    final static ImageIcon y_green_light = new ImageIcon(XLight.class.getClassLoader().getResource("Y_GreenLight2.PNG"));
    final static ImageIcon y_red_light = new ImageIcon(XLight.class.getClassLoader().getResource("Y_RedLight2.PNG.png"));
    final static int Y_LIGHT_X = 346;
    final static int Y_LIGHT_Y = 175;

    boolean currentLight = false;

    public YLight() {
        super(y_red_light);
        
        this.setSize(30, 90);
        this.setLocation(Y_LIGHT_X, Y_LIGHT_Y);
    }

    public void toggleLight() {
        if (!this.currentLight) {
            this.setIcon(y_green_light);
            this.currentLight = true;
        } else {
            this.setIcon(y_red_light);
            this.currentLight = false;
        }
    }
}
