package GameObjects.Sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound 
{
    private final URL shoot;
    private final URL hit;
    private final URL destroy;
    private final URL music;
    public Object play_music;
    public Sound() 
    {
        this.shoot = this.getClass().getClassLoader().getResource("GameObjects/Sound/Shoot.wav");
        this.hit = this.getClass().getClassLoader().getResource("GameObjects/Sound/Hit.wav");
        this.destroy =  this.getClass().getClassLoader().getResource("GameObjects/Sound/Destroy.wav");
        this.music = this.getClass().getClassLoader().getResource("GameObjects/Sound/Music.wav");
    }

    public void sound_shoot()
    {
        play(shoot);
    }
    public void sound_hit()
    {
        play(hit);
    }
    public void sound_destroy()
    {
        play(destroy);
    }
    public void play_music()
    {
        play(music);
    }

    private void play(URL url)
        {
            try
            {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.addLineListener(new LineListener() {
                    @Override
                    public void update(LineEvent event)
                        {
                            if(event.getType()==LineEvent.Type.STOP)
                            {
                                clip.close();
                            }
                        }
                });
                audioIn.close();
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.err.println(e);
            }
        }
}
