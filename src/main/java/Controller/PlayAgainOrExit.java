package Controller;

import GameEntites.Bomber;
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

import static javafx.application.Platform.exit;

public class PlayAgainOrExit {
    @FXML
    public void playAgain(ActionEvent event) {
        //CreateMap.level = 1;
        Bomber.numberBomberLive = 0;
        Audio.gameOver.pause();
        Audio.menuSelect.play();
        Audio.menuSelect.setOnEndOfMedia(Audio.menuSelect::stop);
        Animation.gameOver = false;
        int type = Animation.map.type;
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Group group = new Group();
        Canvas canvas = new Canvas(CreateMap.WIDTH, CreateMap.HEIGHT);
        group.getChildren().add(canvas);
        Scene scene = new Scene(group);
        Animation.map = new CreateMap(type);
        Animation animation = new Animation(type);
        animation.animation(scene, group, event);
        thisStage.setScene(scene);
    }

    @FXML
    public void exitGame(ActionEvent event) {
        Audio.menuSelect.play();
        Audio.menuSelect.setOnEndOfMedia(Audio.menuSelect::stop);
        exit();
    }


}
