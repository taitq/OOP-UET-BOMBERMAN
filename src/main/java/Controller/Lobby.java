package Controller;

import Graphics.Animation;
import Graphics.Audio;
import Graphics.CreateMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Graphics.Sprite;

import static javafx.application.Platform.exit;

public class Lobby {
    @FXML
    ImageView soundImage;

    public void onePlayer(ActionEvent event) throws Exception {
        CreateMap.level = 1;
        Audio.menuSelect.play();
        Audio.menuSelect.setOnEndOfMedia(Audio.menuSelect::stop);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Canvas canvas = new Canvas(CreateMap.WIDTH, CreateMap.HEIGHT);
        Group group = new Group();
        group.getChildren().add(canvas);
        Scene scene = new Scene(group);
        Animation onePlayer = new Animation(1);
        onePlayer.animation(scene, group, event);
        primaryStage.setScene(scene);
    }

    @FXML
    public void twoPlayer(ActionEvent event) {
        CreateMap.level = 1;
        Audio.menuSelect.play();
        Audio.menuSelect.setOnEndOfMedia(Audio.menuSelect::stop);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Canvas canvas = new Canvas(CreateMap.WIDTH, CreateMap.HEIGHT);
        Group group = new Group();
        group.getChildren().add(canvas);
        Scene scene = new Scene(group);
        Animation onePlayer = new Animation(2);
        onePlayer.animation(scene, group, event);
        primaryStage.setScene(scene);

    }

    @FXML
    public void out() {
        exit();
    }

    @FXML
    public void sound() {
        Audio.sound = (!Audio.sound);
        if (Audio.sound) {
            Audio.lobby.setVolume(0.3);
            Audio.lobby.play();
            soundImage.setImage(Sprite.soundOn);
        } else {
            Audio.lobby.stop();
            soundImage.setImage(Sprite.soundOff);
        }
    }
}
