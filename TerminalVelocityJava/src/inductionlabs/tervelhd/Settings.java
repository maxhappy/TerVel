package inductionlabs.tervelhd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


import com.badlogic.androidgames.framework.FileIO;

public class Settings {
	
    public static boolean soundEnabled ;
    public static boolean musicEnabled ;
	//public static boolean sound;
	//public static boolean music;
	public static boolean vibrate;
	public static float soundvolume=0.8F;
	public static float musicvolume=0.3F;
	final public static int musicindex=0;
    
    public final static int[] highscores = new int[] {0,0,0,0,0,0,0,0,0,0};
    public final static String file = ".tervel";

    public static void load(FileIO files) 
    {    BufferedReader in = null;
               try {
        	   
               
            in = new BufferedReader(new InputStreamReader(files.readFile(file)));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            musicEnabled = Boolean.parseBoolean(in.readLine());
            for(int i = 0; i < 5; i++) 
            {
                highscores[i] = Integer.parseInt(in.readLine());
            }
        } catch (IOException e) {
            // :( It's ok we have defaults
        } catch (NumberFormatException e) {
            // :/ It's ok, defaults save our day
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    public static void save(FileIO files) 
    {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                   files.writeFile(file)));
            out.write(Boolean.toString(soundEnabled));
            out.write("\n");
            out.write(Boolean.toString(musicEnabled));
            out.write("\n");
            for(int i = 0; i < 5; i++) {
                out.write(Integer.toString(highscores[i]));
                out.write("\n");
            }

        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }

    public static void addScore(int score)
    {
        for(int i=0; i < 10; i++) 
        {
         
        	if(highscores[i]< score)
            {
                for(int j= 9; j > i; j--)
                    highscores[j] = highscores[j-1];
                highscores[i] = score;
                break;
            }
        	
        }
    }

	public static void save(FileIO fileiohandle, int score)
	{
    addScore(score);
	save(TerVel.fileiohandle);
	}
}
