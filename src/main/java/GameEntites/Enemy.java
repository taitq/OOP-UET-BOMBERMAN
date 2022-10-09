package GameEntites;

import javafx.scene.image.Image;

import java.util.List;

/**
 * Đối tượng quái.
 */
public abstract class Enemy extends MoveAnimation {
    public boolean isKilled = false;
    // thoi gian tu luc enemy chet toi luc enemy bi xoa khoi man hinh
    private int timeDie = 120;

    public Enemy(int x, int y, Image image, int speed) {
        super(x, y, image, speed);
        width = 30;
        height = 30;
    }

    public abstract void move();

    /**
     * check flame kill enemy.
     *
     * @param flameList list flame.
     */
    public void checkcollisonFlame(List<Flame> flameList) {
        for (Flame flame : flameList) {
            if (imageView.getBoundsInParent().intersects(flame.getImageView().getBoundsInParent())) {
                isKilled = true;
                killed();
                return;
            }
        }
    }

    /**
     * describe state killed of enemy.
     */
    public abstract void killed();

    public int getTimeDie() {
        return timeDie;
    }

    public void setTimeDie(int timeDie) {
        this.timeDie = timeDie;
    }
}
