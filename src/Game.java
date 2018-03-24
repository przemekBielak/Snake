import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

        addKeyListener(new KeyboardChecker());

        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT));

        /* Start Timer */
        gameTimer = new Timer(TIME_REFRESH_RATE, this);
        gameTimer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int i = 0; i < snake.getSnakeLen(); i++) {
            g.drawImage(snake.getSnakeImage(), snake.getxPos(i), snake.getyPos(i), this);
        }
//        g.drawImage(snake.getSnakeImage(), snake.getxPos(0), snake.getyPos(0), this);
        g.drawImage(apple.getAppleImage(), apple.getxPos(), apple.getyPos(), this);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        repaint();
    }

    private class KeyboardChecker extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int keyPressed = e.getKeyCode();

            if (keyPressed == KeyEvent.VK_UP){
                snake.setDirection(Snake.DirectionType.DIRECTION_UP);
            }
            if (keyPressed == KeyEvent.VK_DOWN){
                snake.setDirection(Snake.DirectionType.DIRECTION_DOWN);
            }
            if (keyPressed == KeyEvent.VK_LEFT){
                snake.setDirection(Snake.DirectionType.DIRECTION_LEFT);
            }
            if (keyPressed == KeyEvent.VK_RIGHT){
                snake.setDirection(Snake.DirectionType.DIRECTION_RIGHT);
            }
            else {
                /* catch error or message that move not supported */
            }
        }
    }
}