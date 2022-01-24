import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

/**
 * BackGoround
 */
public class BackGround extends JFrame {

    public BackGround(String name) {
        super(name);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        this.setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("BackGround.PNG"))));
        this.getContentPane().setLayout(null);
        this.setVisible(true);
    }

}