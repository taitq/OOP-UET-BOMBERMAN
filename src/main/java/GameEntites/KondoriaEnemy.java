package GameEntites;

import Graphics.Sprite;
import javafx.scene.image.Image;

public class KondoriaEnemy extends Enemy {

    public KondoriaEnemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        enemy_left = Sprite.kondoria_left;
        enemy_right = Sprite.kondoria_right;
        enemy_dead = Sprite.kondoria_dead;
    }

    @Override
    public char getDirection() {
        return 'u';
    }
}
