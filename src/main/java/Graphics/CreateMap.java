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
    public static List<List<Entity>> listEntity;
    public List<Enemy> enemyList;
    public static List<Bomber> bomberList;
    public List<Item> itemList;
    public Portal portal;
    // type 1 player/2 player.
    public int type;
    public static int level = 1;
    public static int LEVEL_MAX = 3;

    public CreateMap(int type) {
        listEntity = new ArrayList<>();
        enemyList = new ArrayList<>();
        bomberList = new ArrayList<>();
        itemList = new ArrayList<>();
        this.type = type;
    }

    /**
     * tạo map.
     */
    public void createMap(Scene scene) {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/Level/Level" + level + ".txt");
            if (type == 2) {
                fileInputStream = new FileInputStream("src/main/resources/Level/twoPlayerLevel" + level + ".txt");
            }
            Scanner scanner = new Scanner(fileInputStream);
            COLUMN = scanner.nextInt();
            ROW = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < COLUMN; i++) {
                String string = scanner.nextLine();
                List<Entity> temp = new ArrayList<>();
                for (int j = 0; j < ROW; j++) {
                    int x = j * Sprite.SizeOfTile;
                    int y = Sprite.MenuSize + i * Sprite.SizeOfTile;
                    switch (string.charAt(j)) {
                        case '#' -> temp.add(new Wall(x, y, Sprite.wall));
                        case '*' -> temp.add(new Brick(x, y, Sprite.brick, Sprite.brick_exploded));
                        case 'x' -> {
                            temp.add(new Brick(x, y, Sprite.brick, Sprite.brick_exploded));
                            portal = new Portal(x, y, Sprite.portal);
                        }
                        case 'b' -> {
                            temp.add(new Brick(x, y, Sprite.brick, Sprite.brick_exploded));
                            itemList.add(new BombItem(x, y, Sprite.powerup_bombs));
                        }
                        case 'f' -> {
                            temp.add(new Brick(x, y, Sprite.brick, Sprite.brick_exploded));
                            itemList.add(new FlameItem(x, y, Sprite.powerup_flames));
                        }
                        case 's' -> {
                            temp.add(new Brick(x, y, Sprite.brick, Sprite.brick_exploded));
                            itemList.add(new SpeedItem(x, y, Sprite.powerup_speed));
                        }
                        case 'p' -> {
                            temp.add(new Grass(x, y, Sprite.grass));
                            bomberList.add(new Bomber(x, y, Sprite.player_down[0], 3, scene));
                        }
                        case 'q' -> {
                            temp.add(new Grass(x, y, Sprite.grass));
                            bomberList.add(new Bomber2(x, y, Sprite.player2_down[0], 3, scene));
                        }
                        case '1' -> {
                            temp.add(new Grass(x, y, Sprite.grass));
                            enemyList.add(new BalloonEnemy(x, y, Sprite.balloon_left[0], 1));
                        }
                        case '2' -> {
                            temp.add(new Grass(x, y, Sprite.grass));
                            enemyList.add(new OnealEnemy(x, y, Sprite.oneal_left[0], 1));
                        }
                        case '3' -> {
                            temp.add(new Grass(x, y, Sprite.grass));
                            enemyList.add(new DollEnemy(x, y, Sprite.doll_left[0], 1));
                        }
                        case '4' -> {
                            temp.add(new Grass(x, y, Sprite.grass));
                            enemyList.add(new KondoriaEnemy(x, y, Sprite.kondoria_left[0], 1));
                        }
                        case '5' -> {
                            temp.add(new Grass(x, y, Sprite.grass));
                            enemyList.add(new MinvoEnemy(x, y, Sprite.minvo_left[0], 1));
                        }
                        case '6' -> {
                            temp.add(new Grass(x, y, Sprite.grass));
                            //chua biet lam gi.
                            //enemyList.add(new MobEnemy(x, y, Sprite.oneal_left[0], 1));
                        }
                        default -> temp.add(new Grass(x, y, Sprite.grass));
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
        // add grass vao truoc
        for (int i = 0; i < CreateMap.COLUMN; i++) {
            for (int j = 0; j < CreateMap.ROW; j++) {
                Grass grass = new Grass(j * Sprite.SizeOfTile, Sprite.MenuSize + i * Sprite.SizeOfTile, Sprite.grass);
                group.getChildren().add(grass.getImageView());
            }
        }
        //add item and portal
        for (Item item : itemList) {
            group.getChildren().add(item.getImageView());
        }
        group.getChildren().add(portal.getImageView());
        // add nhung thuc the khong phai bomber va enemy
        for (int i = 0; i < CreateMap.COLUMN; i++) {
            for (int j = 0; j < CreateMap.ROW; j++) {
                //if (!(listEntity.get(i).get(j) instanceof Enemy || listEntity.get(i).get(j) instanceof Bomber))
                {
                    group.getChildren().add(listEntity.get(i).get(j).getImageView());
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
            enemy.update();
            if (enemy.isKilled) {
                temp.add(enemy);
            }
        }
        // remove enemy after die 60 frames.
        for (Enemy enemy : temp) {
            if (enemy.getTimeDie() < 0) {
                group.getChildren().remove(enemy.getImageView());
                enemyList.remove(enemy);
            }
        }
    }

    /**
     * cac bomber xu li su kien.
     */
    public void bombersHandleInput(Group group) {

        for (Bomber bomber : bomberList) {
            bomber.update();
            if (enemyList.isEmpty()) {
                bomber.checkIsGoToPortal(portal);
            }
            if (bomber.isGoToPortal()) {
                System.out.println("qua man");
                // qua màn.
            }
            Item usedItem = bomber.getItem(itemList);
            if (usedItem != null) {
                group.getChildren().remove(usedItem.getImageView());
            }
        }
    }

}
