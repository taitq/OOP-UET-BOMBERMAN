package GameEntites;

import javafx.scene.image.Image;

/**
 * Đối tượng mà có thay đổi về vị trí hay hiệu ứng,.. trong chương trình.
 */
public abstract class AnimationEntity extends Entity {

    public AnimationEntity(int x, int y, Image image) {
        super(x, y, image);
    }

}
