package GameEntites;

import javafx.scene.image.Image;

/**
 * Các Item cũng được giấu phía sau Brick và chỉ hiện ra khi Brick bị phá hủy.
 */
public abstract class Item extends StaticEntity {
    public Item(int x, int y, Image image) {
        super(x, y, image);
    }
}
