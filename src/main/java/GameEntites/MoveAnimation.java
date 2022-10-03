package GameEntites;

import Graphics.Animation;
import Graphics.Sprite;
import Graphics.CreateMap;
import javafx.scene.image.Image;

import java.lang.Math;

/**
 * Đối tượng có thể di chuyển.
 */
public abstract class MoveAnimation extends AnimationEntity {
    protected int speed;
    protected int width;
    protected int height;

    public MoveAnimation(int x, int y, Image image, int speed) {
        super(x, y, image);
        this.speed = speed;
    }

    //kiểm tra xem ô [row, col] trong map có chứa vật cản ko.
    private boolean checkBoxIsObstacle(int x, int y, Bomb tmp) {
        int r = (y - Sprite.MenuSize) / Sprite.SizeOfTile;
        int c = x / Sprite.SizeOfTile;
        if (CreateMap.listEntity.get(r).get(c) instanceof Obstacle) {
            return true;
        }
        for (Bomb bomb : Animation.map.bomberList.get(0).getBombList()) {
            if (!bomb.equals(tmp)) {
                if (bomb.getX() <= x && x < bomb.getX() + Sprite.SizeOfTile
                        && bomb.getY() <= x && y < bomb.getY() + Sprite.SizeOfTile) {
                    return true;
                }
            }
        }
        return false;
    }

    private Bomb bombInBox(int x, int y) {
        for (Bomb bomb : Animation.map.bomberList.get(0).getBombList()) {
            if (bomb.getX() <= x && x < bomb.getX() + Sprite.SizeOfTile
                    && bomb.getY() <= x && y < bomb.getY() + Sprite.SizeOfTile) {
                return bomb;
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
                    if (x % Sprite.SizeOfTile + (width / 3) * 2 <= Sprite.SizeOfTile) {
                        //trường hợp hơn nửa bomber theo chiều dọc nằm bên trái.
                        if (!checkBoxIsObstacle(x, y - Sprite.SizeOfTile, null)) {
                            x -= Math.min((x + width) % Sprite.SizeOfTile, tmpSpeed);
                        }
                    } else if(x % Sprite.SizeOfTile + (width / 3) >= Sprite.SizeOfTile) {
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
                    if (x % Sprite.SizeOfTile + (width / 3) * 2 <= Sprite.SizeOfTile) {
                        //trường hợp hơn nửa bomber theo chiều dọc nằm bên trái.
                        if (!checkBoxIsObstacle(x, y + height, null)) {
                            x -= Math.min((x + width) % Sprite.SizeOfTile, tmpSpeed);
                        }
                    } else if(x % Sprite.SizeOfTile + (width / 3) >= Sprite.SizeOfTile){
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
                    if ((y - Sprite.MenuSize) % Sprite.SizeOfTile + (height / 3) * 2 <= Sprite.SizeOfTile) {
                        //trường hợp hơn nửa bomber theo chiều ngang nằm bên trên.
                        if (!checkBoxIsObstacle(x - Sprite.SizeOfTile, y, null)) {
                            y -= Math.min((y + height) % Sprite.SizeOfTile, tmpSpeed);
                        }
                    } else if((y - Sprite.MenuSize) % Sprite.SizeOfTile + (height/ 3) >= Sprite.SizeOfTile) {
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
                    if ((y - Sprite.MenuSize) % Sprite.SizeOfTile + (height / 3) * 2 <= Sprite.SizeOfTile) {
                        //trường hợp hơn nửa bomber theo chiều ngang nằm bên trên.
                        if (!checkBoxIsObstacle(x + Sprite.SizeOfTile, y, null)) {
                            y -= Math.min((y + height) % Sprite.SizeOfTile, tmpSpeed);
                        }
                    } else if((y - Sprite.MenuSize) % Sprite.SizeOfTile + (height/ 3) >= Sprite.SizeOfTile) {
                        //trường hợp hơn nửa bomber theo chiều ngang nằm bên dưới.
                        if (!checkBoxIsObstacle(x + Sprite.SizeOfTile, y + Sprite.SizeOfTile, null)) {
                            y += Math.min(Sprite.SizeOfTile - (y - Sprite.MenuSize) % Sprite.SizeOfTile, tmpSpeed);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
