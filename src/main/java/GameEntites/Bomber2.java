package GameEntites;

import Graphics.Audio;
import Graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;


public class Bomber2 extends Bomber {

    public Bomber2(int x, int y, Image image, int speed, Scene scene) {
        super(x, y, image, speed, scene);
        live = true;
        //this.numberOfBomb = Math.max(1, numberOfBomb);
        levelOfFlame = 1;
        width = 22;
        height = 30;
        bombList = new ArrayList<>();
        keyListener = new Bomber2.KeyListener(scene);
        isRunning = false;
        isGoToPortal = false;
    }

    //hàm thay đổi ảnh của bomber khi di chuyển.
    protected void changeImage() {
        if (!isRunning) {
            switch (direction) {
                case 'd' -> setImage(Sprite.player2_down[0]);
                case 'u' -> setImage(Sprite.player2_up[0]);
                case 'l' -> setImage(Sprite.player2_left[0]);
                case 'r' -> setImage(Sprite.player2_right[0]);
                default -> setImage(Sprite.player2_down[0]);
            }
        } else {
            time++;
            if (time == MAX_TIME) {
                resetTime();
            }
        }
        imageView.relocate(x, y);
    }

    // hàm di chuyển của bomber khi nhận sự kiện bàn phím.
    @Override
    protected void move() {
        boolean isPressed = false;
        if (keyListener.isPressed(KeyCode.W)) {
            moveUp();
            if (direction != 'u' || !isRunning) {
                resetTime();
            }
            isPressed = true;
            isRunning = true;
            setImage(Sprite.player2_up[(time / 5 + 1) % 3]);
            direction = 'u';
        }
        if (keyListener.isPressed(KeyCode.S)) {
            moveDown();
            if (direction != 'd' || !isRunning) {
                resetTime();
            }
            isPressed = true;
            isRunning = true;
            setImage(Sprite.player2_down[(time / 5 + 1) % 3]);
            direction = 'd';
        }
        if (keyListener.isPressed(KeyCode.A)) {
            moveLeft();
            if (direction != 'l' || !isRunning) {
                resetTime();
            }
            isPressed = true;
            isRunning = true;
            setImage(Sprite.player2_left[(time / 5 + 1) % 3]);
            direction = 'l';
        }
        if (keyListener.isPressed(KeyCode.D)) {
            moveRight();
            if (direction != 'r' || !isRunning) {
                resetTime();
            }
            isPressed = true;
            isRunning = true;
            setImage(Sprite.player2_right[(time / 5 + 1) % 3]);
            direction = 'r';
        }
        if (keyListener.isPressed(KeyCode.ENTER)) {
            setBomb(x + width / 2, y + height / 2);
        }
        isRunning = isPressed;
    }


    /**
     * describe state be killed of bomber
     */
    public void killed() {
        Audio.bomberDie.setVolume(0.5);
        Audio.bomberDie.play();
        Audio.bomberDie.setOnEndOfMedia(Audio.bomberDie::stop);
        setImage(Sprite.player2_dead[0]);
    }


}
