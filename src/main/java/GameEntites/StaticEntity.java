package GameEntites;

import javafx.scene.image.Image;

/**
 * Đối tượng không thay đổi trong chương trình game.
 */
public abstract class StaticEntity extends Entity {

    @Override
    public void setX(int x) {
        System.out.println("Can't change X.");
    }

    @Override
    public void setY(int y) {
        System.out.println("Can't change y.");
    }

    @Override
    public void setImage(Image image) {
        System.out.println("Can't change image of Entity");
    }

    public StaticEntity(int x, int y, Image image) {
        super(x, y, image);
    }
}
