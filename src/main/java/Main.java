import GameEntites.Bomber;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Graphics.*;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {
    public static final int WIDTH = 930;
    public static final int HEIGHT = 420;

    private Canvas canvas;
    private Scene scene, scene1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        canvas = new Canvas(WIDTH, HEIGHT);
        Group group = new Group();
        group.getChildren().add(canvas);
        Scene scene = new Scene(group);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Animation.animation(scene, group);

        CreateMap map1 = new CreateMap();
        map1.createMap(1);
        map1.renderMap(graphicsContext);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
