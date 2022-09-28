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

    public Bomber(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        //this.numberOfBomb = Math.max(1, numberOfBomb);
        numberOfBomb = 10;
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
            } else if (event.getCode() == KeyCode.SPACE) {
                setBomb(x + 11, y + 15);
            }
        });
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
        if (bombList.size() == numberOfBomb) {
            throw new IllegalArgumentException("The number of booms placed exceeds the limit");
        } else {
            // làm tròn tọa độ x, y.
            x -= x % Sprite.SizeOfTile;
            y -= (y - Sprite.MenuSize) % Sprite.SizeOfTile;

            //kiểm tra xem ô này đã có bomb đc đặt hay chưa.
            for (Bomb bomb : bombList) {
                if (bomb.getX() == x && bomb.getY() == y) {
                    throw new IllegalArgumentException("This box already has bombs");
                }
            }
            bombList.add(new Bomb(x, y, Sprite.bombImage));
        }
    }
}
