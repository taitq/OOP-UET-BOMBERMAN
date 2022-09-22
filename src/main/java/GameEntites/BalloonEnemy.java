package GameEntites;

import javafx.scene.image.Image;

/**
 * là Enemy đơn giản nhất, di chuyển ngẫu nhiên với vận tốc cố định
 */
public class BalloonEnemy extends Enemy {
    public BalloonEnemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
    }
}
