package Model.Audio;


import javafx.scene.media.*;

/**
 * for each button click a sound is played, this class runs in a separate thread and provides the audio fro the button click
 */
public class AudioThread extends Thread{
    /** preloads the audio and sets its volume */
    public AudioThread()
    {
        this.audio=new AudioClip(getClass().getResource("audio.wav").toExternalForm());
        this.audio.setVolume(0.2f);
    }

    private final AudioClip audio;


    public void run(){
        audio.play();
    }

}
