import javax.swing.JPanel;

import java.awt.*;

public class Game extends JPanel {

    private int GAME_WINDOW_WIDTH = 400;
    private int GAME_WINDOW_HEIGHT = 400;

    public Game() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
