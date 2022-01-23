import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class XLight extends JLabel {
    final static ImageIcon x_green_light = new ImageIcon(XLight.class.getClassLoader().getResource("X_GreenLight.PNG"));
    final static ImageIcon x_red_light = new ImageIcon(XLight.class.getClassLoader().getResource("X_RedLight.PNG"));
    final static int X_LIGHT_X = 510;
    final static int X_LIGHT_Y = 235;

    boolean currentLight = true;

    public XLight() {
        super(x_green_light);

        this.setSize(90, 30);
        this.setLocation(X_LIGHT_X, X_LIGHT_Y);
    }

    public void toggleLight() {
        if (this.currentLight) {
            this.setIcon(x_red_light);
            this.currentLight = false;
        } else {
            this.setIcon(x_green_light);
            this.currentLight = true;
        }
    }
}
