package GameEntites;

import javafx.scene.image.Image;

import java.util.List;

/**
 * Đối tượng chỉ thay đổi hiệu ứng nhưng không di chuyển.
 */
public abstract class UnmoveEntity extends AnimationEntity {
    protected Image[] images;
    private int countDown;

    public UnmoveEntity(int x, int y, Image image, Image[] images) {
        super(x, y, image);
        this.images = images;
        countDown = 60;
    }

    @Override
    public void update() {
        countDown--;
        int tmp = countDown / 20;
        setImage(images[tmp]);
        if(countDown == 0) {
            countDown = 60;
        }
    }
}
