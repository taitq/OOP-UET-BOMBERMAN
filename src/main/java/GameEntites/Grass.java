package GameEntites;

import javafx.scene.image.Image;

/**
 * Grass là đối tượng mà Bomber và Enemy có thể di chuyển xuyên qua, và cho phép đặt Bomb lên vị trí của nó.
 */
public class Grass extends Tile {
    public Grass(int x, int y, Image image) {
        super(x, y, image);
    }
}
