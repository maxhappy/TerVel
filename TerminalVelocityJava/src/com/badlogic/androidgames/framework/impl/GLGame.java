package com.badlogic.androidgames.framework.impl;



import inductionlabs.nativ.GameData;
import inductionlabs.nativ.RegionData;
import inductionlabs.nativ.ViewManager;
import inductionlabs.tervelhd.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.Looper;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.badlogic.androidgames.framework.Audio;
import com.badlogic.androidgames.framework.FileIO;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input;
import com.badlogic.androidgames.framework.Screen;
import com.google.ads.*;


public abstract class GLGame extends Activity implements Game, Renderer 
{
    enum GLGameState {
        Initialized,
        Running,
        Paused,
        Finished,
        Idle
    }
    
    static public GLSurfaceView glView;    
    GLGraphics glGraphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    GLGameState state = GLGameState.Initialized;
    Object stateChanged = new Object();
    long startTime = System.nanoTime();
    WakeLock wakeLock;
	public static AdView adView;
	private String pubID="a1509972e15ced6";
	public static RelativeLayout layout;
	public static LinearLayout adlay;
    @Override 
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        glView = new GLSurfaceView(this);
        glView.setRenderer(this);
        
        
        setContentView(R.layout.main);
        //setContentView(glView);
        
        if(inductionlabs.nativ.Game.vm==null)
    		inductionlabs.nativ.Game.vm=new ViewManager();

        if(inductionlabs.nativ.Game.r1==null)
    		inductionlabs.nativ.Game.r1=new RegionData();
        if(inductionlabs.nativ.Game.r2==null)
        	inductionlabs.nativ.Game.r2=new RegionData();
        if(inductionlabs.nativ.Game.r3==null)
        	inductionlabs.nativ.Game.r3=new RegionData();
        if(inductionlabs.nativ.Game.gd==null)
        	inductionlabs.nativ.Game.gd=new GameData(false,false);
 
        
        adView = new AdView(this, AdSize.BANNER, pubID);
     	AdRequest request = new AdRequest();
     	
     	request.addTestDevice("D5C776CF9171D2495EBC87FB1D6B2A79");
    	//request.setTesting(true);
    	
        
        
        
        
        

        
        
        
        //(new Thread() {
            //public void run() {
            //     Looper.prepare();
                adView.loadAd(new AdRequest());
          //  }
        //}).start();
        
        
        
        
        
        
        
        
        
        
        
        
    	 adlay = (LinearLayout)findViewById(R.id.Add);
        layout = (RelativeLayout)findViewById(R.id.Game);
        layout.addView(glView);
        adlay.addView(adView);
            
        
        
        
        
        glGraphics = new GLGraphics(glView);
        fileIO = new AndroidFileIO(getAssets());
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, glView, 1, 1);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
        
        
    }
    
    @Override
    public void onResume() 
    {
        super.onResume();
        glView.onResume();
        wakeLock.acquire();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) 
    {inductionlabs.tervelhd.Assets.focusStatee(hasFocus);
    	super.onWindowFocusChanged(hasFocus);
      
    } 
     
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {        
        glGraphics.setGL(gl);
        
        synchronized(stateChanged) {
            if(state == GLGameState.Initialized)
                screen = getStartScreen();
            state = GLGameState.Running;
            screen.resume();
            startTime = System.nanoTime();
        }        
    }
    
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {        
    }

    @Override
    public void onDrawFrame(GL10 gl) {                
        GLGameState state = null;
        
        synchronized(stateChanged) {
            state = this.state;
        }
        
        if(state == GLGameState.Running) {
            float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
            startTime = System.nanoTime();
            
            screen.update(deltaTime);
            screen.present(deltaTime);
        }
        
        if(state == GLGameState.Paused) {
            screen.pause();            
            synchronized(stateChanged) {
                this.state = GLGameState.Idle;
                stateChanged.notifyAll();
            }
        }
        
        if(state == GLGameState.Finished) {
            screen.pause();
            screen.dispose();
            synchronized(stateChanged) {
                this.state = GLGameState.Idle;
                stateChanged.notifyAll();
            }            
        }
    }   
    
    @Override 
    public void onPause() {        
        synchronized(stateChanged) {
            if(isFinishing())            
                state = GLGameState.Finished;
            else
                state = GLGameState.Paused;
            while(true) {
                try {
                    stateChanged.wait();
                    break;
                } catch(InterruptedException e) {         
                }
            }
        }
        wakeLock.release();
        glView.onPause();  
        super.onPause();
    }    
    
    public GLGraphics getGLGraphics() {
        return glGraphics;
    }  
    
    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        throw new IllegalStateException("We are using OpenGL!");
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    @Override
    public Screen getCurrentScreen() {
        return screen;
    }   
}
