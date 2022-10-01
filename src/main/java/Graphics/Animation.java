package Graphics;

import GameEntites.BalloonEnemy;
import GameEntites.Bomber;
import GameEntites.Bomb;
import GameEntites.Enemy;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Animation {

    private static final int FPS = 30;
    private static final long TIME_PER_FRAME = 1000000000 / FPS;
    private static long lastTime;
    public static Bomber bomber;

    public static void animation(Scene scene, Group group) {
        bomber = new Bomber(Sprite.SizeOfTile, Sprite.MenuSize + Sprite.SizeOfTile, Sprite.player_right_1, 3);
        BalloonEnemy balloonEnemy = new BalloonEnemy(300, 300, Sprite.balloonEnemy, 5);

        group.getChildren().add(balloonEnemy.getImageView());
        group.getChildren().add(bomber.getImageView());
        lastTime = System.nanoTime();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                List<Bomb> bombList = bomber.getBombList();
                for (Bomb bomb : bombList) {
                    group.getChildren().remove(bomb.getImageView());
                    bomb.update();
                }
                bomber.update(scene);
                balloonEnemy.randomMove();
                for (Bomb bomb : bombList) {
                    group.getChildren().add(bomb.getImageView());
                }

                try {
                    TimeUnit.NANOSECONDS.sleep(delay());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        animationTimer.start();
    }

    public static long delay() {
        long endTime = System.nanoTime();
        long delayTime = endTime - lastTime;
        lastTime = endTime;
        if (delayTime < TIME_PER_FRAME) {

            return TIME_PER_FRAME - delayTime;
        }
        return 0;
    }

}
