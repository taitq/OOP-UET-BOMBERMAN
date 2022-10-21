package GameEntites;

import Graphics.Animation;
import Graphics.CreateMap;
import Graphics.Sprite;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

// enemy tự động tìm đường di chuyển đến bomber và biết tránh bomb.
public class DollEnemy extends Enemy {
    private final int[] row = {-1, 1, 0, 0};
    private final int[] col = {0, 0, -1, 1};
    private final char[] direction = {'u', 'd', 'l', 'r'};
    private final int R = CreateMap.COLUMN;
    private final int C = CreateMap.ROW;
    private final int MAX1 = 100000;
    private final int MAX2 = 1000000;
    private int[][] depth = new int[R][C];
    private int[][] type = new int[R][C];

    public DollEnemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        enemy_left = Sprite.doll_left;
        enemy_right = Sprite.doll_right;
        enemy_dead = Sprite.doll_dead;
    }

    @Override
    public char getDirection() {
        BFS();
        int r = (y - Sprite.MenuSize + height / 2 - 1) / Sprite.SizeOfTile;
        int c = (x + width / 2 - 1) / Sprite.SizeOfTile;
        for(int i = 0; i < 4; i++) {
            if(depth[r + row[i]][c + col[i]] + 1 == depth[r][c]) {
                return direction[i];
            }
        }
        int tmpX = c * Sprite.SizeOfTile;
        int tmpY = r * Sprite.SizeOfTile + Sprite.MenuSize;
        if(x != tmpX) {
            if(x < tmpX) return 'r';
            return 'l';
        }
        if(y != tmpY) {
            if(y < tmpY) return 'd';
            return 'u';
        }
        return 'n';

    }

    private void BFS() {
        updateType();
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        int rE = (y - Sprite.MenuSize + height / 2 - 1) / Sprite.SizeOfTile;
        int cE = (x + width / 2 - 1) / Sprite.SizeOfTile;
        boolean enemyInFlame = false;
        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                depth[i][j] = MAX1;
            }
        }
        if(type[rE][cE] == 1) {
            enemyInFlame = true;
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if(type[i][j] == 0) {
                        depth[i][j] = 0;
                        queue.add(new Pair(i, j));
                    }
                }
            }
        }
        for(Bomber bomber : Animation.map.bomberList) {
            int r = (bomber.getY() - Sprite.MenuSize + bomber.height / 2 - 1) / Sprite.SizeOfTile;
            int c = (bomber.getX() + bomber.width / 2 - 1) / Sprite.SizeOfTile;
            if(type[r][c] != 2) {
                depth[r][c] = 0;
                queue.add(new Pair(r, c));
            }
        }

        while (!queue.isEmpty()) {
            int r = queue.peek().getKey();
            int c = queue.peek().getValue();
            queue.remove();
            for (int i = 0; i < 4; i++) {
                if (type[r + row[i]][c + col[i]] == 0
                        || (type[r + row[i]][c + col[i]] == 1 && enemyInFlame == true)
                        || (r + row[i] == rE && c + col[i] == cE)) {
                    if (depth[r + row[i]][c + col[i]] > depth[r][c] + 1) {
                        depth[r + row[i]][c + col[i]] = depth[r][c] + 1;
                        queue.add(new Pair(r + row[i], c + col[i]));
                    }
                }
            }
        }
    }

    //-1 là obstacle, 0 là grass, 1 là flame, 2 là bomb.
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
                            type[u + row[i] * k][v + col[i] * k] = 1;
                        }
                    }
                }
            }
            for (Bomb bomb : bomber.getBombList()) {
                int u = (bomb.getY() - Sprite.MenuSize) / Sprite.SizeOfTile;
                int v = (bomb.getX()) / Sprite.SizeOfTile;
                type[u][v] = 2;
            }
        }
    }

}
