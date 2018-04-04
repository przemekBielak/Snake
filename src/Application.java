import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/** Application class is responsible for managing all windows. */
public class Application extends JFrame{

    /** Application class constructor.
     * Creates window and calls Game class constructor.
     */
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

                JFrame frame = new JFrame();
                JPanel panel = new JPanel();

                frame.setTitle("Snake Game");
                frame.setSize(400, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                /* Start game button. */
                JButton button = new JButton("Start Game");
                button.addActionListener(new ActionListener() {
                    @Override
                    /** Hide current window and show Application window. */
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        JFrame gameFrame = new Application();
                    }
                });

                /* Open file chooser for highscore file. */
                JButton chooseButton = new JButton("Choose Highscore File");
                chooseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser chooser = new JFileChooser();
                        int returnVal = chooser.showOpenDialog(null);
                        if(returnVal == JFileChooser.APPROVE_OPTION) {
                            Game.sethighscoreFilename(chooser.getSelectedFile().getAbsolutePath());
                        }
                    }
                });

                /* Add all elements to the frame */
                frame.add(panel);
                panel.add(button);
                panel.add(chooseButton);
            }
        });
    }

}
