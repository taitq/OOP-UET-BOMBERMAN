package GameEntites;

import javafx.scene.image.Image;

/**
 * Wall là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được,
 * Bomber và Enemy không thể di chuyển vào đối tượng này.
 */
public class Wall extends Tile implements Obstacle {
    public Wall(int x, int y, Image image) {
        super(x, y, image);
    }
}
