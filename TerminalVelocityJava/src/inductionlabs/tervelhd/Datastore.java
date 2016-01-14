package inductionlabs.tervelhd;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.content.Context;

public class Datastore implements Serializable {

    static public String FILENAME = "_spacequark";
	private static final long serialVersionUID = 1L;

	public boolean sound;
	public boolean grid;
	public boolean scrshoot;
	public boolean music;
	public boolean tut1;
	public boolean tut2;
	public boolean tut3;
	public boolean tut4;
	
	public boolean achivments[]=new  boolean[100];
	public int diffiun;
	public boolean vibrate;
	public int totalundo;
	public int totaltime;
	public int stagescompleted;
	public int achivmentunlocked;
	public int totalshake;
	public int totalwalk;
	public String realtime;
	public int stagesreplayed;
	public boolean animation;
	
	public Datastore()
	{  int i=0;
		realtime="--:--:--";
		sound=Settings.soundEnabled;
		music=Settings.musicEnabled;
		vibrate=Settings.vibrate;
	  while(i<100)
      {
      achivments[i]=false;
      i++;
      }
	}
	
	public static void save(String SAVENAME, Datastore theObjectAr,   Context context) 
	{  try{
		FileOutputStream fos = context.openFileOutput(SAVENAME, Context.MODE_PRIVATE);
    	ObjectOutputStream oos = new ObjectOutputStream(fos); 
    	oos.writeObject(theObjectAr); 
    	oos.close();
    	fos.close();
         }
	    catch(IOException e)
	    {	
	    }
	  }
	
	public static Datastore Load(String SAVENAME,    Context context) 
	{    try{ 
		FileInputStream is = context.openFileInput(SAVENAME); 
		ObjectInputStream ois = new ObjectInputStream(is);
		Datastore f = (Datastore) ois.readObject(); 
	      ois.close();  
	      is.close();
	      return f;
	   }catch(IOException e)
	    {
		   Del( Datastore.FILENAME, TerVel.contex); 
	    } catch (ClassNotFoundException e) 
	    {
	    	 Del( Datastore.FILENAME, TerVel.contex); 
	    	// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	        return null;     
	}
	public static void Del(String SAVENAME,    Context context) 
	{ context.deleteFile(SAVENAME); 
	}

}
