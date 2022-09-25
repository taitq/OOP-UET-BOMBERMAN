import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import Graphics.CreateMap;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {
    public static final int WIDTH = 930;
    public static final int HEIGHT = 420;
    private GraphicsContext graphicsContext;
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
        Button button = new Button("Play");
        group.getChildren().add(button);
        scene = new Scene(group);
        button.setOnAction(actionEvent -> {
            primaryStage.setScene(scene1);
        });
        Group group1 = new Group();
        Canvas canvas1 = new Canvas(WIDTH, HEIGHT);
        graphicsContext = canvas1.getGraphicsContext2D();

       /* for(int i = 0; i < 30; i++) {
            Wall wall = new Wall(i*Sprite.SizeOfTile,Sprite.MenuSize, Sprite.wallImage);
            wall.DrawEntity(graphicsContext);
            Grass grass = new Grass( i*Sprite.SizeOfTile,Sprite.MenuSize+Sprite.SizeOfTile,Sprite.grassImage);
            grass.DrawEntity(graphicsContext);
            Brick brick = new Brick(i*Sprite.SizeOfTile,Sprite.MenuSize+2+3*Sprite.SizeOfTile,Sprite.brickImage);
            brick.DrawEntity(graphicsContext);

        }*/
        CreateMap map1 = new CreateMap();
        map1.createMap(1);
        for (int i = 0; i < CreateMap.HEIGHT; i++) {
            for (int j = 0; j < CreateMap.WIDTH; j++) {
                map1.listEntity.get(i).get(j).DrawEntity(graphicsContext);
            }
        }
        group1.getChildren().add(canvas1);
        Button button1 = new Button("back");
        group1.getChildren().add(button1);
        scene1 = new Scene(group1);

        button1.setOnAction(event -> {
            primaryStage.setScene(scene);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
        /*try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/demoFXML.fxml"));
            // Parent root = FXMLLoader.load(Main.class.getResource("src/main/resources/FXML/demoFXML.fxml"));
            Scene scene2 = new Scene(fxmlLoader.load(), 900, 600);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("hello");
        }*/
    }
}
