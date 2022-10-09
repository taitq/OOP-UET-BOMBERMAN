package Graphics;

import GameEntites.Bomb;
import GameEntites.Enemy;
import GameEntites.Bomber;
import GameEntites.Flame;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Animation {

    private static final int FPS = 30;
    private static final long TIME_PER_FRAME = 1000000000 / FPS;
    private static long lastTime;
    public static boolean gameOver = false;
    public static CreateMap map = new CreateMap();

    public static void animation(Scene scene, Group group) {
        map.createMap(1, scene);
        map.renderMap(group);
        lastTime = System.nanoTime();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gameOver) {
                    List<Bomb> bombList = map.bomberList.get(0).getBombList();
                    for (Bomb bomb : bombList) {
                        //remove bomb which are explosive.
                        group.getChildren().remove(bomb.getImageView());
                        // remove flame which are explosive.
                        for (Flame flame : bomb.flameList) {
                            group.getChildren().remove(flame.getImageView());
                        }
                        bomb.update();
                    }

                    map.bombersHandleInput();
                    map.enemyMove();
                    map.bombersHandleInput();
                    // update Enemy list
                    map.updateEnemyList(group);
                    // update flame list to group.
                    for (Bomb bomb : bombList) {
                        group.getChildren().add(bomb.getImageView());
                        for (Flame flame : bomb.flameList) {
                            group.getChildren().add(flame.getImageView());
                        }
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
        Bomber bomber = map.bomberList.get(0);
        if (map.bomberList.get(0).checkCollisonEnemy(map.enemyList)) {
            gameOver = true;
        }
        for (Bomb bomb : bomber.getBombList()) {
            if (bomber.checkCollisonFlame(bomb.flameList)) {
                gameOver = true;
            }
        }
    }

}
