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
    public static int ROW;
    public static int COLUMN;
    public static int WIDTH = 930;
    public static int HEIGHT = 420;
    public static List<List<Entity>> listEntity = new ArrayList<>();
    public List<Enemy> enemyList = new ArrayList<>();
    public List<Bomber> bomberList = new ArrayList<>();

    /**
     * tạo map.
     *
     * @param level level của map.
     */
    public void createMap(int level, Scene scene) {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/Level/Level1.txt");
            Scanner scanner = new Scanner(fileInputStream);
            COLUMN = scanner.nextInt();
            ROW = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < COLUMN; i++) {
                String string = scanner.nextLine();
                List<Entity> temp = new ArrayList<>();
                for (int j = 0; j < ROW; j++) {
                    switch (string.charAt(j)) {
                        case '#' -> temp.add(new Wall(j * Sprite.SizeOfTile, Sprite.MenuSize + i * Sprite.SizeOfTile, Sprite.wall));
                        case '*' -> temp.add(new Brick(j * Sprite.SizeOfTile, Sprite.MenuSize + i * Sprite.SizeOfTile, Sprite.brick));
                        case 'x' -> temp.add(new Portal(j * Sprite.SizeOfTile, Sprite.MenuSize + i * Sprite.SizeOfTile, Sprite.portal));
                        case 'p' -> {
                            Bomber bomber = new Bomber(j * Sprite.SizeOfCharacter, Sprite.MenuSize + i * Sprite.SizeOfCharacter, Sprite.player_down[0], 3, scene);
                            temp.add(bomber);
                            bomberList.add(bomber);
                        }
                        case '1' -> {
                            BalloonEnemy balloonEnemy = new BalloonEnemy(j * Sprite.SizeOfCharacter, Sprite.MenuSize + i * Sprite.SizeOfCharacter, Sprite.balloon_left[0], 2);
                            temp.add(balloonEnemy);
                            enemyList.add(balloonEnemy);
                        }
                        case '2' -> {
                            OnealEnemy onealEnemy = new OnealEnemy(j * Sprite.SizeOfCharacter, Sprite.MenuSize + i * Sprite.SizeOfCharacter, Sprite.oneal_left[0], 3);
                            temp.add(onealEnemy);
                            enemyList.add(onealEnemy);
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
        for (int i = 0; i < CreateMap.COLUMN; i++) {
            for (int j = 0; j < CreateMap.ROW; j++) {
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
            enemy.update();
        }
    }

    /**
     * cac bomber xu li su kien.
     */
    public void bombersHandleInput() {
        for (Bomber bomber : bomberList) {
            bomber.update();
        }
    }
}
