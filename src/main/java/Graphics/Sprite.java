package Graphics;

import com.sun.glass.ui.Pixels;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import org.w3c.dom.css.RGBColor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sprite {
    // Kích cỡ ảnh đối tượng Grass, Wall , Brick ,Portal.
    public static final int SizeOfTile = 30;
    // Kích cỡ ảnh của Bomber, Enemy
    public static final int SizeOfCharacter = 30;
    // Kích cỡ ảnh của item
    public static final int SizeOfItem = 30;
    // Kích cỡ bom
    public static int SizeOfBomb = 30;
    // Kích thước thanh menu trong game
    public static int MenuSize = 30;
    public static int SizeOfFlame = 30;


    /*
    public static final Image wall = newImage("wall", SizeOfTile, SizeOfTile) ;
    public static final Image brick = newImage("brick", SizeOfTile, SizeOfTile);
    public static final Image portal = newImage("portal", SizeOfTile, SizeOfTile);
    public static final Image grass = newImage("grass", SizeOfTile, SizeOfTile);
    public static final Image player_right_1 = newImage("player_right_1", SizeOfCharacter, SizeOfCharacter);
    public static final Image bomb = newImage("bomb", SizeOfTile, SizeOfTile);
    public static final Image bomb_exploded = newImage("bomb_exploded", SizeOfTile, SizeOfTile);
    public static final Image balloonEnemy = newImage("balloom_right1", SizeOfCharacter, SizeOfCharacter);
    public static final Image gameOver = newImage("gameOver", 930, 420);
    */
    public static final Image balloon_dead = newImage("balloom_dead", SizeOfTile, SizeOfTile);
    public static final Image[] balloon_left = {
            newImage("balloom_left1", SizeOfTile, SizeOfTile),
            newImage("balloom_left2", SizeOfTile, SizeOfTile),
            newImage("balloom_left3", SizeOfTile, SizeOfTile),
    };
    public static final Image[] balloon_right = {
            newImage("balloom_right1", SizeOfTile, SizeOfTile),
            newImage("balloom_right2", SizeOfTile, SizeOfTile),
            newImage("balloom_right3", SizeOfTile, SizeOfTile),
    };
    public static final Image[] bomb = {
            newImage("bomb", SizeOfTile, SizeOfTile),
            newImage("bomb_1", SizeOfTile, SizeOfTile),
            newImage("bomb_2", SizeOfTile, SizeOfTile),
    };
    public static final Image[] bomb_exploded = {
            newImage("bomb_exploded", SizeOfTile, SizeOfTile),
            newImage("bomb_exploded1", SizeOfTile, SizeOfTile),
            newImage("bomb_exploded2", SizeOfTile, SizeOfTile),
    };
    public static final Image brick = newImage("brick", SizeOfTile, SizeOfTile);
    public static final Image[] brick_exploded = {
            newImage("brick_exploded", SizeOfTile, SizeOfTile),
            newImage("brick_exploded1", SizeOfTile, SizeOfTile),
            newImage("brick_exploded2", SizeOfTile, SizeOfTile),
    };
    public static final Image doll_dead = newImage("doll_dead", SizeOfTile, SizeOfTile);
    public static final Image[] doll_left = {
            newImage("doll_left1", SizeOfTile, SizeOfTile),
            newImage("doll_left2", SizeOfTile, SizeOfTile),
            newImage("doll_left3", SizeOfTile, SizeOfTile),
    };
    public static final Image[] doll_right = {
            newImage("doll_right1", SizeOfTile, SizeOfTile),
            newImage("doll_right2", SizeOfTile, SizeOfTile),
            newImage("doll_right3", SizeOfTile, SizeOfTile),
    };
    public static final Image[] explosion_horizontal = {
            newImage("explosion_horizontal", SizeOfFlame, SizeOfFlame),
            newImage("explosion_horizontal1", SizeOfFlame, SizeOfFlame),
            newImage("explosion_horizontal2", SizeOfFlame, SizeOfFlame),
    };
    public static final Image[] explosion_horizontal_left_last = {
            newImage("explosion_horizontal_left_last", SizeOfFlame, SizeOfFlame),
            newImage("explosion_horizontal_left_last1", SizeOfFlame, SizeOfFlame),
            newImage("explosion_horizontal_left_last2", SizeOfFlame, SizeOfFlame),
    };
    public static final Image[] explosion_horizontal_right_last = {
            newImage("explosion_horizontal_right_last", SizeOfFlame, SizeOfFlame),
            newImage("explosion_horizontal_right_last1", SizeOfFlame, SizeOfFlame),
            newImage("explosion_horizontal_right_last2", SizeOfFlame, SizeOfFlame),
    };
    public static final Image[] explosion_vertical = {
            newImage("explosion_vertical", SizeOfFlame, SizeOfFlame),
            newImage("explosion_vertical1", SizeOfFlame, SizeOfFlame),
            newImage("explosion_vertical2", SizeOfFlame, SizeOfFlame),
    };
    public static final Image[] explosion_vertical_top_last = {
            newImage("explosion_vertical_top_last", SizeOfFlame, SizeOfFlame),
            newImage("explosion_vertical_top_last1", SizeOfFlame, SizeOfFlame),
            newImage("explosion_vertical_top_last2", SizeOfFlame, SizeOfFlame),
    };
    public static final Image[] explosion_vertical_down_last = {
            newImage("explosion_vertical_down_last", SizeOfFlame, SizeOfFlame),
            newImage("explosion_vertical_down_last1", SizeOfFlame, SizeOfFlame),
            newImage("explosion_vertical_down_last2", SizeOfFlame, SizeOfFlame),
    };
    public static final Image gameOver = newImage("gameOver", CreateMap.WIDTH, CreateMap.HEIGHT);
    public static final Image grass = newImage("grass", SizeOfTile, SizeOfTile);
    public static final Image kondoria_dead = newImage("kondoria_dead", SizeOfTile, SizeOfTile);
    public static final Image[] kondoria_left = {
            newImage("kondoria_left1", SizeOfTile, SizeOfTile),
            newImage("kondoria_left2", SizeOfTile, SizeOfTile),
            newImage("kondoria_left3", SizeOfTile, SizeOfTile),
    };
    public static final Image[] kondoria_right = {
            newImage("kondoria_right1", SizeOfTile, SizeOfTile),
            newImage("kondoria_right2", SizeOfTile, SizeOfTile),
            newImage("kondoria_right3", SizeOfTile, SizeOfTile),
    };
    public static final Image minvo_dead = newImage("minvo_dead", SizeOfTile, SizeOfTile);
    public static final Image[] minvo_left = {
            newImage("minvo_left1", SizeOfTile, SizeOfTile),
            newImage("minvo_left2", SizeOfTile, SizeOfTile),
            newImage("minvo_left3", SizeOfTile, SizeOfTile),
    };
    public static final Image[] minvo_right = {
            newImage("minvo_right1", SizeOfTile, SizeOfTile),
            newImage("minvo_right2", SizeOfTile, SizeOfTile),
            newImage("minvo_right3", SizeOfTile, SizeOfTile),
    };
    public static final Image[] mob_dead = {
            newImage("mob_dead1", SizeOfTile, SizeOfTile),
            newImage("mob_dead2", SizeOfTile, SizeOfTile),
            newImage("mob_dead3", SizeOfTile, SizeOfTile),
    };
    public static final Image oneal_dead = newImage("oneal_dead", SizeOfTile, SizeOfTile);
    public static final Image[] oneal_left = {
            newImage("oneal_left1", SizeOfTile, SizeOfTile),
            newImage("oneal_left2", SizeOfTile, SizeOfTile),
            newImage("oneal_left3", SizeOfTile, SizeOfTile),
    };
    public static final Image[] oneal_right = {
            newImage("oneal_right1", SizeOfTile, SizeOfTile),
            newImage("oneal_right2", SizeOfTile, SizeOfTile),
            newImage("oneal_right3", SizeOfTile, SizeOfTile),
    };
    public static final Image ovapi_dead = newImage("ovapi_dead", SizeOfTile, SizeOfTile);
    public static final Image[] ovapi_left = {
            newImage("ovapi_left1", SizeOfTile, SizeOfTile),
            newImage("ovapi_left2", SizeOfTile, SizeOfTile),
            newImage("ovapi_left3", SizeOfTile, SizeOfTile),
    };
    public static final Image[] ovapi_right = {
            newImage("ovapi_right1", SizeOfTile, SizeOfTile),
            newImage("ovapi_right2", SizeOfTile, SizeOfTile),
            newImage("ovapi_right3", SizeOfTile, SizeOfTile),
    };
    public static final Image pass_dead = newImage("pass_dead", SizeOfTile, SizeOfTile);
    public static final Image[] pass_left = {
            newImage("pass_left1", SizeOfTile, SizeOfTile),
            newImage("pass_left2", SizeOfTile, SizeOfTile),
            newImage("pass_left3", SizeOfTile, SizeOfTile),
    };
    public static final Image[] pass_right = {
            newImage("pass_right1", SizeOfTile, SizeOfTile),
            newImage("pass_right2", SizeOfTile, SizeOfTile),
            newImage("pass_right3", SizeOfTile, SizeOfTile),
    };
    public static final Image[] player_dead = {
            newImage("player_dead1", SizeOfTile, SizeOfTile),
            newImage("player_dead2", SizeOfTile, SizeOfTile),
            newImage("player_dead3", SizeOfTile, SizeOfTile),
    };
    public static final Image[] player_down = {
            newImage("player_down", SizeOfTile, SizeOfTile),
            newImage("player_down_1", SizeOfTile, SizeOfTile),
            newImage("player_down_2", SizeOfTile, SizeOfTile),
    };
    public static final Image[] player_left = {
            newImage("player_left", SizeOfTile, SizeOfTile),
            newImage("player_left_1", SizeOfTile, SizeOfTile),
            newImage("player_left_2", SizeOfTile, SizeOfTile),
    };
    public static final Image[] player_right = {
            newImage("player_right", SizeOfTile, SizeOfTile),
            newImage("player_right_1", SizeOfTile, SizeOfTile),
            newImage("player_right_2", SizeOfTile, SizeOfTile),
    };
    public static final Image[] player_up = {
            newImage("player_up", SizeOfTile, SizeOfTile),
            newImage("player_up_1", SizeOfTile, SizeOfTile),
            newImage("player_up_2", SizeOfTile, SizeOfTile),
    };
    public static final Image portal = newImage("portal", SizeOfTile, SizeOfTile);
    public static final Image powerup_bombpass = newImage("powerup_bombpass", SizeOfTile, SizeOfTile);
    public static final Image powerup_bombs = newImage("powerup_bombs", SizeOfTile, SizeOfTile);
    public static final Image powerup_detonator = newImage("powerup_detonator", SizeOfTile, SizeOfTile);
    public static final Image powerup_flamepass = newImage("powerup_flamepass", SizeOfTile, SizeOfTile);
    public static final Image powerup_flames = newImage("powerup_flames", SizeOfTile, SizeOfTile);
    public static final Image powerup_speed = newImage("powerup_speed", SizeOfTile, SizeOfTile);
    public static final Image powerup_wallpass = newImage("powerup_wallpass", SizeOfTile, SizeOfTile);
    public static final Image wall = newImage("wall", SizeOfTile, SizeOfTile);

    //hàm set image rút gọn.
    private static Image newImage(String name, int w, int h) {
        Image image = null;
        try {
            image = makeTransparent(new Image(Files.newInputStream(Paths.get("src/main/resources/sprites/" + name + ".png")), w, h, true, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    // remove background
    public static Image makeTransparent(Image inputImage) {
        int W = (int) inputImage.getWidth();
        int H = (int) inputImage.getHeight();
        WritableImage outputImage = new WritableImage(W, H);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int argb = reader.getArgb(x, y);

                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;

                if (r == 0xFF && g == 0 && b == 0xFF) {
                    argb &= 0x00FFFFFF;
                }
                writer.setArgb(x, y, argb);
            }
        }

        return outputImage;
    }

}

