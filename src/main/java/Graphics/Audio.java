package Graphics;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;


public class Audio {

    public static final MediaPlayer background = media("backGround");
    public static final MediaPlayer lobby = media("lobby");
    public static final MediaPlayer setBomb = media("setBomb");
    public static final MediaPlayer explosion = media("explosion");
    public static final MediaPlayer menuSelect = media("menuSelect");
    public static final MediaPlayer bomberDie = media("bomberDie");
    public static final MediaPlayer item = media("item");
    public static final MediaPlayer enemyDie = media("enemyDie");

    public static MediaPlayer media(String name) {
        try {
            String path = "src/main/resources/Music/" + name + ".mp3";
            File mediaFile = new File(path);
            Media media = new Media(mediaFile.toURI().toURL().toString());

            return new MediaPlayer(media);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
