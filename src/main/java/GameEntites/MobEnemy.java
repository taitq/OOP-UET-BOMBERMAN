package GameEntites;

import Graphics.Sprite;
import javafx.scene.image.Image;

public class MobEnemy extends Enemy {

    public MobEnemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        /*enemy_left = Sprite.mob_left;
        enemy_right = Sprite.mob_right;
        enemy_dead = Sprite.mob_dead;*/
    }

    @Override
    public char getDirection() {
        return 'u';
    }
}
