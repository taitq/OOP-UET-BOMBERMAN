package GameEntites;

import Graphics.Sprite;
import javafx.scene.image.Image;


/**
 * là enemy đơn giản, chỉ di chuyển trái phải.
 */
public class BalloonEnemy extends Enemy {
    private char direction = 'l';
    private int oldX;
    private int oldY;

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

}
