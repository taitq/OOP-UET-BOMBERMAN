package Graphics;

import GameEntites.Bomber;
import Run.Main;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    public Label level;
    public ArrayList<ImageView> playerList;
    public ArrayList<ImageView> numberBomb;
    public ArrayList<ImageView> speedList;
    public List<Label> numberBombLabelList;
    public List<Label> speedLabelList;
    public ImageView pauseView;
    public ImageView playView;
    public ImageView back;

    public Menu() {
        playerList = new ArrayList<>();
        numberBomb = new ArrayList<>();
        speedList = new ArrayList<>();
        numberBombLabelList = new ArrayList<>();
        speedLabelList = new ArrayList<>();
        pauseView = new ImageView(Sprite.pause);
        playView = new ImageView(Sprite.play);
        back = new ImageView(Sprite.back);
        level = new Label("LEVEL  " + CreateMap.level);
    }

    public void initMenu(Group group) {
        level.setLayoutX(460);
        pauseView.setLayoutX(50);
        playView.setLayoutX(100);
       /* System.out.println(CreateMap.type);
        System.out.println(CreateMap.bomberList.size());
        System.out.println();*/
        for (int i = 0; i < CreateMap.type; i++) {
            ImageView player = new ImageView(Sprite.p[i]);
            player.setLayoutX(200 + i * 400);

            ImageView bomb = new ImageView(Sprite.bombIcon);
            bomb.setLayoutX(270 + i * 400);

            ImageView speed = new ImageView(Sprite.speed);
            speed.setLayoutX(340 + i * 400);

            String n = String.valueOf(CreateMap.bomberList.get(i).getNumberOfBomb());
            Label bombLabel = new Label(n);
            bombLabel.setLayoutX(310 + i * 400);
            bombLabel.setLayoutY(7);
            numberBombLabelList.add(bombLabel);

            String v = String.valueOf(CreateMap.bomberList.get(i).getSpeed());
            Label speedLabel = new Label(v);
            speedLabel.setLayoutX(380 + i * 400);
            speedLabel.setLayoutY(7);
            speedLabelList.add(speedLabel);

            speedList.add(speed);
            numberBomb.add(bomb);
            playerList.add(player);
        }

        group.getChildren().addAll(level, pauseView, playView, back);
        for (int i = 0; i < CreateMap.type; i++) {
            group.getChildren().addAll(playerList.get(i), speedList.get(i), numberBomb.get(i)
                    , numberBombLabelList.get(i), speedLabelList.get(i));
        }
    }

    public void updateMenu(Scene scene, Group group) {
        player();
        setLevel();
        pause();
        play();
        back(scene, group);
        numberBombList();
    }

    public void pause() {
        pauseView.setCursor(Cursor.HAND);
        pauseView.setOnMouseClicked(mouseEvent -> {
            if (Audio.menuSelect != null) {
                Audio.menuSelect.play();
            }
            Animation.animationTimer.stop();
        });
    }

    public void play() {
        playView.setCursor(Cursor.HAND);
        playView.setOnMouseClicked(mouseEvent -> {
            if (Audio.menuSelect != null) {
                Audio.menuSelect.play();
            }
            Animation.animationTimer.start();
        });
    }

    public void setLevel() {
        level.setLayoutY(7);
        level.setText("LEVEL  " + CreateMap.level);
    }

    public void player() {
       /* System.out.println(CreateMap.bomberList.size());
        System.out.println(Bomber.numberBomberLive);
        System.out.println(numberBombLabelList.size());
        System.out.println(speedLabelList.size());
        System.out.println();*/
        for (int i = 0; i < Bomber.numberBomberLive; i++) {
            numberBombLabelList.get(i).setText(String.valueOf(CreateMap.bomberList.get(i).getNumberOfBomb()));
            speedLabelList.get(i).setText(String.valueOf(CreateMap.bomberList.get(i).getSpeed()));
        }
    }

    public void back(Scene scene, Group group) {
        Stage thisStage = (Stage) scene.getWindow();
        back.setCursor(Cursor.HAND);
        back.setOnMouseClicked(mouseEvent -> {
            if (Audio.menuSelect != null) {
                Audio.menuSelect.play();
            }
            if (Audio.background != null) {
                Audio.background.pause();
            }
            Animation.animationTimer.stop();
            thisStage.setScene(Main.menuScene);
        });
    }

    public void numberBombList() {
        for (int i = 0; i < Bomber.numberBomberLive; i++) {
            numberBombLabelList.get(i).setText(String.valueOf(CreateMap.bomberList.get(i).getNumberOfBomb()));
        }
    }
}
