package pokedex.audio;

import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
@Component
public class AudioPlayer {

    private Clip clip;

    public void play(String audioFilePath) {
        try {
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
