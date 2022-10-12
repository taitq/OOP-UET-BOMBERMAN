package GameEntites;

import Graphics.CreateMap;
import Graphics.Sprite;
import javafx.scene.image.Image;

/**
 * Brick là đối tượng được đặt lên các ô Grass, không cho phép đặt Bomb lên nhưng có thể bị phá hủy bởi Bomb được đặt gần đó.
 * Bomber và Enemy thông thường không thể di chuyển vào vị trí Brick khi nó chưa bị phá hủy.
 */
public class Brick extends UnmoveEntity implements Obstacle {

    public Brick(int x, int y, Image image, Image[] images) {
        super(x, y, image, images);
        MAX_TIME = 45;
    }

    // gọi là convert sang grass nhưng vì có background là grass rồi
    // nên để image của grass này là null để nếu có item sau brick, thì item sẽ ko bị che bởi grass.
     public void convertBrickToGeass() {
         Grass grass = new Grass(getX(), getY(), null);
         int r = (getY() - Sprite.MenuSize) / Sprite.SizeOfTile;
         int c = getX() / Sprite.SizeOfTile;
         CreateMap.listEntity.get(r).get(c).getImageView().setImage(null);
         CreateMap.listEntity.get(r).set(c, grass);
     }
}
