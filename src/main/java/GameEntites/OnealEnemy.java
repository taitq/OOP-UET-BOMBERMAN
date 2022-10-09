package GameEntites;

import Graphics.Sprite;
import javafx.scene.image.Image;

/**
 * oneal là enemy đơn giản, chỉ di chuyển lên xuống.
 */
public class OnealEnemy extends Enemy {
    private char direction = 'u';
    private int oldX = x;
    private int oldY = y;

    public OnealEnemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        enemy_left = Sprite.oneal_left;
        enemy_right = Sprite.oneal_right;
        enemy_dead = Sprite.oneal_dead;
    }

    @Override
    public char getDirection() {
        if(oldX == x && oldY == y) {
            if(direction == 'u') {
                direction = 'd';
            } else {
                direction = 'u';
            }
        }
        oldX = x;
        oldY = y;
        return direction;
    }

    @Override
    public void killed() {
        setImage(Sprite.balloon_dead);
    }
}
