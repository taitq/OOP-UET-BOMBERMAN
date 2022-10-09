package Graphics;

import GameEntites.*;
import javafx.scene.Group;
import javafx.scene.Scene;

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
    public void createMap(int level) {
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
                            Bomber bomber = new Bomber(j * Sprite.SizeOfCharacter, Sprite.MenuSize + i * Sprite.SizeOfCharacter, Sprite.player_down[0], 10);
                            temp.add(bomber);
                            bomberList.add(bomber);
                        }
                        case '1' -> {
                            BalloonEnemy balloonEnemy = new BalloonEnemy(j * Sprite.SizeOfCharacter, Sprite.MenuSize + i * Sprite.SizeOfCharacter, Sprite.balloon_left[0], 5);
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
        // render enemy list.
        for (Enemy enemy : enemyList) {
            group.getChildren().add(enemy.getImageView());
        }
        // add cac bomber vao group.
        for (Bomber bomber : bomberList) {
            group.getChildren().add(bomber.getImageView());
        }
    }

    /**
     * update list enemy.
     */
    public void updateEnemyList(Group group) {
        List<Flame> flameList = new ArrayList<>();
        for (Bomber bomber : bomberList) {
            for (Bomb bomb : bomber.getBombList()) {
                flameList.addAll(bomb.flameList);
            }
        }
        List<Enemy> temp = new ArrayList<>();
        for (Enemy enemy : enemyList) {
            enemy.checkcollisonFlame(flameList);
            if (!enemy.isKilled) {
                enemy.move();
            } else {
                enemy.killed();
                temp.add(enemy);
            }
        }
        // remove enemy after die 120 milisecond.
        for (Enemy enemy : temp) {
            if (enemy.getTimeDie() < 0) {
                enemyList.removeAll(temp);
                group.getChildren().remove(enemy.getImageView());
            }
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
