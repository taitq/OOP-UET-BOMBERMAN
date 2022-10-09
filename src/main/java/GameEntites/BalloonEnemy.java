package GameEntites;

import Graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * là Enemy đơn giản nhất, di chuyển ngẫu nhiên với vận tốc cố định
 */
public class BalloonEnemy extends Enemy {
    public BalloonEnemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
    }

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

    @Override
    public void killed() {
        setTimeDie(getTimeDie() - 1);
        setImage(Sprite.balloon_dead);
    }
}
