import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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

                JFrame frame = new JFrame();
                JPanel panel = new JPanel();

                frame.setSize(400, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JButton button = new JButton("Start Game");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        JFrame gameFrame = new Application();
                    }
                });

                JButton chooseButton = new JButton("Choose Highscore File");
                chooseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser chooser = new JFileChooser();
                        int returnVal = chooser.showOpenDialog(null);
                        if(returnVal == JFileChooser.APPROVE_OPTION) {
                            Game.HIGHSCORE_FILENAME = (chooser.getSelectedFile().getAbsolutePath());
//                            System.out.println(chooser.getSelectedFile().getName());
                        }
                    }
                });

                frame.add(panel);
                panel.add(button);
                panel.add(chooseButton);
            }
        });
    }

}
