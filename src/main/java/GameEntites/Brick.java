package GameEntites;

import javafx.scene.image.Image;

/**
 * Brick là đối tượng được đặt lên các ô Grass, không cho phép đặt Bomb lên nhưng có thể bị phá hủy bởi Bomb được đặt gần đó.
 * Bomber và Enemy thông thường không thể di chuyển vào vị trí Brick khi nó chưa bị phá hủy.
 */
public class Brick extends UnmoveEntity implements Obstacle {
    public Brick(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void update() {

    }
}
