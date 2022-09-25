package GameEntites;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyListener;

public class Bomber extends MoveAnimation {
    private int numberOfBomb;

    public Bomber(int x, int y, Image image, int speed, int numberOfBomb) {
        super(x, y, image, speed);
        this.numberOfBomb = Math.max(1, numberOfBomb);
    }

    /**
     * nhận sự kiện di chuyển từ bàn phím cho bomber.
     */
    public void handleInput(Scene scene) {
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
                //dat bomb
            }
        });
    }
}
