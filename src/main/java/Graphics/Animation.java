package Graphics;

import GameEntites.Bomb;
import GameEntites.Bomber;
import GameEntites.Flame;
import Run.Main;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Animation {

    private static final int FPS = 30;
    private static final long TIME_PER_FRAME = 1000000000 / FPS;
    private static long lastTime;
    public static boolean gameOver;
    public static CreateMap map;
    public static Stage thisStage;
    public int type;
    public static boolean victory = false;
    public static AnimationTimer animationTimer;
    public static Menu menu;

    public Animation(int type) {
        this.type = type;
        gameOver = false;
        map = new CreateMap(type);
        menu = new Menu();
    }

    public void animation(Scene scene, Group group, ActionEvent event) {
        thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        map.createMap(scene);
        map.renderMap(group);
        menu.initMenu(group);
        lastTime = System.nanoTime();

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gameOver && !victory) {
                    playGame(scene, group);

                } else if (!victory) {
                    this.stop();
                    Audio.background.pause();
                    choice();
                } else {
                    this.stop();
                    Audio.background.pause();
                    Victory();
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

    /**
     * kiem tra xem game over.
     */
    public static boolean checkGameOver(Group group) {
        if (Bomber.numberBomberLive == 0) {
            gameOver = true;
            return true;
        }
        for (int i = 0; i < CreateMap.bomberList.size(); i++) {
            CreateMap.bomberList.get(i).checkCollisonEnemy(map.enemyList);
            for (Bomb bomb : CreateMap.bomberList.get(i).getBombList()) {
                CreateMap.bomberList.get(i).checkCollisonFlame(bomb.flameList);
            }
            if (CreateMap.bomberList.get(i).getTimeDie() < 0) {
                group.getChildren().remove(CreateMap.bomberList.get(i).getImageView());
                CreateMap.bomberList.remove(CreateMap.bomberList.get(i));
                Bomber.numberBomberLive--;
            }

        }
        return false;
    }

    public void playGame(Scene scene, Group group) {
        if (checkGameOver(group)) {
            choice();
        }
        menu.updateMenu(scene);
        if (Audio.sound) {
            Audio.lobby.pause();
            Audio.background.setVolume(0.5);
            Audio.background.play();
            Audio.background.setCycleCount(MediaPlayer.INDEFINITE);
        }
        List<List<Bomb>> bombList = new ArrayList<>();
        for (int i = 0; i < CreateMap.bomberList.size(); i++) {
            bombList.add(CreateMap.bomberList.get(i).getBombList());
        }
        for (List<Bomb> bombList1 : bombList) {
            for (Bomb bomb : bombList1) {
                //remove bomb which are explosive.
                group.getChildren().remove(bomb.getImageView());
                // remove flame which are explosive.
                for (Flame flame : bomb.flameList) {
                    group.getChildren().remove(flame.getImageView());
                }
                bomb.update();
            }
        }

        map.bombersHandleInput(group);
        // update Enemy list
        map.updateEnemyList(group);
        // update flame list to group.
        for (List<Bomb> bombList1 : bombList) {
            for (Bomb bomb : bombList1) {
                //bomb.update();
                group.getChildren().add(bomb.getImageView());
                for (Flame flame : bomb.flameList) {
                    group.getChildren().add(flame.getImageView());
                }
            }
        }

        if (Bomber.isGoToPortal()) {
            Bomber.numberBomberLive = 0;
            map = new CreateMap(map.type);
            CreateMap.level++;
            if (Audio.sound) {
                Audio.nextLevel.play();
                Audio.nextLevel.setOnEndOfMedia(Audio.nextLevel::stop);
            }
            if (CreateMap.level <= CreateMap.LEVEL_MAX) {
                map.createMap(scene);
                map.renderMap(group);
                playGame(scene, group);
            } else {
                victory = true;
                return;
            }

        }
    }

    // victory game
    private static void Victory() {
        try {
            victory = false;
            Canvas canvas = new Canvas(CreateMap.WIDTH, CreateMap.HEIGHT);
            Group group = new Group();
            group.getChildren().add(canvas);
            Image image = new Image(Files.newInputStream(Paths.get("src/main/resources/Picture/victory.png")));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(CreateMap.WIDTH);
            imageView.setFitHeight(CreateMap.HEIGHT);
            group.getChildren().add(imageView);
            Scene scene = new Scene(group);
            Audio.victory.play();
            thisStage.setScene(scene);
            Audio.victory.setOnEndOfMedia(() -> {
                Audio.victory.stop();
                Audio.sound = true;
                Audio.lobby.play();
                thisStage.setScene(Main.menuScene);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void choice() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/FXML/PlayAgainOrExit.fxml"));
            FXMLLoader loader = new FXMLLoader();
            Parent choiceRoot = loader.load(fileInputStream);
            Scene choiceScene = new Scene(choiceRoot);
            thisStage.setScene(choiceScene);
            if (Audio.sound) {
                Audio.gameOver.play();
                Audio.gameOver.setOnEndOfMedia(Audio.gameOver::stop);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
