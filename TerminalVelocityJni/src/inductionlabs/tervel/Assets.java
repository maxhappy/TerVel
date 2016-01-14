package inductionlabs.tervel;

import java.util.ArrayList;
import java.util.List;
import libgdxextension.FontExtension;
import android.content.Context;
import android.os.Vibrator;

import com.badlogic.androidgames.framework.Music;
import com.badlogic.androidgames.framework.Sound;
import com.badlogic.androidgames.framework.gl.Animation;
import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLGame;
public class Assets 
{
	public static int max_tex=20;
	public static int max_texrg=1000;
	public static float screenHeight=480;
	public static float screenWidth=320;
	public static  Texture splashscreen;
	public static  TextureRegion loadscreen,circ,loading;
	public static Texture [] textures;
    public static TextureRegion [] textureregions;
    public static Animation [] animations;
    public static FontExtension [] fonts;
    public static List<String> TextureNames = new ArrayList<String>(60);
    public static TexturRegionName TextureRegionNames =new TexturRegionName();
    public static List<String> SoundNames = new ArrayList<String>();
    public static List<String> FontNames = new ArrayList<String>();
    public static List<String> MusicNames = new ArrayList<String>();
    public static Music []music;
    public static Sound [] sounds;
    static int texcount=0;
	static int texregcount=0;
	static int fontcount=0;
	static int soundcount=0;
	static int MusicCount=0;
	public static int loaderp;
	private static GLGame game;
	public static boolean keyhandle;
	public static int fps;
	public static int vibra=-1;
	public static int performtask;
	public static boolean quit;
	
    public static void load(GLGame game)
    {
    	//Load Textures and define texture regions LoadFonts///////////
          Assets.game=game; 
    	  splashscreen = new Texture(game, "splash.png" );
          loadscreen = new TextureRegion(splashscreen,  1, 205, 800, 600);
          circ = new TextureRegion(splashscreen, 1, 83,120,120);
          loading = new TextureRegion(splashscreen, 803,705,156,55);
         // bgload();
          
          
          
          
          final Runnable bg = new Runnable() 
          {@Override
			public void run() 
			{
			 bgload();
			}
		  };
          GLGame.glView.queueEvent(bg);
          
          
          
           
          
     }
     
    public static void bgload()
    { 	
    	
    	
    	
        texcount=0;
        texregcount=0;
        fontcount=0;
    	loaderparser.loadindex(TerVel.fileiohandle,"asset.items");
    	Settings.soundvolume=.7f;
    	Settings.musicvolume=.3f;
    	
    	
    	//////////////////////////////////////////////////////////////
    	
    	textures=new Texture[TextureNames.size()];
    	textureregions=new TextureRegion[TextureRegionNames.size()];    	
    	sounds=new Sound[SoundNames.size()];
        music=new Music[MusicNames.size()];
    	fonts=new FontExtension[2];
        music[Settings.musicindex] = game.getAudio().newMusic(MusicNames.get(Settings.musicindex));
        music[Settings.musicindex].setLooping(true);
        music[Settings.musicindex].setVolume(Settings.musicvolume);
        
        if(Settings.musicEnabled)
        music[Settings.musicindex].play();
        
        adjustvolume(Settings.musicvolume,1);
        adjustvolume(Settings.soundvolume,0);
        
        
        //load all textures reported by pack files/////////////
        int i=0;
        while(i<TextureNames.size())
    	{ textures[i]= new Texture(game,TextureNames.get(i) );
        i++;
    	}
        Assets.loaderp=60;
        i=0;
        ///////////////////////////////Load fonts//////////////////////////////////
        while(i<2)
    	{String[] s={"font1","font2"};
    	 Integer[] w={50,50,50,50};
    	int x=TexturRegionName.x.get(TexturRegionName.texturegionname.indexOf(s[i]))+9;
        int y=TexturRegionName.y.get(TexturRegionName.texturegionname.indexOf(s[i]))+11;
        fonts[i]=new FontExtension(textures[TextureNames.indexOf(FontNames.get(0))],x,y,16,w[i],w[i+2]);
         i++;
    	}
        i=0;
        Assets.loaderp=65;
     //////////////load all textures regions reported by pack files////////////////
        while(i<TexturRegionName.texturename.size())
    	{int x,y,w,h;
        	x=TexturRegionName.x.get(i);
        	y=TexturRegionName.y.get(i);
        	w=TexturRegionName.sizex.get(i);
        	h=TexturRegionName.sizey.get(i);
        textureregions[i] = new TextureRegion(gettexture(TexturRegionName.texturename.get(i)), x,y,w,h);
        i++;
        Assets.loaderp=65+30*(i*100/TexturRegionName.texturename.size())/100;
    	}i=0;
    	Assets.loaderp=95;
    ///////////////load all sounds reported by pack files/////////////////////////
    	 sounds[0] =  game.getAudio().newSound(SoundNames.get(0));
    	 sounds[1] =  game.getAudio().newSound(SoundNames.get(2));
    	 sounds[2] =  game.getAudio().newSound(SoundNames.get(1));
    	 
    	 /*/
    	  while(i<SoundNames.size())
    	 
    	{ sounds[1] =  game.getAudio().newSound(SoundNames.get(i));
    	i++;
    	}
    	//*/
    	Assets.loaderp=100;
    //////////////////////////////////////////Load all Patterns/////////////////////////////
    	
    }       
    
    ///////////////////////////////////////////Reload Textures.///////////////////////////////
    public static void reload() 
    {
    	 Assets.splashscreen.reload();
    	 final Runnable bg = new Runnable() 
         {@Override
			public void run() 
			{
			 bgreload();
			}
		  };
         GLGame.glView.queueEvent(bg);  
    	
    }
    
    
   
    
    
    
    protected static void bgreload() 
    {
    	int i=0;
    	Assets.loaderp=0;
    	while(i<texcount)
    	{textures[i++].reload();
    	Assets.loaderp=100*i/texcount;
    	}
    	if(Settings.musicEnabled)
    	music[Settings.musicindex].play();
    	Assets.loaderp=100;// TODO Auto-generated method stub
		
	}

	public static void playSound(Sound sound) 
    {      if(Settings.soundEnabled)
           sound.play(Settings.soundvolume);
           //sound.play(0.5f);
    }
    
    //////////////////////////////////////////////////Dispose Textures.////////////////////////////
    public static void dispose()
    {  //Dispose textures
    	
    	
    	
    	int i=0;
    	while(i<texcount)
    	{textures[i++].dispose();
    	}
    	Assets.loaderp=0;
    }

	public static Texture gettexture(String name) 
	{return textures[TextureNames.indexOf(name)];
 	}
    
	
	public static TextureRegion gettextureregion(String name) 
	{    
	 return textureregions[TexturRegionName.texturegionname.indexOf(name)];
		
	}

	
	
	
	public static Sound getsound(String name) 
	{
		return sounds[SoundNames.indexOf(name)];
	}

	
	
	
	public static void playMusic() 
	{
		// TODO Auto-generated method stub
		if(!(music[Settings.musicindex].isPlaying()) )
		{music[Settings.musicindex].play();
		}
		
			
	}

	public static void stopMusic()
	{	Settings.musicEnabled=false;
	 	music[Settings.musicindex].stop();
		
	}

	public static void adjustvolume(float v, int m) 
	{ if(m==1)
	   {Settings.soundvolume=v;}
	 else
	  {Settings.musicvolume=v;
	   music[Settings.musicindex].setVolume(0.5f);
	  }
  	}

	public static void vibrate(int patternindex) 
	{
		if(patternindex==-1)
		return;
		
		Vibrator v = (Vibrator)TerVel.contex.getSystemService(Context.VIBRATOR_SERVICE);
	    long[] pattern = { 0,10,10,20};
     	v.vibrate(pattern, -1);
		
	}


  public int assetsize(int i)
  {switch(i)
	  {
       case 1:return  Assets.TextureNames.size();
       case 2:return TexturRegionName.texturegionname.size();
	   case 3: return Assets.SoundNames.size();
	   case 4: return (int) Assets.screenHeight;
	   case 5: return (int) Assets.screenWidth;
	    
       }
  return -1;
  }
  public  String assetnames(int i,int index)
  {  
	if(i==1)
	return Assets.TextureNames.get(index);
	if(i==2)
	return TexturRegionName.texturename.get(index);
	if(i==3)
	return TexturRegionName.texturegionname.get(index);
	if(i==4)
	return Assets.SoundNames.get(index);
	else
	return null;
  }
  
  public  int assetdatasize(int i,int index)
  {//send texture regions width
    if(i==1)
   	return TexturRegionName.sizex.get(index);
   //send texture region height
    if(i==2)
    return TexturRegionName.sizey.get(index);	
    if(i==3)
      return TextureNames.indexOf(TexturRegionName.texturename.get(index));	
         
    //Send sound list
    else
    return -1;
  }

public static void focusStatee(boolean hasFocus) 
{
if(!hasFocus)
 {Assets.music[0].pause();
	NativeFun.poststate(3);// TODO Auto-generated method stub
	return;
 }
if(hasFocus&&Settings.musicEnabled&&Assets.music!=null)
{if(Assets.music[0]==null)
	return;
//else if(Assets.music[0].isStopped())
 Assets.music[0].play();
}

}


}
