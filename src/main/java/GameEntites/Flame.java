package GameEntites;

import javafx.scene.image.Image;

/**
 * Khi bom nổ sẽ tạo ra đối tượng flame này.
 */
public class Flame extends UnmoveEntity {
    public Flame(int x, int y, Image image, int speed) {
        super(x, y, image);
    }

    @Override
    public void update() {

    }
}
