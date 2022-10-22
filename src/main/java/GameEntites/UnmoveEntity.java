package GameEntites;

import javafx.scene.image.Image;


/**
 * Đối tượng chỉ thay đổi hiệu ứng nhưng không di chuyển.
 */
public abstract class UnmoveEntity extends AnimationEntity {
    protected Image[] images;
    protected int countFrame = 0;
    protected int MAX_TIME;

    public UnmoveEntity(int x, int y, Image image, Image[] images) {
        super(x, y, image);
        this.images = images;
    }

    @Override
    public void update() {
        int tmp = countFrame / (MAX_TIME / 3);
        setImage(images[tmp]);
        countFrame++;
        if(countFrame == MAX_TIME) {
            countFrame = 0;
        }
    }
}
