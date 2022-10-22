package Run;

import Graphics.Audio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Main extends Application {
    public static Scene menuScene;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {

        try {
            // play audio background
            Audio.lobby.play();
            FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/FXML/Lobby.fxml"));
            FXMLLoader loader = new FXMLLoader();
            Parent choiceRoot = loader.load(fileInputStream);
            menuScene = new Scene(choiceRoot);
            primaryStage.setScene(menuScene);
            primaryStage.setTitle("BOMBERMAN UET ");
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
