package GameEntites;

import Graphics.Animation;
import Graphics.CreateMap;
import Graphics.Sprite;
import javafx.scene.image.Image;

import java.util.Random;

//enemy di chuyen ngau nhien và có thể tránh bomb.
public class KondoriaEnemy extends Enemy {
    private char[] direction = {'u', 'd', 'l', 'r'};
    private final int[] row = {-1, 1, 0, 0};
    private final int[] col = {0, 0, -1, 1};
    private char oldDirection;
    private int countDown = 0;
    private final int R = CreateMap.COLUMN;
    private final int C = CreateMap.ROW;
    private int[][] type = new int[R][C];

    public KondoriaEnemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        enemy_left = Sprite.kondoria_left;
        enemy_right = Sprite.kondoria_right;
        enemy_dead = Sprite.kondoria_dead;
    }

    @Override
    public char getDirection() {
        if (countDown > 0 && oldDirection != 'n') {
            countDown--;
            return oldDirection;
        }
        countDown = 30 / speed;
        updateType();
        Random random = new Random();
        int r = (y - Sprite.MenuSize + height / 2 - 1) / Sprite.SizeOfTile;
        int c = (x + width / 2 - 1) / Sprite.SizeOfTile;
        int minType = 10;
        int oppositeDirection = 0;
        for (int i = 0; i < 4; i++) {
            if (oldDirection == direction[i]) {
                if (i % 2 == 0) {
                    oppositeDirection = i + 1;
                } else {
                    oppositeDirection = i - 1;
                }
                break;
            }
        }
        if (type[r][c] == 0) {
            for (int i = 0; i < 4; i++) {
                if (i != oppositeDirection) {
                    if (type[r + row[i]][c + col[i]] >= 0) {
                        minType = Math.min(minType, type[r + row[i]][c + col[i]]);
                    }
                }
            }
            if (minType == 0) {
                int temp = random.nextInt(4);
                while (temp == oppositeDirection || type[r + row[temp]][c + col[temp]] != minType) {
                    temp = random.nextInt(4);
                }
                oldDirection = direction[temp];
            } else {
                minType = Math.min(minType, type[r + row[oppositeDirection]][c + col[oppositeDirection]]);
                int temp = random.nextInt(4);
                while (type[r + row[temp]][c + col[temp]] != minType) {
                    temp = random.nextInt(4);
                }
                if(minType == 0) {
                    oldDirection = direction[temp];
                } else {
                    oldDirection = 'n';
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                if (type[r + row[i]][c + col[i]] >= 0) {
                    minType = Math.min(minType, type[r + row[i]][c + col[i]]);
                }
            }
            int temp = random.nextInt(4);
            while (type[r + row[temp]][c + col[temp]] != minType) {
                temp = random.nextInt(4);
            }
            oldDirection = direction[temp];
        }
        return oldDirection;
    }


    //-1 là obstacle, 0 là grass, -2 là bomb, >= 1 là flame.
    private void updateType() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (CreateMap.listEntity.get(i).get(j) instanceof Grass) {
                    type[i][j] = 0;
                } else {
                    type[i][j] = -1;
                }
            }
        }
        for (Bomber bomber : Animation.map.bomberList) {
            for (Bomb bomb : bomber.getBombList()) {
                int u = (bomb.getY() - Sprite.MenuSize) / Sprite.SizeOfTile;
                int v = (bomb.getX()) / Sprite.SizeOfTile;
                for (int i = 0; i < 4; i++) {
                    for (int k = 1; k <= bomb.getLevelOfFlame(); k++) {
                        if (CreateMap.listEntity.get(u + row[i] * k).get(v + col[i] * k) instanceof Obstacle) {
                            break;
                        } else {
                            type[u + row[i] * k][v + col[i] * k] = bomb.getLevelOfFlame() - k + 1;
                        }
                    }
                }
            }
            for (Bomb bomb : bomber.getBombList()) {
                int u = (bomb.getY() - Sprite.MenuSize) / Sprite.SizeOfTile;
                int v = (bomb.getX()) / Sprite.SizeOfTile;
                type[u][v] = -2;
            }
        }
    }
}
