package Graphics;

import GameEntites.Bomber;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class Animation {
    public static void animation(Scene scene, Group group) {
        Bomber bomber = new Bomber(60, 60, Sprite.playerImageRight, 30, 2);
        ImageView imageView = new ImageView(bomber.getImage());
        group.getChildren().add(imageView);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                bomber.handleInput(scene);
                imageView.relocate(bomber.getX(), bomber.getY());
            }
        };
        animationTimer.start();
    }
}
