package GameEntites;

import Graphics.Animation;
import Graphics.CreateMap;
import Graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class Bomber extends MoveAnimation {
    private List<Bomb> bombList = new ArrayList<>();
    private int numberOfBomb;
    private int levelOfFlame;
    // biến đếm ngược frame để thay đổi image.
    private int time;
    // biến kiểm tra xem bomber có di chuyển không.
    private boolean isRunning;
    // hướng di chuyển hiện tại của bomber.
    private char direction = 'd';
    // bien kiem tra xem bomber live or die.
    private static boolean live = true;
    // bien kiem tra xem bomber di vao portal chua.
    private boolean isGoToPortal = false;
    private KeyListener keyListener;
    private final int MAX_TIME = 15;
    public Bomber(int x, int y, Image image, int speed, Scene scene) {
        super(x, y, image, speed);
        //this.numberOfBomb = Math.max(1, numberOfBomb);
        numberOfBomb = 10;
        levelOfFlame = 1;
        width = 22;
        height = 30;
        keyListener = new KeyListener(scene);
    }

    private class KeyListener implements EventHandler<KeyEvent> {
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

    // hàm khởi tạo lại giá trị của biến time.
    private void resetTime() {
        time = 0;
    }

    /**
     * cập nhật các trạng thái cho bomber.
     */
    @Override
    public void update() {
        removeBomb();
        if(live == false) {
            Animation.gameOver = true;
        }
        move();
        changeImage();
    }

    //hàm thay đổi ảnh của bomber khi di chuyển.
    private void changeImage() {
        if(isRunning == false) {
            switch (direction) {
                case 'd' -> setImage(Sprite.player_down[0]);
                case 'u' -> setImage(Sprite.player_up[0]);
                case 'l' -> setImage(Sprite.player_left[0]);
                case 'r' -> setImage(Sprite.player_right[0]);
                default -> setImage(Sprite.player_down[0]);
            }
        } else {
            time++;
            if(time == MAX_TIME) {
                resetTime();
            }
        }
        imageView.relocate(x, y);
    }

    /**
     * xóa những bomb đã nổ.
     */
    private void removeBomb() {
        List<Bomb> tmp = new ArrayList<>();
        for(Bomb bomb : bombList) {
            if(bomb.getRemainingFrame() <= -45) {
                bomb.updateBrick();
                tmp.add(bomb);
            }
        }
        for(Bomb bomb : tmp) {
            bombList.remove(bomb);
        }
    }

    // hàm di chuyển của bomber khi nhận sự kiện bàn phím.
    private void move() {
        boolean isPressed = false;
        if(keyListener.isPressed(KeyCode.UP)) {
            moveUp();
            if(direction != 'u' || !isRunning) {
                resetTime();
            }
            isPressed = true;
            isRunning = true;
            setImage(Sprite.player_up[(time / 5 + 1) % 3]);
            direction = 'u';
        }
        if(keyListener.isPressed(KeyCode.DOWN)) {
            moveDown();
            if(direction != 'd' || !isRunning) {
                resetTime();
            }
            isPressed = true;
            isRunning = true;
            setImage(Sprite.player_down[(time / 5 + 1) % 3]);
            direction = 'd';
        }
        if(keyListener.isPressed(KeyCode.LEFT)) {
            moveLeft();
            if(direction != 'l' || !isRunning) {
                resetTime();
            }
            isPressed = true;
            isRunning = true;
            setImage(Sprite.player_left[(time / 5 + 1) % 3]);
            direction = 'l';
        }
        if(keyListener.isPressed(KeyCode.RIGHT)) {
            moveRight();
            if(direction != 'r' || !isRunning) {
                resetTime();
            }
            isPressed = true;
            isRunning = true;
            setImage(Sprite.player_right[(time / 5 + 1) % 3]);
            direction = 'r';
        }
        if(keyListener.isPressed(KeyCode.SPACE)) {
            setBomb(x + width / 2, y + height / 2);
        }
        isRunning = isPressed;
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
     * @return true if collison with enemy, false if not collison.
     */
    public boolean checkCollisonEnemy(List<Enemy> enemyList) {
        for (Enemy enemy : enemyList) {
            if (checkCollisonRectangle(enemy.getX(), enemy.getY(), enemy.width, enemy.height)) {
                killed();
                live = false;
                return true;
            }
        }
        return false;
    }

    /**
     * check flame kill bomber.
     *
     * @param flameList list flame.
     * @return true if killed by flame, false if not.
     */

    public boolean checkCollisonFlame(List<Flame> flameList) {
        for (Flame flame : flameList) {
            if(checkCollisonRectangle(flame.getX(), flame.getY(), Sprite.SizeOfTile, Sprite.SizeOfTile)) {
                killed();
                live = false;
                return true;
            }
        }
        return false;
    }

    /**
     * describe state be killed of bomber
     */
    public void killed() {
        setImage(Sprite.player_dead[0]);
    }

    //hàm kiểm tra bomber đi vào portal khi hết enemy chưa.
    public void checkIsGoToPortal(Portal portal) {
        int tmpX = x + width / 2;
        int tmpY = y + height / 2;
        if(tmpX >= portal.getX() && tmpX < portal.getX() + Sprite.SizeOfTile
            && tmpY >= portal.getY() && tmpY < portal.getY() + Sprite.SizeOfTile) {
            isGoToPortal = true;
        }
    }

    public Item getItem(List<Item> itemList) {
        Item usedItem = null;
        for(Item item : itemList) {
            int tmpX = x + width / 2;
            int tmpY = y + height / 2;
            if(tmpX >= item.getX() && tmpX < item.getX() + Sprite.SizeOfTile
                    && tmpY >= item.getY() && tmpY < item.getY() + Sprite.SizeOfTile) {
                usedItem = item;
            }
        }
        if(usedItem instanceof FlameItem) {
            levelOfFlame++;
        } else if(usedItem instanceof BombItem) {
            numberOfBomb++;
        } else if(usedItem instanceof SpeedItem) {
            speed++;
        } else {

        }
        itemList.remove(usedItem);
        return usedItem;
    }

    public boolean isGoToPortal() {
        return isGoToPortal;
    }

}
