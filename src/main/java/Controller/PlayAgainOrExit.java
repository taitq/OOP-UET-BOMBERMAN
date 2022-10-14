package Controller;

import Graphics.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static javafx.application.Platform.exit;

public class PlayAgainOrExit {
    @FXML
    public void playAgain() {

    }

    @FXML
    public void exitGame(ActionEvent event) {
        exit();
    }


}
