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

import static javafx.application.Platform.exit;

public class PlayAgainOrExit {
    @FXML
    public void playAgain(ActionEvent event) {
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Group group = new Group();
        Canvas canvas = new Canvas(CreateMap.WIDTH, CreateMap.HEIGHT);
        group.getChildren().add(canvas);
        Scene scene = new Scene(group);
        Animation.map = new CreateMap();
        Animation.gameOver = false;
        Animation animation = new Animation();
        animation.animation(scene, group, event);
        thisStage.setScene(scene);
    }

    @FXML
    public void exitGame(ActionEvent event) {
        exit();
    }


}
