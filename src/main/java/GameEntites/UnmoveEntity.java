package GameEntites;

import javafx.scene.image.Image;

/**
 * Đối tượng chỉ thay đổi hiệu ứng nhưng không di chuyển.
 */
public abstract class UnmoveEntity extends AnimationEntity {
    public UnmoveEntity(int x, int y, Image image, int speed) {
        super(x, y, image, 0);
    }
}
