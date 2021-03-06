package inductionlabs.tervelhd;

import inductionlabs.jni.bridge.Bridge;
import android.content.Context;

import com.badlogic.androidgames.framework.FileIO;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.GLGame;
 

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
 
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.view.*;

public class TerVel  extends GLGame
{
	
	    static public Context contex;
	    public static FileIO fileiohandle;
 	    boolean firstTimeCreate = true;
 	    Assets asset;
 	    
 	    static public int orientation;
 	    
	    //Entry point to app after opengl surface creation 	    
	    @Override
	    public void onBackPressed() 
	    {
	    	if(Assets.loaderp!=100)
	        return;
	    		if(!NativeFun.updatekey(50,1))
	 		   super.onBackPressed();
	    	
	    }
 	    @Override
	    public void onSurfaceCreated(GL10 gl, EGLConfig config) 
	    {  asset=new Assets();
	       Assets.quit=false;
	       Bridge.object(asset,6);
	       contex=this;
	       super.onSurfaceCreated(gl, config);
 	      	  
 	    	
 	    	Display display = ((WindowManager)contex.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
 	        orientation= display.getRotation();
 	       int width = 0;
 	      int height = 0;
 	      switch (display.getRotation())
 	      {
 	      case Surface.ROTATION_0:
 	      case Surface.ROTATION_180:
 	          width = display.getWidth();
 	          height = display.getHeight();
 	          break;
 	      case Surface.ROTATION_90:       
 	      case Surface.ROTATION_270:
 	          width = display.getHeight();
 	          height = display.getWidth();
 	          break;
 	      default:
 	          break;
 	      }

 	      if(width > height)
 	      {
 	    	  orientation=1;
 	      }
 	      else 
 	      { orientation=0;
 	      } 

 	        
 	    	//Handy Variable for referencing this class all over game
	        //  this take two variables a java class and index,Index tell c++ this object is for which class 
	       
 	        Bridge.object(this, 3);
	        //So browse c++ code and see we have an enum to make this user friendly
	        setfileiohandle();
	        if(firstTimeCreate)
	        { //Reloading all assets and even reading save data file 
	        	Settings.load(getFileIO());
	            Assets.load((GLGame) contex);
	            firstTimeCreate = false;            
	        }
	        else
	        {//Only Reloading Assets.
	            Assets.reload();
	        }
	       
	    }     
	    
 	   @Override 
 	   public boolean onCreateOptionsMenu(Menu menu) 
 	   {
 	        //MenuInflater inflater = getMenuInflater();
 	        //inflater.inflate(R.menu.menu, menu);
 	        return true;
 	    }
 	    @Override
 	    public boolean onOptionsItemSelected(MenuItem item) {
 	        switch(item.getItemId())
 	        { //case dratervel.mr:Sender.openWebURL("market://search?q=pub:\"Induction Labs\"",contex);break; 
 	          //case id.share:Sender.share_post("");break;
 	         //case id.rate:Sender.openWebURL("market://details?id=inductionsoftware.pnr",contex);break;
 	         }
 	        return true;
 	    }
 	   
 	    
	    
 	    
 	    //Just pausing  app. 
 	    
 	    @Override
	    public void onPause() 
	    {  pause=true;	
		    //Pausing music
		  if(Settings.musicEnabled)
 	       Assets.music[Settings.musicindex].pause();
		  super.onPause();
	    }
 	    
 	 //Chained after on resume here we will tell code to initiate our game screen in process creating the gl surface.
 	    
    static int i=0;
	public static boolean pause;	
	
 	 @Override
	public Screen getStartScreen() 
	{
	//Initiating the c++ world
 	 /*
 	 long currentim=System.nanoTime();
 	   long sd=100000000;
 	  while((System.nanoTime()-currentim )<sd*100 )
 	    {	}i=1;
 		//*/
 		setfileiohandle();
 		return new GameScreen(this);
		//return new resumescreen(this);
		 
	}
	
 	 
 	 
 	public  void setfileiohandle()
 	{fileiohandle= getFileIO();
 	}
 	 
 	 
 
 	 
 	 
 	 //Loading our Native c++ engine for this game
	//static
	//{
      //System.loadLibrary("engine");
      

	//}
    
	
	
}