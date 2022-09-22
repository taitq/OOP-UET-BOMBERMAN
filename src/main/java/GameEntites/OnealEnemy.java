package GameEntites;

import javafx.scene.image.Image;

/**
 * Oneal có tốc độ di chuyển thay đổi, lúc nhanh, lúc chậm và
 * di chuyển "thông minh" hơn so với Balloom (biết đuổi theo Bomber)
 */
public class OnealEnemy extends Enemy {
    public OnealEnemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
    }
}
