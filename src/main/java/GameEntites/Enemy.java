package GameEntites;

import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * Đối tượng quái.
 */
public abstract class Enemy extends MoveAnimation {
    public Enemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
    }
}
