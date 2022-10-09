package GameEntites;

import Graphics.CreateMap;
import Graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Bomb là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass.
 * Khi đã được kích hoạt, Bomber và Enemy không thể di chuyển vào vị trí Bomb.
 * Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình,
 * Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 2s,
 * Bomb sẽ tự nổ, các đối tượng Flame  được tạo ra.
 */
public class Bomb extends UnmoveEntity implements Obstacle {
    private int remainingFrame;
    private boolean explode;
    public List<Flame> flameList = new ArrayList<>();

    public int getRemainingFrame() {
        return remainingFrame;
    }

    public void setRemainingFrame(int remainingFrame) {
        this.remainingFrame = remainingFrame;
    }

    public Bomb(int x, int y, Image image) {
        super(x, y, image);
        remainingFrame = 120;
        explode = false;
    }

    @Override
    public void update() {
        remainingFrame--;
        // update image nữa và remainingFrame = 0 thì chuyển sang trạng thái nố;
        if (remainingFrame == 0) {
            explode = true;
            int r = (y - Sprite.MenuSize) / Sprite.SizeOfTile;
            int c = x / Sprite.SizeOfTile;
            // make flameList when set bomb
            imageView.setImage(Sprite.bomb_exploded[0]);
            if (!(CreateMap.listEntity.get(r).get(c + 1) instanceof Obstacle)) {
                Flame flame = new Flame(x + Sprite.SizeOfBomb, y, Sprite.explosion_horizontal_right_last[0]);
                flameList.add(flame);
            }
            if (!(CreateMap.listEntity.get(r).get(c - 1) instanceof Obstacle)) {
                Flame flame = new Flame(x - Sprite.SizeOfBomb, y, Sprite.explosion_horizontal_left_last[0]);
                flameList.add(flame);
            }
            if (!(CreateMap.listEntity.get(r - 1).get(c) instanceof Obstacle)) {
                Flame flame = new Flame(x, y - Sprite.SizeOfBomb, Sprite.explosion_vertical_top_last[0]);
                flameList.add(flame);
            }
            if (!(CreateMap.listEntity.get(r + 1).get(c) instanceof Obstacle)) {
                Flame flame = new Flame(x, y + Sprite.SizeOfBomb, Sprite.explosion_vertical_down_last[0]);
                flameList.add(flame);
            }

            if (CreateMap.listEntity.get(r).get(c + 1) instanceof Brick) {
                CreateMap.listEntity.get(r).get(c + 1).getImageView().setImage(Sprite.grass);
                Grass grass = new Grass(x + Sprite.SizeOfBomb, y, Sprite.grass);
                CreateMap.listEntity.get(r).set(c + 1, grass);
            }
            if (CreateMap.listEntity.get(r).get(c - 1) instanceof Brick) {
                CreateMap.listEntity.get(r).get(c - 1).getImageView().setImage(Sprite.grass);
                Grass grass = new Grass(x - Sprite.SizeOfBomb, y, Sprite.grass);
                CreateMap.listEntity.get(r).set(c - 1, grass);
            }
            if (CreateMap.listEntity.get(r - 1).get(c) instanceof Brick) {
                CreateMap.listEntity.get(r - 1).get(c).getImageView().setImage(Sprite.grass);
                Grass grass = new Grass(x, y - Sprite.SizeOfBomb, Sprite.grass);
                CreateMap.listEntity.get(r - 1).set(c, grass);
            }
            if (CreateMap.listEntity.get(r + 1).get(c) instanceof Brick) {
                CreateMap.listEntity.get(r + 1).get(c).getImageView().setImage(Sprite.grass);
                Grass grass = new Grass(x, y + Sprite.SizeOfBomb, Sprite.grass);
                CreateMap.listEntity.get(r + 1).set(c, grass);
            }
            if (remainingFrame == 0) {
                setImage(Sprite.bomb_exploded[0]);
            }
        }
    }
}
