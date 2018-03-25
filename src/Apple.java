import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Apple {
    private int xPos;
    private int yPos;

    private ImageIcon appleIcon;
    private Image appleImage;

    public Image getAppleImage() {
        return appleImage;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getxPos() {
        return xPos;
    }

    public Apple() {
        appleIcon = new ImageIcon("Images/red.png");
        appleImage = appleIcon.getImage();
        xPos = 240;
        yPos = 240;
    }

    public void setNewPos() {
        Random rand = new Random();

        int randRangex = rand.nextInt(Game.GAME_WINDOW_WIDTH - Game.IMAGE_WIDTH);
        int randRangey= rand.nextInt(Game.GAME_WINDOW_HEIGHT - Game.IMAGE_HEIGHT);

        this.xPos = randRangex / Game.IMAGE_WIDTH * Game.IMAGE_WIDTH;
        this.yPos = randRangey / Game.IMAGE_HEIGHT* Game.IMAGE_HEIGHT;
    }

}
