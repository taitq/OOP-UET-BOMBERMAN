package GameEntites;

import Graphics.Sprite;
import javafx.scene.image.Image;

/**
 * Bomb là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass.
 * Khi đã được kích hoạt, Bomber và Enemy không thể di chuyển vào vị trí Bomb.
 * Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình,
 * Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 2s,
 * Bomb sẽ tự nổ, các đối tượng Flame  được tạo ra.
 */
public class Bomb extends UnmoveEntity implements Obstacle {
    private int remainingFrame;

    public int getRemainingFrame() {
        return remainingFrame;
    }

    public void setRemainingFrame(int remainingFrame) {
        this.remainingFrame = remainingFrame;
    }

    public Bomb(int x, int y, Image image) {
        super(x, y, image);
        remainingFrame = 120;
    }

    public void update() {
        remainingFrame--;
        // update image nữa và remainingFrame = 0 thì chuyển sang trạng thái nố;
        if(remainingFrame == 0) {
            setImage(Sprite.bombExplodedImage);
        }
    }
}
