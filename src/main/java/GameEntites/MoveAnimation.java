package GameEntites;

import Graphics.Animation;
import Graphics.Sprite;
import Graphics.CreateMap;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.event.KeyListener;
import java.lang.Math;
import java.util.HashSet;
import java.util.Set;

/**
 * Đối tượng có thể di chuyển.
 */
public abstract class MoveAnimation extends AnimationEntity {
    protected int speed;
    protected int width;
    protected int height;
    protected boolean wallPass;

    public MoveAnimation(int x, int y, Image image, int speed) {
        super(x, y, image);
        this.speed = speed;
        wallPass = false;
    }

    //kiểm tra xem ô có nằm trong map ko, trừ viền.
    private boolean isValidBox(int r, int c) {
        return r > 0 && r < CreateMap.COLUMN - 1 && c > 0 && c < CreateMap.ROW - 1;
    }

    //kiểm tra xem ô [row, col] trong map có chứa vật cản ko.
    private boolean checkBoxIsObstacle(int x, int y, Bomb tmp) {
        int r = (y - Sprite.MenuSize) / Sprite.SizeOfTile;
        int c = x / Sprite.SizeOfTile;
        if (CreateMap.listEntity.get(r).get(c) instanceof Obstacle) {
            if(wallPass && CreateMap.listEntity.get(r).get(c) instanceof Wall && isValidBox(r, c)) {
                return false;
            }
            return true;
        }
        for (Bomber bomber : CreateMap.bomberList) {
            for (Bomb bomb : bomber.getBombList()) {
                if (!bomb.equals(tmp)) {
                    if (bomb.getX() <= x && x < bomb.getX() + Sprite.SizeOfTile
                            && bomb.getY() <= y && y < bomb.getY() + Sprite.SizeOfTile) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //kiểm tra xem ô(x,y) có bomb ko.
    private Bomb bombInBox(int x, int y) {
        for (Bomber bomber : CreateMap.bomberList) {
            for (Bomb bomb : bomber.getBombList()) {
                if (bomb.getX() <= x && x < bomb.getX() + Sprite.SizeOfTile
                        && bomb.getY() <= y && y < bomb.getY() + Sprite.SizeOfTile) {
                    return bomb;
                }
            }
        }

        return null;
    }

    public void moveUp() {
        Bomb tmp = bombInBox(x + width / 2, y + height / 2);
        int tmpSpeed = speed;
        while (tmpSpeed > 0 && !checkBoxIsObstacle(x, y - 1, tmp) && !checkBoxIsObstacle(x + width - 1, y - 1, tmp)) {
            y--;
            tmpSpeed--;
        }
        if (tmpSpeed > 0) {
            //nâng cấp khả năng di chuyển của bomber.
            if ((y - Sprite.MenuSize) % Sprite.SizeOfTile == 0) {
                if (x % Sprite.SizeOfTile + width > Sprite.SizeOfTile) {
                    if (x % Sprite.SizeOfTile + width / 2 <= Sprite.SizeOfTile) {
                        //trường hợp hơn nửa bomber theo chiều dọc nằm bên trái.
                        if (!checkBoxIsObstacle(x, y - Sprite.SizeOfTile, null)) {
                            x -= Math.min((x + width) % Sprite.SizeOfTile, tmpSpeed);
                        }
                    } else if(x % Sprite.SizeOfTile + width / 2 >= Sprite.SizeOfTile) {
                        //trường hợp hơn nửa bomber theo chiều dọc nằm bên phải.
                        if (!checkBoxIsObstacle(x + width, y - Sprite.SizeOfTile, null)) {
                            x += Math.min(Sprite.SizeOfTile - x % Sprite.SizeOfTile, tmpSpeed);
                        }
                    }
                }
            }
        }
    }

    public void moveDown() {
        Bomb tmp = bombInBox(x + width / 2, y + height / 2);
        int tmpSpeed = speed;
        while (tmpSpeed > 0 && !checkBoxIsObstacle(x, y + height, tmp) && !checkBoxIsObstacle(x + width - 1, y + height, tmp)) {
            y++;
            tmpSpeed--;
        }
        if (tmpSpeed > 0) {
            //nâng cấp khả năng di chuyển của bomber.
            if ((y + height - Sprite.MenuSize) % Sprite.SizeOfTile == 0) {
                if (x % Sprite.SizeOfTile + width > Sprite.SizeOfTile) {
                    if (x % Sprite.SizeOfTile + (width / 2) <= Sprite.SizeOfTile) {
                        //trường hợp hơn nửa bomber theo chiều dọc nằm bên trái.
                        if (!checkBoxIsObstacle(x, y + height, null)) {
                            x -= Math.min((x + width) % Sprite.SizeOfTile, tmpSpeed);
                        }
                    } else if(x % Sprite.SizeOfTile + (width / 2) >= Sprite.SizeOfTile){
                        //trường hợp hơn nửa bomber theo chiều dọc nằm bên phải.
                        if (!checkBoxIsObstacle(x + width, y + height, null)) {
                            x += Math.min(Sprite.SizeOfTile - x % Sprite.SizeOfTile, tmpSpeed);
                        }
                    }
                }
            }
        }
    }

    public void moveLeft() {
        Bomb tmp = bombInBox(x + width / 2, y + height / 2);
        int tmpSpeed = speed;
        while (tmpSpeed > 0 && !checkBoxIsObstacle(x - 1, y, tmp) && !checkBoxIsObstacle(x - 1, y + height - 1, tmp)) {
            x--;
            tmpSpeed--;
        }
        if (tmpSpeed > 0) {
            //nâng cấp khả năng di chuyển của bomber.
            if (x % Sprite.SizeOfTile == 0) {
                if ((y - Sprite.MenuSize) % Sprite.SizeOfTile + height > Sprite.SizeOfTile) {
                    if ((y - Sprite.MenuSize) % Sprite.SizeOfTile + (height / 2) <= Sprite.SizeOfTile) {
                        //trường hợp hơn nửa bomber theo chiều ngang nằm bên trên.
                        if (!checkBoxIsObstacle(x - Sprite.SizeOfTile, y, null)) {
                            y -= Math.min((y + height) % Sprite.SizeOfTile, tmpSpeed);
                        }
                    } else if((y - Sprite.MenuSize) % Sprite.SizeOfTile + (height/ 2) >= Sprite.SizeOfTile) {
                        //trường hợp hơn nửa bomber theo chiều ngang nằm bên dưới.
                        if (!checkBoxIsObstacle(x - Sprite.SizeOfTile, y + Sprite.SizeOfTile, null)) {
                            y += Math.min(Sprite.SizeOfTile - (y - Sprite.MenuSize) % Sprite.SizeOfTile, tmpSpeed);
                        }
                    }
                }
            }
        }
    }

    public void moveRight() {
        Bomb tmp = bombInBox(x + width / 2, y + height / 2);
        int tmpSpeed = speed;
        while (tmpSpeed > 0 && !checkBoxIsObstacle(x + width, y, tmp) && !checkBoxIsObstacle(x + width, y + height - 1, tmp)) {
            x++;
            tmpSpeed--;
        }
        if (tmpSpeed > 0) {
            //nâng cấp khả năng di chuyển của bomber.
            if ((x + width) % Sprite.SizeOfTile == 0) {
                if ((y - Sprite.MenuSize) % Sprite.SizeOfTile + height > Sprite.SizeOfTile) {
                    if ((y - Sprite.MenuSize) % Sprite.SizeOfTile + (height / 2) <= Sprite.SizeOfTile) {
                        //trường hợp hơn nửa bomber theo chiều ngang nằm bên trên.
                        if (!checkBoxIsObstacle(x + Sprite.SizeOfTile, y, null)) {
                            y -= Math.min((y + height) % Sprite.SizeOfTile, tmpSpeed);
                        }
                    } else if((y - Sprite.MenuSize) % Sprite.SizeOfTile + (height / 2) >= Sprite.SizeOfTile) {
                        //trường hợp hơn nửa bomber theo chiều ngang nằm bên dưới.
                        if (!checkBoxIsObstacle(x + Sprite.SizeOfTile, y + Sprite.SizeOfTile, null)) {
                            y += Math.min(Sprite.SizeOfTile - (y - Sprite.MenuSize) % Sprite.SizeOfTile, tmpSpeed);
                        }
                    }
                }
            }
        }
    }

    public boolean checkCollisonRectangle(int x, int y, int w, int h) {
        int u = Math.min(getX() + width, x + w) - Math.max(getX(), x);
        int v = Math.min(getY() + height, y + h) - Math.max(getY(), y);
        if (u >= 0 && v >= 0) {
            if (u * v * 6 >= width * height) return true;
        }
        return false;
    }

    public int getSpeed() {
        return speed;
    }
}
