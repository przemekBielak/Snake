import javax.swing.*;
import java.awt.*;

public class Snake {

    public enum DirectionType {
            DIRECTION_UP
        ,   DIRECTION_DOWN
        ,   DIRECTION_LEFT
        ,   DIRECTION_RIGHT
    }

    public static final int HEAD_POS = 0;

    private ImageIcon snakeIcon;
    private Image snakeImage;

    private int xPos[];
    private int yPos[];
    private int snakeLen;
    private boolean isAlive;
    private DirectionType direction;

    public DirectionType getDirection() {
        return direction;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }

    public int getSnakeLen() {
        return snakeLen;
    }

    public void setSnakeLen(int snakeLen) {
        this.snakeLen = snakeLen;
    }

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

    public boolean isAlive() {
        return isAlive;
    }

    public Snake() {
        snakeIcon = new ImageIcon("Images/green.png");
        snakeImage = snakeIcon.getImage();
        snakeLen = 3;
        isAlive = true;
        direction = DirectionType.DIRECTION_UP;
        xPos = new int[1600];
        yPos = new int[1600];

        for(int i = 0; i < snakeLen; i++) {
            xPos[i] = 140;
            yPos[i] = 140 + 10 * i;
        }
    }

    public void move() {
        /* Move body of the snake */
        for(int i = snakeLen; i > 0; i--) {
            xPos[i] = xPos[i - 1];
            yPos[i] = yPos[i - 1];
        }

        /* Move head of the snake */
        switch (direction) {
            case DIRECTION_UP:
                yPos[HEAD_POS] -= Game.IMAGE_HEIGHT;
                break;
            case DIRECTION_DOWN:
                yPos[HEAD_POS] += Game.IMAGE_HEIGHT;
                break;
            case DIRECTION_LEFT:
                xPos[HEAD_POS] -= Game.IMAGE_WIDTH;
                break;
            case DIRECTION_RIGHT:
                xPos[HEAD_POS] += Game.IMAGE_WIDTH;
                break;

        }
    }

    public boolean checkIfMovePossible() {

        boolean retVal = false;

            if(direction == DirectionType.DIRECTION_UP) {
                if (direction == DirectionType.DIRECTION_DOWN) {
                    retVal = false;
                } else {
                    retVal = true;
                }
            }
            if(direction == DirectionType.DIRECTION_DOWN) {
                if (direction == DirectionType.DIRECTION_UP) {
                    retVal = false;
                } else {
                    retVal = true;
                }
            }
            if(direction == DirectionType.DIRECTION_LEFT) {
                if (direction == DirectionType.DIRECTION_RIGHT) {
                    retVal = false;
                } else {
                    retVal = true;
                }
            }
            if(direction == DirectionType.DIRECTION_RIGHT) {
                if (direction == DirectionType.DIRECTION_LEFT) {
                    retVal = false;
                } else {
                    retVal = true;
                }
            }

        return retVal;
    }

    public boolean checkIfAlive() {
        for(int i = 1; i < snakeLen; i++) {
            if( (xPos[HEAD_POS] == xPos[i]) && (yPos[HEAD_POS] == yPos[i]) ) {
                isAlive = false;
            }
        }

        if( (xPos[HEAD_POS] < 0) ||
            (xPos[HEAD_POS] > Game.GAME_WINDOW_WIDTH - Game.IMAGE_WIDTH) ||
            (yPos[HEAD_POS] < 0) ||
            (yPos[HEAD_POS] > Game.GAME_WINDOW_HEIGHT - Game.IMAGE_HEIGHT) ) {
            isAlive = false;
        }

        return isAlive;
    }



}
