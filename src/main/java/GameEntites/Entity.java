package GameEntites;

import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Thực thể game tổng quát.
 */
public abstract class Entity {
    // Gốc tọa độ là góc trái trên cùng.
    // Hoành độ của thực thể.
    protected int x;
    // Tung độ của thực thể.
    protected int y;
    protected Image image;
    protected ImageView imageView;

    /**
     * Khởi tạo thực thể với tọa độ (x,y) có ảnh là image.
     *
     * @param x     hoành độ.
     * @param y     tung độ.
     * @param image ảnh của thực thể.
     */
    public Entity(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.relocate(x, y);
    }
    /**
     * Vẽ thực thể lên màn hình.
     * @param graphicsContext bút vẽ.
     */
    public void DrawEntity(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image,x,y);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImage(Image image) {
        this.image = image;
        imageView.setImage(this.image);
    }

    public Image getImage() {
        return image;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }

}