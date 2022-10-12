
import Controller.Lobby;
import Graphics.CreateMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {

       /* Canvas canvas = new Canvas(CreateMap.WIDTH, CreateMap.HEIGHT);
        Group group = new Group();
        group.getChildren().add(canvas);
        Scene scene = new Scene(group);
        Animation.animation(scene, group);
        primaryStage.setScene(scene);
        File file = new File("src/main/resources/Music/test.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        primaryStage.show();
*/
        try {
            FXMLLoader lobby = new FXMLLoader(getClass().getResource("FXML/Lobby.fxml"));
            Scene lobbyScene = new Scene(lobby.load(), CreateMap.WIDTH, CreateMap.HEIGHT);
            primaryStage.setScene(lobbyScene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
