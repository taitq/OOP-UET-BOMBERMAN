package GameEntites;

import Graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * là enemy di chuyển ngẫu nhiên.
 */
public class OvapiEnemy extends Enemy {
    private char direction = 'l';
    private int oldX;
    private int oldY;
    private int countDown = 0;
    private char[] drt = {'u', 'd', 'l', 'r'};

    public OvapiEnemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        enemy_left = Sprite.ovapi_left;
        enemy_right = Sprite.ovapi_right;
        enemy_dead = Sprite.ovapi_dead;
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
            countDown = (random.nextInt(2) + 1) * Sprite.SizeOfTile / speed;
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
