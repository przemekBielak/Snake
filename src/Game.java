import javax.swing.*;

import java.awt.*;

public class Game extends JPanel {

    private int GAME_WINDOW_WIDTH = 400;
    private int GAME_WINDOW_HEIGHT = 400;


    private ImageIcon snakeBodyIcon = new ImageIcon("Images/green.png");
    private ImageIcon appleIcon = new ImageIcon("Images/red.png");
    private Image snakeBodyImage = snakeBodyIcon.getImage();
    private Image appleImage = appleIcon.getImage();

    public Game() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(snakeBodyImage, 10, 10, this);
        g.drawImage(snakeBodyImage, 20, 20, this);
        g.drawImage(snakeBodyImage, 10, 20, this);
        g.drawImage(appleImage, 100, 100, this);
    }
}