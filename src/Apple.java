import javax.swing.*;
import java.awt.*;
import java.util.Random;

/* Apple class is a template for apple object on game map. */
public class Apple {
    private int xPos;
    private int yPos;

    /** Stores Icom */
    private ImageIcon appleIcon;
    /** Stores Image */
    private Image appleImage;

    /** appleImage getter. */
    public Image getAppleImage() {
        return appleImage;
    }

    /** yPos getter. */
    public int getyPos() {
        return yPos;
    }

    /** yPos setter. */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /** xPos setter. */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /** xPos getter. */
    public int getxPos() {
        return xPos;
    }

    /**
     * Apple class constructor.
     * Initializes apple image and places apple on a screen in default position.
     */
    public Apple() {
        appleIcon = new ImageIcon(this.getClass().getResource("Images/red.png"));
        appleImage = appleIcon.getImage();
        xPos = 240;
        yPos = 240;
    }

    /** Randomly selects new position for an apple. */
    public void setNewPos() {
        Random rand = new Random();

        int randRangex = rand.nextInt(Game.GAME_WINDOW_WIDTH - Game.IMAGE_WIDTH);
        int randRangey= rand.nextInt(Game.GAME_WINDOW_HEIGHT - Game.IMAGE_HEIGHT);

        this.xPos = randRangex / Game.IMAGE_WIDTH * Game.IMAGE_WIDTH;
        this.yPos = randRangey / Game.IMAGE_HEIGHT* Game.IMAGE_HEIGHT;
    }

}
