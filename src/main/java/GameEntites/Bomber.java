package GameEntites;

import Graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.List;
import java.util.ArrayList;

public class Bomber extends MoveAnimation {
    private List<Bomb> bombList = new ArrayList<>();
    private int numberOfBomb;
    // bien kiem tra xem bomber live or die.
    private static boolean live = true;

    public Bomber(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        //this.numberOfBomb = Math.max(1, numberOfBomb);
        numberOfBomb = 3;
        width = 22;
        height = 30;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        Bomber.live = live;
    }

    public int getNumberOfBomb() {
        return numberOfBomb;
    }

    public void setNumberOfBomb(int numberOfBomb) {
        this.numberOfBomb = numberOfBomb;
    }

    public List<Bomb> getBombList() {
        return bombList;
    }

    /**
     * nhận sự kiện di chuyển từ bàn phím cho bomber và xóa bomb đã nổ ra khỏi bombList.
     */
    public void update(Scene scene) {
        removeBomb();
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                moveUp();
            } else if (event.getCode() == KeyCode.DOWN) {
                moveDown();
            } else if (event.getCode() == KeyCode.LEFT) {
                moveLeft();
            } else if (event.getCode() == KeyCode.RIGHT) {
                moveRight();
            }
            if (event.getCode() == KeyCode.SPACE) {
                setBomb(x + width / 2, y + height / 2);
            }
        });
        imageView.relocate(x, y);
    }

    /**
     * xóa những bomb đã nổ.
     */
    private void removeBomb() {
        for(Bomb bomb: bombList) {
            if(bomb.getRemainingFrame() < -40) {
                bombList.remove(bomb);
            }
        }
    }

    /**
     * Đặt thêm 1 quả bomb mới.
     */
    private void setBomb(int x, int y) {
        if (bombList.size() < numberOfBomb) {
            // làm tròn tọa độ x, y.
            x -= x % Sprite.SizeOfTile;
            y -= (y - Sprite.MenuSize) % Sprite.SizeOfTile;

            //kiểm tra xem ô này đã có bomb đc đặt hay chưa.
            boolean status = false;
            for (Bomb bomb : bombList) {
                if (bomb.getX() == x && bomb.getY() == y) {
                    status = true;
                }
            }
            if (!status) {
                bombList.add(new Bomb(x, y, Sprite.bomb));
            }
        }
    }

    /**
     * check collison with enemy.
     *
     * @param enemyList danh sach enemy.
     * @return true if collison with enemy, false if not collison.
     */
    public boolean checkCollisonEnemy(List<Enemy> enemyList) {
        for (Enemy enemy : enemyList) {
            if (imageView.getBoundsInParent().intersects(enemy.getImageView().getBoundsInParent())) {
                System.out.println("aaa");
                live = false;
                return true;
            }
            ;
        }
        return false;
    }
}
