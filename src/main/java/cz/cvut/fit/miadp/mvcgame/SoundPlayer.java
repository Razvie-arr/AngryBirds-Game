package cz.cvut.fit.miadp.mvcgame;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {
    private final String sound;

    public SoundPlayer(String sound) {
        this.sound = sound;
    }

    public void playSound() {
        try {
            Clip clip = AudioSystem.getClip();
            URL url = this.getClass().getClassLoader().getResource("sounds/" + sound + ".wav");
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {}
    }
}
