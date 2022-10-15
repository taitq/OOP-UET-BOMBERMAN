package GameEntites;

import Graphics.CreateMap;
import Graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Bomb là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass.
 * Khi đã được kích hoạt, Bomber và Enemy không thể di chuyển vào vị trí Bomb.
 * Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình,
 * Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 2s,
 * Bomb sẽ tự nổ, các đối tượng Flame  được tạo ra.
 */
public class Bomb extends UnmoveEntity implements Obstacle {
    private int remainingFrame;
    private int levelOfFlame;
    private boolean explode;
    public List<Flame> flameList = new ArrayList<>();
    // danh sach brick bị nổ khi bomb nổ.
    private List<Brick> brickList = new ArrayList<>();
    //tren duoi trai phair
    private int[] row = {0, 0, -1, 1};
    private int[] col = {-1, 1, 0, 0};
    private Image[][] explosion = {
            Sprite.explosion_vertical,
            Sprite.explosion_vertical,
            Sprite.explosion_horizontal,
            Sprite.explosion_horizontal,
    };
    private Image[][] explosion_last = {
            Sprite.explosion_vertical_top_last,
            Sprite.explosion_vertical_down_last,
            Sprite.explosion_horizontal_left_last,
            Sprite.explosion_horizontal_right_last,
    };

    public int getLevelOfFlame() {
        return levelOfFlame;
    }

    public int getRemainingFrame() {
        return remainingFrame;
    }

    public void setRemainingFrame(int remainingFrame) {
        this.remainingFrame = remainingFrame;
    }

    public Bomb(int x, int y, Image image, int levelOfFlame) {
        super(x, y, image, Sprite.bomb);
        remainingFrame = 90;
        this.levelOfFlame = levelOfFlame;
        explode = false;
        MAX_TIME = 60;
    }

    private boolean checkObstacle(int r, int c) {
        return (CreateMap.listEntity.get(r).get(c) instanceof Obstacle);
    }

    @Override
    public void update() {
        remainingFrame--;
        int tmp = (remainingFrame % 30) / 10;
        // update animation của bomb như bthg.
        if(remainingFrame >= 0) {
            super.update();
        }
        //bomb bắt đầu nổ.s
        if(remainingFrame == -1) {
            explode = true;
            flameList.add(new Flame(x, y, null, Sprite.bomb_exploded));
            // lấy tọa độ r,c của bomb trên map.
            int r = (y - Sprite.MenuSize) / Sprite.SizeOfTile;
            int c = x / Sprite.SizeOfTile;
            for(int i = 0; i < 4; i++) {
                for(int k = 1; k <= levelOfFlame; k++) {
                    int tmpR = r + col[i] * k;
                    int tmpC = c + row[i] * k;
                    if (!checkObstacle(tmpR, tmpC)) {
                        int tmpX = x + row[i] * k * Sprite.SizeOfTile;
                        int tmpY = y + col[i] * k * Sprite.SizeOfTile;
                        if (k == levelOfFlame) {
                            flameList.add(new Flame(tmpX, tmpY, null, explosion_last[i]));
                        } else {
                            flameList.add(new Flame(tmpX, tmpY, null, explosion[i]));
                        }
                    } else {
                        if (CreateMap.listEntity.get(tmpR).get(tmpC) instanceof Brick) {
                            brickList.add((Brick) CreateMap.listEntity.get(tmpR).get(tmpC));
                        }
                        break;
                    }
                }
            }
        }
        if (explode) {
            for (Flame flame : flameList) {
                flame.update();
            }
            for (Brick brick : brickList) {
                brick.update();
            }
        }
    }

    //chuyển brick thành grass sau khi bomb nổ xong.
    public void updateBrick() {
        for (Brick brick : brickList) {
            brick.convertBrickToGeass();
        }
    }
}
