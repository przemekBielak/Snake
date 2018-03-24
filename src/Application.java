import java.awt.*;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Application extends JFrame{

    public Application() {
        add(new Game());
        setResizable(false);
        pack();
        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Application();
            }
        });
    }

}
