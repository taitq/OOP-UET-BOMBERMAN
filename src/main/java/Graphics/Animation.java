package Graphics;

import GameEntites.Bomber;
import GameEntites.Bomb;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import java.util.List;

public class Animation {
    public static void animation(Scene scene, Group group) {
        Bomber bomber = new Bomber(Sprite.SizeOfTile, Sprite.MenuSize + Sprite.SizeOfTile, Sprite.playerImageRight, 5);
        group.getChildren().add(bomber.getImageView());
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            List<Bomb> bombList = bomber.getBombList();
            for(Bomb bomb: bombList) {
                group.getChildren().remove(bomb.getImageView());
                bomb.update();
            }
            bomber.update(scene);
            bomber.getImageView().relocate(bomber.getX(), bomber.getY());
            for(Bomb bomb: bombList) {
                group.getChildren().add(bomb.getImageView());
            }
            }
        };
        animationTimer.start();
    }
}
