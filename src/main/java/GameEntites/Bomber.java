package GameEntites;

import Graphics.Audio;
import Graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bomber extends MoveAnimation {
    protected List<Bomb> bombList;
    protected int numberOfBomb;
    protected int levelOfFlame;
    // biến đếm ngược frame để thay đổi image.
    protected int time;
    // biến kiểm tra xem bomber có di chuyển không.
    protected boolean isRunning;
    // hướng di chuyển hiện tại của bomber.
    protected char direction = 'd';
    // bien kiem tra xem bomber live or die.
    protected boolean live;
    // bien kiem tra xem bomber di vao portal chua.
    protected static boolean isGoToPortal;
    protected static KeyListener keyListener;
    // bien dem nguoc thoi gian die la 60 frame.
    protected int timeDie;
    // so luong bomber con song.
    public static int numberBomberLive = 0;
    protected final int MAX_TIME = 15;

    public Bomber(int x, int y, Image image, int speed, Scene scene) {
        super(x, y, image, speed);
        live = true;
        //this.numberOfBomb = Math.max(1, numberOfBomb);
        numberOfBomb = 1;
        levelOfFlame = 1;
        width = 22;
        height = 30;
        bombList = new ArrayList<>();
        keyListener = new KeyListener(scene);
        isRunning = false;
        isGoToPortal = false;
        timeDie = 60;
        numberBomberLive++;
        numberOfBomb = 1;
    }

    protected class KeyListener implements EventHandler<KeyEvent> {
        final private Set<KeyCode> activeKeys = new HashSet<>();

        public KeyListener(Scene scene) {
            scene.setOnKeyPressed(this);
            scene.setOnKeyReleased(this);
        }

        @Override
        public void handle(KeyEvent event) {
            if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
                activeKeys.add(event.getCode());
            } else if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
                activeKeys.remove(event.getCode());
            }
        }

        public boolean isPressed(KeyCode keyCode) {
            return activeKeys.contains(keyCode);
        }
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
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

    // hàm khởi tạo lại giá trị của biến time.
    protected void resetTime() {
        time = 0;
    }

    /**
     * cập nhật các trạng thái cho bomber.
     */
    @Override
    public void update() {
        removeBomb();
        if (!live) {
            killed();
            timeDie--;
            return;
        }
        move();
        changeImage();
    }

    //hàm thay đổi ảnh của bomber khi di chuyển.
    protected void changeImage() {
        if (!isRunning) {
            switch (direction) {
                case 'd' -> setImage(Sprite.player_down[0]);
                case 'u' -> setImage(Sprite.player_up[0]);
                case 'l' -> setImage(Sprite.player_left[0]);
                case 'r' -> setImage(Sprite.player_right[0]);
                default -> setImage(Sprite.player_down[0]);
            }
        } else {
            time++;
            if (time == MAX_TIME) {
                resetTime();
            }
        }
        imageView.relocate(x, y);
    }

    /**
     * xóa những bomb đã nổ.
     */
    protected void removeBomb() {
        List<Bomb> tmp = new ArrayList<>();
        for (Bomb bomb : bombList) {
            if (bomb.getRemainingFrame() <= -45) {
                bomb.updateBrick();
                tmp.add(bomb);
            }
        }
        for (Bomb bomb : tmp) {
            bombList.remove(bomb);
        }
    }

    // hàm di chuyển của bomber khi nhận sự kiện bàn phím.
    protected void move() {
        if (live) {
            boolean isPressed = false;
            if (keyListener.isPressed(KeyCode.UP)) {
                moveUp();
                if (direction != 'u' || !isRunning) {
                    resetTime();
                }
                isPressed = true;
                isRunning = true;
                setImage(Sprite.player_up[(time / 5 + 1) % 3]);
                direction = 'u';
            }
            if (keyListener.isPressed(KeyCode.DOWN)) {
                moveDown();
                if (direction != 'd' || !isRunning) {
                    resetTime();
                }
                isPressed = true;
                isRunning = true;
                setImage(Sprite.player_down[(time / 5 + 1) % 3]);
                direction = 'd';
            }
            if (keyListener.isPressed(KeyCode.LEFT)) {
                moveLeft();
                if (direction != 'l' || !isRunning) {
                    resetTime();
                }
                isPressed = true;
                isRunning = true;
                setImage(Sprite.player_left[(time / 5 + 1) % 3]);
                direction = 'l';
            }
            if (keyListener.isPressed(KeyCode.RIGHT)) {
                moveRight();
                if (direction != 'r' || !isRunning) {
                    resetTime();
                }
                isPressed = true;
                isRunning = true;
                setImage(Sprite.player_right[(time / 5 + 1) % 3]);
                direction = 'r';
            }
            if (keyListener.isPressed(KeyCode.SPACE)) {
                setBomb(x + width / 2, y + height / 2);
            }
            isRunning = isPressed;
        }

    }

    /**
     * Đặt thêm 1 quả bomb mới.
     */
    protected void setBomb(int x, int y) {
        if (bombList.size() < numberOfBomb) {
            if (Audio.sound) {
                Audio.setBomb.play();
                Audio.setBomb.setOnEndOfMedia(Audio.setBomb::stop);
            }

            // làm tròn tọa độ x, y.
            x -= x % Sprite.SizeOfTile;
            y -= (y - Sprite.MenuSize) % Sprite.SizeOfTile;

            //kiểm tra xem ô này đã có bomb đc đặt hay chưa.
            boolean status = false;
            for (Bomb bomb : bombList) {
                if (bomb.getX() == x && bomb.getY() == y) {
                    status = true;
                    break;
                }
            }
            if (!status) {
                bombList.add(new Bomb(x, y, Sprite.bomb[0], levelOfFlame));
            }
        }
    }

    /**
     * check collison with enemy.
     *
     * @param enemyList danh sach enemy.
     */
    public void checkCollisonEnemy(List<Enemy> enemyList) {
        for (Enemy enemy : enemyList) {
            if (checkCollisonRectangle(enemy.getX(), enemy.getY(), enemy.width, enemy.height)) {
                killed();
                live = false;
                return;
            }
        }
    }

    /**
     * check flame kill bomber.
     *
     * @param flameList list flame.
     */

    public void checkCollisonFlame(List<Flame> flameList) {
        for (Flame flame : flameList) {
            if (checkCollisonRectangle(flame.getX(), flame.getY(), Sprite.SizeOfTile, Sprite.SizeOfTile)) {
                killed();
                live = false;
                return;
            }
        }
    }

    /**
     * describe state be killed of bomber
     */
    public void killed() {
        if (Audio.sound) {
            Audio.bomberDie.setVolume(0.5);
            Audio.bomberDie.play();
            Audio.bomberDie.setOnEndOfMedia(Audio.bomberDie::stop);
        }
        setImage(Sprite.player_dead[0]);
    }

    //hàm kiểm tra bomber đi vào portal khi hết enemy chưa.
    public void checkIsGoToPortal(Portal portal) {
        int tmpX = x + width / 2;
        int tmpY = y + height / 2;
        if (tmpX >= portal.getX() && tmpX < portal.getX() + Sprite.SizeOfTile
                && tmpY >= portal.getY() && tmpY < portal.getY() + Sprite.SizeOfTile) {
            isGoToPortal = true;
        }
    }

    public Item getItem(List<Item> itemList) {
        Item usedItem = null;
        for (Item item : itemList) {
            int tmpX = x + width / 2;
            int tmpY = y + height / 2;
            if (tmpX >= item.getX() && tmpX < item.getX() + Sprite.SizeOfTile
                    && tmpY >= item.getY() && tmpY < item.getY() + Sprite.SizeOfTile) {
                if (Audio.sound) {
                    Audio.item.play();
                    Audio.item.setOnEndOfMedia(Audio.item::stop);
                }
                usedItem = item;
            }
        }
        if (usedItem instanceof FlameItem) {
            levelOfFlame++;
        } else if (usedItem instanceof BombItem) {
            numberOfBomb++;
        } else if (usedItem instanceof SpeedItem) {
            speed++;
        } else {

        }
        itemList.remove(usedItem);
        return usedItem;
    }

    public static boolean isGoToPortal() {
        return isGoToPortal;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setTimeDie(int timeDie) {
        this.timeDie = timeDie;
    }

    public int getTimeDie() {
        return timeDie;
    }
}
