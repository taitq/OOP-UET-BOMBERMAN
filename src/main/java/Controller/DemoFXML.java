package Controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;

public class DemoFXML {
    @FXML
    public ImageView imageView;

    public void up(ActionEvent event) {
        System.out.println("up");
        imageView.setY(imageView.getY() - 10);
    }

    public void down(ActionEvent event) {
        System.out.println("down");
        imageView.setY(imageView.getY() + 20);
    }

    public void right(ActionEvent event) {
        System.out.println("right");
        imageView.setX(imageView.getX() + 20);
    }

    public void left(ActionEvent event) {
        System.out.println("left");
        imageView.setX(imageView.getX() - 20);
    }
}
