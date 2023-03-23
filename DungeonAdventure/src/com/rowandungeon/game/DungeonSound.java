package com.rowandungeon.game;

import com.rowandungeon.levels.DungeonLevel1;

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 *
 * @author 1939056
 */
public class DungeonSound 
{
    DungeonLevel1 level1;
    public static synchronized void play(InputStream soundResource, boolean isMusic)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(soundResource);
                    clip.open(ais);
                    clip.start();
                    if(isMusic == true)
                        clip.loop(clip.LOOP_CONTINUOUSLY);
                }catch(Exception ex)
                {
                    System.out.println("Error playing sound " + ex.getMessage());
                }
                
            }
        }).start();
    }
    
    
}
