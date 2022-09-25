package GameEntites;

import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * Đối tượng có thể di chuyển.
 */
public abstract class MoveAnimation extends AnimationEntity {
    protected int speed;
    public MoveAnimation(int x, int y, Image image, int speed) {
        super(x, y, image);
        this.speed = speed;
    }

    public void moveUp() {
        y -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public static void main(String[] args) {

    }
}
