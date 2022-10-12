import Graphics.Animation;
import Graphics.CreateMap;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {

        Canvas canvas = new Canvas(CreateMap.WIDTH, CreateMap.HEIGHT);
        Group group = new Group();
        group.getChildren().add(canvas);
        Scene scene = new Scene(group);
        Animation.animation(scene, group);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
