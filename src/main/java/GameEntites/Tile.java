package GameEntites;

import javafx.scene.image.Image;

/**
 * Đối tượng cố định tạo nên map.
 */
public abstract class Tile extends StaticEntity {
    public Tile(int x, int y, Image image) {
        super(x, y, image);
    }
}
