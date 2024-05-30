package pokedex.audio;

import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
@Component
public class AudioPlayer {

    private Clip clip;
    private Clip clip2;

    public void click(){
        try {
            File audioFile2 = new File("src/main/resources/audio/click.wav");
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            clip2 = AudioSystem.getClip();
            clip2.open(audioStream2);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
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
        System.out.println("no se me para");
        clip.close();
    }
}
