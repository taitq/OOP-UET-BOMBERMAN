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
import javafx.stage.Stage;

public class Lobby {
    @FXML
    public void onePlayer(ActionEvent event) throws Exception {
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

    public void twoPlayer(ActionEvent event) {
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
}
