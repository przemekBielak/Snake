import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener{

    private int GAME_WINDOW_WIDTH = 400;
    private int GAME_WINDOW_HEIGHT = 400;
    private int IMAGE_WIDTH = 10;
    private int IMAGE_HEIGHT = 10;
    private int TIME_REFRESH_RATE = 100;

    private ImageIcon snakeBodyIcon = new ImageIcon("Images/green.png");
    private ImageIcon appleIcon = new ImageIcon("Images/red.png");
    private Image snakeBodyImage = snakeBodyIcon.getImage();
    private Image appleImage = appleIcon.getImage();

    int applexPos = 0;
    int appleyPos = 0;

    private Timer gameTimer;

    public Game() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT));

        /* Start Timer */
        gameTimer = new Timer(TIME_REFRESH_RATE, this);
        gameTimer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(snakeBodyImage, 10, 10, this);
        g.drawImage(snakeBodyImage, 20, 20, this);
        g.drawImage(snakeBodyImage, 10, 20, this);
        g.drawImage(appleImage, applexPos, appleyPos, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        applexPos += 10;
        appleyPos += 10;
        repaint();
    }
}