package Graphics;

import GameEntites.*;
import javafx.scene.canvas.GraphicsContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Đồ họa của game.
 */
public class CreateMap {
    public static int HEIGHT;
    public static int WIDTH;
    public List<List<Entity>> listEntity = new ArrayList<>();

    /**
     * tạo map.
     *
     * @param level level của map.
     */
    public void createMap(int level) {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/Level/Level1.txt");
            Scanner scanner = new Scanner(fileInputStream);
            HEIGHT = scanner.nextInt();
            WIDTH = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < HEIGHT; i++) {
                String string = scanner.nextLine();
                List<Entity> temp = new ArrayList<>();
                for (int j = 0; j < WIDTH; j++) {
                    switch (string.charAt(j)) {
                        case '#' -> temp.add(new Wall(j * Sprite.SizeOfTile, Sprite.MenuSize + i * Sprite.SizeOfTile, Sprite.wallImage));
                        case '*' -> temp.add(new Brick(j * Sprite.SizeOfTile, Sprite.MenuSize + i * Sprite.SizeOfTile, Sprite.brickImage));
                        case 'x' -> temp.add(new Portal(j * Sprite.SizeOfTile, Sprite.MenuSize + i * Sprite.SizeOfTile, Sprite.portalImage));
                        case 'p' -> temp.add(new Bomber(j * Sprite.SizeOfCharacter, Sprite.MenuSize + i * Sprite.SizeOfCharacter, Sprite.playerImageRight, 2, 1));
                        default -> temp.add(new Grass(j * Sprite.SizeOfTile, Sprite.MenuSize + i * Sprite.SizeOfTile, Sprite.grassImage));
                    }
                }
                listEntity.add(temp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * render Map on scene
     *
     * @param graphicsContext but ve.
     */
    public void renderMap(GraphicsContext graphicsContext) {
        for (int i = 0; i < CreateMap.HEIGHT; i++) {
            for (int j = 0; j < CreateMap.WIDTH; j++) {
                listEntity.get(i).get(j).DrawEntity(graphicsContext);
            }
        }
    }

}
