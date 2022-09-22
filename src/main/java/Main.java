import GameEntites.Tile;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import GameEntites.Entity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main extends Application {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    private GraphicsContext graphicsContext;
    private Canvas canvas;
    private Scene scene, scene1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

       /* canvas = new Canvas(WIDTH,HEIGHT);
        Group group = new Group();
        group.getChildren().add(canvas);
        Button button = new Button("Play");
        group.getChildren().add(button);
        scene = new Scene(group);
        button.setOnAction(actionEvent -> {
            primaryStage.setScene(scene1);
        });
        Group group1 = new Group();
        Canvas canvas1 = new Canvas(WIDTH,HEIGHT);
        graphicsContext = canvas1.getGraphicsContext2D();
        Image image = new Image(Files.newInputStream(Paths.get("src/main/resources/Picture/demo.jpg")));
        graphicsContext.drawImage(image,0,0);
        group1.getChildren().add(canvas1);
        Button button1 = new Button("back");
        group1.getChildren().add(button1);
        scene1 = new Scene(group1);*/
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/demoFXML.fxml"));
            // Parent root = FXMLLoader.load(Main.class.getResource("src/main/resources/FXML/demoFXML.fxml"));
            Scene scene2 = new Scene(fxmlLoader.load(), 900, 600);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("hello");
        }
    }
}
