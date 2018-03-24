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

    private Apple apple = new Apple();
    private Snake snake = new Snake();
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

        g.drawImage(snake.getSnakeImage(), snake.getxPos(), snake.getyPos(), this);
        g.drawImage(apple.getAppleImage(), apple.getxPos(), apple.getyPos(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        apple.setxPos(apple.getxPos() + 10);
        apple.setyPos(apple.getyPos() + 10);
        repaint();
    }
}