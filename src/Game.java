import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/*
    TODO:
    [V] finish game logic
    [V] show highest score
    [X] configurable size of the map - add second window with setting and PLAY button
    [V] show score on the screen
    [V] add sound and pulsing sign when lost "Game Over"
 */

public class Game extends JPanel implements ActionListener{

    public static final int GAME_WINDOW_WIDTH = 400;
    public static final int GAME_WINDOW_HEIGHT = 400;
    public static final int IMAGE_WIDTH = 10;
    public static final int IMAGE_HEIGHT = 10;
    private int TIME_REFRESH_RATE = 100;

    // "./src/highscore.txt"

    public static String HIGHSCORE_FILENAME = "";

    public static void setHIGHSCORE_FILENAME(String score) {
        HIGHSCORE_FILENAME = score;
    }


    /* https://freesound.org/people/myfox14/sounds/382310/ */
    final URL GAMEOVER_SOUND = (getClass().getResource("Sounds/gameover.wav"));

    /* https://freesound.org/people/niamhd00145229/sounds/422709/ */
    final URL POINT_SOUND = (getClass().getResource("Sounds/point.wav"));

    private int highScore;
    private boolean executeOnceFlag = true;

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

        if (snake.isAlive()){
            for (int i = 0; i < snake.getSnakeLen(); i++) {
                g.drawImage(snake.getSnakeImage(), snake.getxPos(i), snake.getyPos(i), this);
            }
            g.drawImage(apple.getAppleImage(), apple.getxPos(), apple.getyPos(), this);
        }
        else {
            gameOverScreen(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(snake.checkIfAlive()) {
            snake.move();
            checkPoints();
        }
        else {
            if(executeOnceFlag) {
                try {
                    showHighScore();
                } catch (Exception e1) {
                    e1.printStackTrace();
                };

                try {
                    playSound(GAMEOVER_SOUND);
                } catch (LineUnavailableException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedAudioFileException e1) {
                    e1.printStackTrace();
                }


                executeOnceFlag = false;
            }
        }

        repaint();
    }

    private class KeyboardChecker extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int keyPressed = e.getKeyCode();

            if (keyPressed == KeyEvent.VK_UP){
                if(snake.checkIfMovePossible()) {
                    snake.setDirection(Snake.DirectionType.DIRECTION_UP);
                }
            }
            if (keyPressed == KeyEvent.VK_DOWN){
                if(snake.checkIfMovePossible()) {
                    snake.setDirection(Snake.DirectionType.DIRECTION_DOWN);
                }
            }
            if (keyPressed == KeyEvent.VK_LEFT){
                if(snake.checkIfMovePossible()) {
                    snake.setDirection(Snake.DirectionType.DIRECTION_LEFT);
                }
            }
            if (keyPressed == KeyEvent.VK_RIGHT){
                if(snake.checkIfMovePossible()) {
                    snake.setDirection(Snake.DirectionType.DIRECTION_RIGHT);
                }
            }
            else if(keyPressed == KeyEvent.VK_ENTER) {
                if(!snake.isAlive()) {
                    restartGame();
                }
            }
            else {
                /* catch error or message that move not supported */
            }
        }
    }

    private void checkPoints() {
        if( (snake.getxPos(Snake.HEAD_POS) == apple.getxPos()) && (snake.getyPos(Snake.HEAD_POS) == apple.getyPos()) ) {
            snake.setSnakeLen(snake.getSnakeLen() + 1);
            apple.setNewPos();
            try {
                playSound(POINT_SOUND);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
    }

    private void showHighScore() throws Exception {

        FileReader freader = new FileReader(HIGHSCORE_FILENAME);
        BufferedReader breader = new BufferedReader(freader);

        String readHighScore = breader.readLine();
        highScore = Integer.parseInt(readHighScore);
        breader.close();

        FileWriter fwriter = new FileWriter(HIGHSCORE_FILENAME);
        BufferedWriter bwriter = new BufferedWriter(fwriter);

        if(snake.getSnakeLen() > highScore) {
            bwriter.write(Integer.toString(snake.getSnakeLen()));
        }
        else {
            bwriter.write(readHighScore);
        }
        bwriter.close();
    }

    private void restartGame() {
        snake.resetSnakeState();
        executeOnceFlag = true;
    }

    private void gameOverScreen(Graphics g) {
        g.setFont(new Font("Invasion2000", Font.BOLD, 30));
        g.setColor(Color.red);
        g.drawString("Game Over", GAME_WINDOW_WIDTH / 2 - 100, GAME_WINDOW_HEIGHT / 2 - 80);

        String scoreString = "Score: " + snake.getSnakeLen();
        g.setFont(new Font("Invasion2000", Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString(scoreString, 20, GAME_WINDOW_HEIGHT / 2);

        String highScoreString = "Highest Score: " + highScore;
        g.setFont(new Font("Invasion2000", Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString(highScoreString, 20, GAME_WINDOW_HEIGHT / 2 + 50);

        g.setFont(new Font("Invasion2000", Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString("Press Enter to play again", 20, GAME_WINDOW_HEIGHT / 2 + 100);
    }

    private void playSound(URL wavPath) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        AudioInputStream audioStream = null;
        audioStream = AudioSystem.getAudioInputStream(wavPath);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }

}