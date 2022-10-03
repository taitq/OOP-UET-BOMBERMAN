package Graphics;

import GameEntites.*;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import javax.swing.text.html.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Đồ họa của game.
 */
public class CreateMap {
    public static int WIDTH;
    public static int HEIGHT;

    public static List<List<Entity>> listEntity = new ArrayList<>();
    public List<Enemy> enemyList = new ArrayList<>();
    public List<Bomber> bomberList = new ArrayList<>();

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
                        case '#' -> temp.add(new Wall(j * Sprite.SizeOfTile, Sprite.MenuSize + i * Sprite.SizeOfTile, Sprite.wall));
                        case '*' -> temp.add(new Brick(j * Sprite.SizeOfTile, Sprite.MenuSize + i * Sprite.SizeOfTile, Sprite.brick));
                        case 'x' -> temp.add(new Portal(j * Sprite.SizeOfTile, Sprite.MenuSize + i * Sprite.SizeOfTile, Sprite.portal));
                        case 'p' -> {
                            Bomber bomber = new Bomber(j * Sprite.SizeOfCharacter, Sprite.MenuSize + i * Sprite.SizeOfCharacter, Sprite.player_right_1, 10);
                            temp.add(bomber);
                            bomberList.add(bomber);
                        }
                        case '1' -> {
                            BalloonEnemy balloonEnemy = new BalloonEnemy(j * Sprite.SizeOfCharacter, Sprite.MenuSize + i * Sprite.SizeOfCharacter, Sprite.balloonEnemy, 5);
                            temp.add(balloonEnemy);
                            enemyList.add(balloonEnemy);
                        }
                        default -> temp.add(new Grass(j * Sprite.SizeOfTile, Sprite.MenuSize + i * Sprite.SizeOfTile, Sprite.grass));
                    }
                }
                listEntity.add(temp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * add Map to group
     *
     * @param group group.
     */
    public void renderMap(Group group) {
        // add nhung thuc the khong phai enemy va bomber vao group truoc.
        for (int i = 0; i < CreateMap.HEIGHT; i++) {
            for (int j = 0; j < CreateMap.WIDTH; j++) {
                if (!(listEntity.get(i).get(j) instanceof Enemy || listEntity.get(i).get(j) instanceof Bomber)) {
                    group.getChildren().add(listEntity.get(i).get(j).getImageView());
                } else {
                    Grass grass = new Grass(j * Sprite.SizeOfTile, Sprite.MenuSize + i * Sprite.SizeOfTile, Sprite.grass);
                    group.getChildren().add(grass.getImageView());
                }
            }
        }
        // add cac enemy vao group.
        for (Enemy enemy : enemyList) {
            group.getChildren().add(enemy.getImageView());
        }
        // add cac bomber vao group.
        for (Bomber bomber : bomberList) {
            group.getChildren().add(bomber.getImageView());
        }
    }

    /**
     * cac enemy di chuyen.
     */
    public void enemyMove() {
        for (Enemy enemy : enemyList) {
            enemy.move();
        }
    }

    /**
     * cac bomber xu li su kien.
     */
    public void bombersHandleInput(Scene scene) {
        for (Bomber bomber : bomberList) {
            bomber.update(scene);
        }
    }
}
