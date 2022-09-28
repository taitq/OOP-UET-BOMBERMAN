package Graphics;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sprite {
    public static Image wallImage = null;
    public static Image brickImage = null;
    public static Image portalImage = null;
    public static Image grassImage = null;
    public static Image playerImageRight = null;
    public static Image bombImage = null;
    public static Image bombExplodedImage = null;
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

    static {
        try {
            wallImage = new Image(Files.newInputStream(Paths.get("src/main/resources/sprites/wall.png")), SizeOfTile, SizeOfTile, true, true);
            brickImage = new Image(Files.newInputStream(Paths.get("src/main/resources/sprites/brick.png")), SizeOfTile, SizeOfTile, true, true);
            portalImage = new Image(Files.newInputStream(Paths.get("src/main/resources/sprites/portal.png")), SizeOfTile, SizeOfTile, true, true);
            grassImage = new Image(Files.newInputStream(Paths.get("src/main/resources/sprites/grass.png")), SizeOfTile, SizeOfTile, true, true);
            bombImage = new Image(Files.newInputStream(Paths.get("src/main/resources/sprites/bomb.png")), SizeOfTile, SizeOfTile, true, true);
            bombExplodedImage = new Image(Files.newInputStream(Paths.get("src/main/resources/sprites/bomb_exploded.png")), SizeOfTile, SizeOfTile, true, true);
            playerImageRight = new Image(Files.newInputStream(Paths.get("src/main/resources/sprites/player_right_1.png")), SizeOfCharacter, SizeOfCharacter, true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

