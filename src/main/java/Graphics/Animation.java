package Graphics;

import GameEntites.Bomb;
import GameEntites.Enemy;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Animation {

    private static final int FPS = 60;
    private static final long TIME_PER_FRAME = 1000000000 / FPS;
    private static long lastTime;
    public static boolean gameOver = false;
    public static CreateMap map = new CreateMap();

    public static void animation(Scene scene, Group group) {

        map.createMap(1);
        map.renderMap(group);
        lastTime = System.nanoTime();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gameOver) {
                    List<Bomb> bombList = map.bomberList.get(0).getBombList();
                    for (Bomb bomb : bombList) {
                        group.getChildren().remove(bomb.getImageView());
                        bomb.update();
                    }
                    map.bombersHandleInput(scene);
                    map.enemyMove();
                    for (Bomb bomb : bombList) {
                        group.getChildren().add(bomb.getImageView());
                    }
                    checkGameOver();
                    try {
                        TimeUnit.NANOSECONDS.sleep(delay());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    // TO DO FOR GAME OVER
                    group.getChildren().add(new ImageView(Sprite.gameOver));
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

    /**
     * kiem tra xem game over.
     *
     * @return true if end game.
     */
    public static void checkGameOver() {
        if (map.bomberList.get(0).checkCollisonEnemy(map.enemyList)) {
            gameOver = true;
        }
    }

}
