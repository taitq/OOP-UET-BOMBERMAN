package GameEntites;

import javafx.scene.Group;
import javafx.scene.control.skin.TextInputControlSkin;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

/**
 * Đối tượng quái.
 */
public abstract class Enemy extends MoveAnimation {
    protected Image[] enemy_left;
    protected Image[] enemy_right;
    protected Image enemy_dead;
    //hướng của ảnh enemy.
    private char directionOfImage;
    //hướng di chuyển tiếp theo của enemy.
    private char newDirection;
    private int time = 0;
    private final int MAX_TIME = 60;


    public Enemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        width = 30;
        height = 30;
    }
    private void resetTime() {
        time = 0;
    }

    @Override
    public void update() {
        newDirection = getDirection();
        switch (newDirection) {
            case 'u' : moveUp();
            case 'd' : moveDown();
            case 'l' : moveLeft();
            case 'r' : moveRight();
        }
        if(directionOfImage == 'l' && newDirection == 'r') {
            resetTime();
            directionOfImage = 'r';
        }
        if(directionOfImage == 'r' && newDirection == 'l') {
            resetTime();;
            directionOfImage = 'l';
        }
        if(directionOfImage == 'l') {
            setImage(enemy_left[time / (MAX_TIME / 3)]);
        } else {
            setImage(enemy_right[time / (MAX_TIME / 3)]);
        }
        time++;
        if(time == MAX_TIME) resetTime();
        imageView.relocate(x, y);
    }

    public abstract char getDirection();


    public char randomDirection() {
        Random random = new Random();
        int direction = random.nextInt(4);
        char tmp = 'u';
        switch (direction) {
            case 0 -> {
                tmp = 'u';
            }
            case 1 -> {
                tmp = 'd';
            }
            case 2 -> {
                moveLeft();
                tmp = 'l';
            }
            case 3 -> {
                tmp = 'r';
            }
        }
        return tmp;
    }
}
