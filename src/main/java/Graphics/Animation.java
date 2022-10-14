package Graphics;

import GameEntites.Bomb;
import GameEntites.Bomber;
import GameEntites.Flame;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Animation {

    private static final int FPS = 30;
    private static final long TIME_PER_FRAME = 1000000000 / FPS;
    private static long lastTime;
    public static boolean gameOver = false;
    public static CreateMap map = new CreateMap();
    public static Stage thisStage;
    public static AnimationTimer animationTimer;

    public void animation(Scene scene, Group group, ActionEvent event) {
        thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        map.createMap(1, scene);
        map.renderMap(group);
        lastTime = System.nanoTime();

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gameOver) {
                    playGame(1, scene, group);
                    try {
                        TimeUnit.NANOSECONDS.sleep(delay());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    animationTimer.stop();
                    choice();
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

    public static void playGame(int level, Scene scene, Group group) {
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

        map.bombersHandleInput(group);
        // update Enemy list
        map.updateEnemyList(group);
        // update flame list to group.
        for (Bomb bomb : bombList) {
            //bomb.update();
            group.getChildren().add(bomb.getImageView());
            for (Flame flame : bomb.flameList) {
                group.getChildren().add(flame.getImageView());
            }
        }
        checkGameOver();
    }

    public void choice() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/FXML/PlayAgainOrExit.fxml"));
            FXMLLoader loader = new FXMLLoader();
            Parent choiceRoot = loader.load(fileInputStream);
            Scene choiceScene = new Scene(choiceRoot);
            thisStage.setScene(choiceScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetGame() {
        gameOver = false;
    }
}
