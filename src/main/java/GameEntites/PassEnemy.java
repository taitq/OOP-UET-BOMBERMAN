package GameEntites;

import Graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * là enemy di chuyển ngẫu nhiên, di xuyên tường.
 */
public class PassEnemy extends Enemy {
    private char direction = 'l';
    private int oldX;
    private int oldY;
    private int countDown = 0;
    private char[] drt = {'u', 'd', 'l', 'r'};

    public PassEnemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        enemy_left = Sprite.pass_left;
        enemy_right = Sprite.pass_right;
        enemy_dead = Sprite.pass_dead;
        wallPass = true;
    }

    @Override
    public char getDirection() {
        Random random = new Random();
        if(oldX == x && oldY == y) {
            char tmp = randomDirection();
            while(tmp == direction) {
                tmp = randomDirection();
            }
            direction = tmp;
            countDown = (random.nextInt(3) + 1) * Sprite.SizeOfTile / speed;
        } else if(countDown == 0) {
            int tmp = random.nextInt(4);
            if(tmp < 4) {
                direction = drt[tmp];
            }
            countDown = (random.nextInt(2) + 1) * Sprite.SizeOfTile / speed;
        }
        countDown--;
        oldX = x;
        oldY = y;
        return direction;
    }

}
