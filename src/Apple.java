import javax.swing.*;
import java.awt.*;

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

}
