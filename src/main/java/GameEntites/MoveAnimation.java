package GameEntites;

import javafx.scene.image.Image;

/**
 * Đối tượng có thể di chuyển.
 */
public abstract class MoveAnimation extends AnimationEntity {
    public MoveAnimation(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
    }
}
