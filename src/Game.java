import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;


//Game.java
/** Class contains Snake game logic and game window configuration.
 * @author Przemyslaw Bielak
 * @version 1.0
 */
public class Game extends JPanel implements ActionListener{

    /* \defgroup sizes Global window/graphics sizes
     * Define game window and graphics sizes
     * @{
     */
    /** Game window width */
    public static final int GAME_WINDOW_WIDTH = 400;
    /** Game window height */
    public static final int GAME_WINDOW_HEIGHT = 400;
    public static final int IMAGE_WIDTH = 10;
    public static final int IMAGE_HEIGHT = 10;
    /** @} */


    /** Game specific variables */
    private final int TIME_REFRESH_RATE = 100;
    private int highScore;
    private boolean executeOnceFlag = true;
    /** Sound created by: https://freesound.org/people/myfox14/sounds/382310/ */
    private final URL GAMEOVER_SOUND = (getClass().getResource("Sounds/gameover.wav"));
    /** Sound created by: https://freesound.org/people/niamhd00145229/sounds/422709/ */
    private final URL POINT_SOUND = (getClass().getResource("Sounds/point.wav"));
    private static String highscoreFilename = "";

    public static void sethighscoreFilename(String score) {
        highscoreFilename = score;
    }

    /** Create game objects */
    private Apple apple = new Apple();
    private Snake snake = new Snake();
    private Timer gameTimer;

    /** Game class constructor starts game
     */
    public Game() {

        addKeyListener(new KeyboardChecker());

        /** set widnow properties, background color and dimensions */
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT));

        /** After each gameTimer overflow, game is update and snake moved on board */
        gameTimer = new Timer(TIME_REFRESH_RATE, this);
        /** Start gameTimer */
        gameTimer.start();
    }

    @Override
    /** Update game graphics */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /** Update graphics when game is ongoing */
        if (snake.isAlive()){
            for (int i = 0; i < snake.getSnakeLen(); i++) {
                g.drawImage(snake.getSnakeImage(), snake.getxPos(i), snake.getyPos(i), this);
            }
            g.drawImage(apple.getAppleImage(), apple.getxPos(), apple.getyPos(), this);
        }
        /** Update graphics of gameover screen */
        else {
            gameOverScreen(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /** Update snake position if game is ongoing */
        if(snake.checkIfAlive()) {
            snake.move();
            checkPoints();
        }
        /** gameover screen:
         * - If highscoreFilename was selected in JFileChooser, show highscore
         *   and play gameover sound.
         * - If highscoreFilename was not selected in JFileChooser, play
         *   gameover sound.
         */
        else {
            if(executeOnceFlag) {
                if(highscoreFilename != "") {
                    try {
                        showHighScore();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

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
        /** Update graphics */
        repaint();
    }

    /** KeyboardChecker checks for keyboard presses and updates snake direction */
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

    /** checkPoints checks if snake has eaten the apple.
     * function compares snakes position with apple position.
     * If positions are the same, increment snake length and
     * play sound.
     */
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

    /** showHighScore loads highscore file.
     * If user score was bigger than the loaded highscore, it will be stored
     * in the highscore file as new highscore.
     */
    private void showHighScore() throws Exception {

        /** Open existing highscore file as a reader. */
        FileReader freader = new FileReader(highscoreFilename);
        BufferedReader breader = new BufferedReader(freader);

        /** Read content and save it to highScore. */
        String readHighScore = breader.readLine();
        highScore = Integer.parseInt(readHighScore);
        breader.close();

        /** Open existing highscore file as a writer. */
        FileWriter fwriter = new FileWriter(highscoreFilename);
        BufferedWriter bwriter = new BufferedWriter(fwriter);

        /** Check if current score is bigger than the highscore. */
        if(snake.getSnakeLen() > highScore) {
            bwriter.write(Integer.toString(snake.getSnakeLen()));
        }
        else {
            bwriter.write(readHighScore);
        }
        bwriter.close();
    }

    /** restartGame restarts game. */
    private void restartGame() {
        snake.resetSnakeState();
        executeOnceFlag = true;
    }

    /** gameOverScreen is called in paintComponent when player has lost.
     * User score, highscore (if highscore file was selected) and 'Game Over'
     * sigh will be shown.
     */
    private void gameOverScreen(Graphics g) {
        g.setFont(new Font("Invasion2000", Font.BOLD, 30));
        g.setColor(Color.red);
        g.drawString("Game Over", GAME_WINDOW_WIDTH / 2 - 100, GAME_WINDOW_HEIGHT / 2 - 80);

        String scoreString = "Score: " + snake.getSnakeLen();
        g.setFont(new Font("Invasion2000", Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString(scoreString, 20, GAME_WINDOW_HEIGHT / 2);

        if(highscoreFilename != "") {
            String highScoreString = "Highest Score: " + highScore;
            g.setFont(new Font("Invasion2000", Font.PLAIN, 20));
            g.setColor(Color.white);
            g.drawString(highScoreString, 20, GAME_WINDOW_HEIGHT / 2 + 50);
        }

        g.setFont(new Font("Invasion2000", Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString("Press Enter to play again", 20, GAME_WINDOW_HEIGHT / 2 + 100);
    }

    /** playSound plays selected .wav file.
     * @param wavPath Path to sound file.
     */
    private void playSound(URL wavPath) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        AudioInputStream audioStream = null;
        audioStream = AudioSystem.getAudioInputStream(wavPath);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }

}