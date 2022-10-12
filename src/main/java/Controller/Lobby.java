package Controller;

import Graphics.Animation;
import Graphics.CreateMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
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
        primaryStage.setScene(scene);
        Animation.animation(scene, group);
        File file = new File("src/main/resources/Music/test.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        primaryStage.show();
    }
}
