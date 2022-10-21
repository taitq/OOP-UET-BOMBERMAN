package GameEntites;

import Graphics.Audio;
import Graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.control.skin.TextInputControlSkin;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

import java.util.List;

/**
 * Đối tượng quái.
 */
public abstract class Enemy extends MoveAnimation {
    protected Image[] enemy_left;
    protected Image[] enemy_right;
    protected Image enemy_dead;
    //hướng của ảnh enemy.
    private char directionOfImage = 'l';
    //hướng di chuyển tiếp theo của enemy.
    private char newDirection;
    private int time = 0;
    private final int MAX_TIME = 30;


    public boolean isKilled = false;
    // thoi gian tu luc enemy chet toi luc enemy bi xoa khoi man hinh
    private int timeDie = 60;

    public Enemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        width = 30;
        height = 30;
    }
    private void resetTime() {
        time = 0;
    }

    @Override
    public void update() {
        if(isKilled) {
            timeDie--;
            return;
        }
        newDirection = getDirection();
        switch (newDirection) {
            case 'u' -> moveUp();
            case 'd' -> moveDown();
            case 'l' -> moveLeft();
            case 'r' -> moveRight();
        }
        if(directionOfImage == 'l' && newDirection == 'r') {
            resetTime();
            directionOfImage = 'r';
        }
        if(directionOfImage == 'r' && newDirection == 'l') {
            resetTime();
            directionOfImage = 'l';
        }
        if(directionOfImage == 'l') {
            setImage(enemy_left[time / (MAX_TIME / 3)]);
        } else {
            setImage(enemy_right[time / (MAX_TIME / 3)]);
        }
        time++;
        if(time == MAX_TIME) resetTime();
        imageView.relocate(x, y);
    }

    /**
     * check flame kill enemy.
     *
     * @param flameList list flame.
     */
    public void checkcollisonFlame(List<Flame> flameList) {
        for (Flame flame : flameList) {
            if (checkCollisonRectangle(flame.getX(), flame.getY(), Sprite.SizeOfTile, Sprite.SizeOfTile)) {
                killed();
                return;
            }
        }
    }

    /**
     * describe state killed of enemy.
     */
    public void killed() {
        isKilled = true;
        if (Audio.sound) {
            Audio.enemyDie.play();
            Audio.enemyDie.setOnEndOfMedia(Audio.enemyDie::stop);
        }
        setImage(enemy_dead);
    }

    public int getTimeDie() {
        return timeDie;
    }

    public void setTimeDie(int timeDie) {
        this.timeDie = timeDie;
    }
    public abstract char getDirection();


    public char randomDirection() {
        Random random = new Random();
        int direction = random.nextInt(4);
        char tmp = 'u';
        switch (direction) {
            case 0 -> {
                tmp = 'u';
            }
            case 1 -> {
                tmp = 'd';
            }
            case 2 -> {
                moveLeft();
                tmp = 'l';
            }
            case 3 -> {
                tmp = 'r';
            }
        }
        return tmp;
    }

}
