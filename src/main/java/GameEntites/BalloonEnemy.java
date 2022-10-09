package GameEntites;

import Graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * là enemy đơn giản, chỉ di chuyển trái phải.
 */
public class BalloonEnemy extends Enemy {
    private char direction = 'l';
    private int oldX = x;
    private int oldY = y;

    public BalloonEnemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        enemy_left = Sprite.balloon_left;
        enemy_right = Sprite.balloon_right;
        enemy_dead = Sprite.balloon_dead;
    }

    @Override
    public char getDirection() {
        if(oldX == x && oldY == y) {
            if(direction == 'l') {
                direction = 'r';
            } else {
                direction = 'l';
            }
        }
        oldX = x;
        oldY = y;
        return direction;
    }

    @Override
    public void killed() {
        setTimeDie(getTimeDie() - 1);
        setImage(Sprite.balloon_dead);
    }

    @Override
    public void move() {
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction) {
            case 0 -> {
                moveUp();
            }
            case 1 -> {
                moveDown();
            }
            case 2 -> {
                moveLeft();
            }
            case 3 -> {
                moveRight();
            }
        }
        imageView.relocate(getX(), getY());
    }
}
