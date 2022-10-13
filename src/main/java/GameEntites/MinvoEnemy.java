package GameEntites;

import Graphics.Animation;
import Graphics.CreateMap;
import Graphics.Sprite;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

// enemy tự động tìm đường di chuyển đến bomber nhưng không biết tránh bomb.
public class MinvoEnemy extends Enemy {
    private final int[] row = {-1, 1, 0, 0};
    private final int[] col = {0, 0, -1, 1};
    private final char[] direction = {'u', 'd', 'l', 'r'};
    private final int R = CreateMap.COLUMN;
    private final int C = CreateMap.ROW;
    private int[][] depth = new int[R][C];

    public MinvoEnemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        enemy_left = Sprite.minvo_left;
        enemy_right = Sprite.minvo_right;
        enemy_dead = Sprite.minvo_dead;
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
        return 'u';
    }

    private void BFS() {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                depth[i][j] = Integer.MAX_VALUE;
            }
        }
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        for(Bomber bomber : Animation.map.bomberList) {
            int r = (bomber.getY() - Sprite.MenuSize + bomber.height / 2 - 1) / Sprite.SizeOfTile;
            int c = (bomber.getX() + bomber.width / 2 - 1) / Sprite.SizeOfTile;
            depth[r][c] = 0;
            queue.add(new Pair(r, c));
        }
        while(!queue.isEmpty()) {
            int r = queue.peek().getKey();
            int c = queue.peek().getValue();
            queue.remove();
            for(int i = 0; i < 4; i++) {
                if(CreateMap.listEntity.get(r + row[i]).get(c + col[i]) instanceof Grass) {
                    if(depth[r + row[i]][c + col[i]] > depth[r][c] + 1) {
                        depth[r + row[i]][c + col[i]] = depth[r][c] + 1;
                        queue.add(new Pair(r + row[i], c + col[i]));
                    }
                }
            }
        }
    }
}
