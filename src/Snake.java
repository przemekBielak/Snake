import javax.swing.*;
import java.awt.*;

public class Snake {
    private int snakeLen;
    private int xPos[];
    private int yPos[];

    public int getSnakeLen() {
        return snakeLen;
    }

    public void setSnakeLen(int snakeLen) {
        this.snakeLen = snakeLen;
    }

    private ImageIcon snakeIcon;
    private Image snakeImage;

    public Image getSnakeImage() {
        return snakeImage;
    }

    public int getyPos(int idx) {
        return yPos[idx];
    }

    public void setyPos(int yPos, int idx) {
        this.yPos[idx] = yPos;
    }

    public void setxPos(int xPos, int idx) {
        this.xPos[idx] = xPos;
    }

    public int getxPos(int idx) {
        return xPos[idx];
    }

    public Snake() {
        snakeIcon = new ImageIcon("Images/green.png");
        snakeImage = snakeIcon.getImage();
        snakeLen = 3;
        xPos = new int[1600];
        yPos = new int[1600];

        for(int i = 0; i < snakeLen; i++) {
            xPos[i] = 140;
            yPos[i] = 140 + 10 * i;
        }
    }

}
