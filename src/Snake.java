import javax.swing.*;
import java.awt.*;

public class Snake {
    private int xPos;
    private int yPos;

    private ImageIcon snakeIcon;
    private Image snakeImage;

    public Image getSnakeImage() {
        return snakeImage;
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

    public Snake() {
        snakeIcon = new ImageIcon("Images/green.png");
        snakeImage = snakeIcon.getImage();
    }

}
