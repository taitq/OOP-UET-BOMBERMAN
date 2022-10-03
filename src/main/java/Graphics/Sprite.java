package Graphics;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sprite {
    // Kích cỡ ảnh đối tượng Grass, Wall , Brick ,Portal.
    public static final int SizeOfTile = 30;
    // Kích cỡ ảnh của Bomber, Enemy
    public static final int SizeOfCharacter = 30;
    // Kích cỡ ảnh của item
    public static final int SizeOfItem = 30;
    // Kích cỡ bom
    public static int SizeOfBomb = 30;
    // Kích thước thanh menu trong game
    public static int MenuSize = 30;

    public static final Image wall = newImage("wall", SizeOfTile, SizeOfTile) ;
    public static final Image brick = newImage("brick", SizeOfTile, SizeOfTile);
    public static final Image portal = newImage("portal", SizeOfTile, SizeOfTile);
    public static final Image grass = newImage("grass", SizeOfTile, SizeOfTile);
    public static final Image player_right_1 = newImage("player_right_1", SizeOfCharacter, SizeOfCharacter);
    public static final Image bomb = newImage("bomb", SizeOfTile, SizeOfTile);
    public static final Image bomb_exploded = newImage("bomb_exploded", SizeOfTile, SizeOfTile);
    public static final Image balloonEnemy = newImage("balloom_right1", SizeOfCharacter, SizeOfCharacter);
    public static final Image gameOver = newImage("gameOver", 930, 420);
    //hàm set image rút gọn.
    private static Image newImage(String name, int w, int h) {
        Image image = null;
        try {
            image = new Image(Files.newInputStream(Paths.get("src/main/resources/sprites/" + name + ".png")), w, h, true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}

