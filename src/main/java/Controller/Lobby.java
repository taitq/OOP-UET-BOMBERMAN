package Controller;

import Graphics.Animation;
import Graphics.CreateMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Lobby {
    @FXML
    public void onePlayer(ActionEvent event) throws Exception {
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
