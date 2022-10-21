package Graphics;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    public static Label level;
    public static ImageView bombImage;
    public static ImageView speedImage;
    public static Label speed;

    public Menu(Group group) {
        level = new Label("LEVEL  " + CreateMap.level);
        level.setLayoutX(0);
        level.setLayoutY(0);
       

        try {
            Image image = new Image(Files.newInputStream(Paths.get("src/main/resources/Picture/bombIcon.png")));
            bombImage = new ImageView(image);
            bombImage.setFitWidth(30);
            bombImage.setFitHeight(30);
            bombImage.setLayoutX(100);
            bombImage.setLayoutY(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        group.getChildren().addAll(level, bombImage);
    }

    public static void updateMenu() {
        level.setText("LEVEL  " + CreateMap.level);
    }
}
