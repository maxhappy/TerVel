package inductionlabs.tervel;


import java.util.List;
import inductionlabs.jni.bridge.BatcherBridge;
import inductionlabs.jni.bridge.Bridge;
import inductionlabs.jni.bridge.tools_seting_bridge;

import javax.microedition.khronos.opengles.GL10;

import libgdxextension.spritebatchextension;

import android.app.Activity;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input.KeyEvent;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.FPSCounter;
import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;

public class GameScreen extends GLScreen
{
	Camera2D guiCam;
    public  spritebatchextension batcher;
    public  BatcherBridge batcherBridge;
    public  tools_seting_bridge ts_bridge;
    
    Rectangle nextBounds;
    Vector2 touchPoint;
    Texture helpImage;
    TextureRegion helpRegion;
    FPSCounter f;
	private boolean first;
	private boolean resume=false;
    public GameScreen(Game game)
  {
    	
    	super(game);
        guiCam = new Camera2D(glGraphics, Assets.screenWidth, Assets.screenHeight);
        batcher = new spritebatchextension(glGraphics,Assets.max_texrg );
        batcherBridge=new BatcherBridge(batcher);
        ts_bridge=new tools_seting_bridge();
        f=new FPSCounter();
        //Sending a handle of this class to c++
        Bridge.object(this, 0);
       //Sending a handle of this class to c++
        Bridge.object(batcherBridge, 1);
       //Sending a handle of this class to c++
        Bridge.object(guiCam, 2);
      //////////////////////////////////////
        Bridge.object(ts_bridge,4);
      /////////////////////////////////  
        
    }
    
    //Resume Call
    @Override
    public void resume()
    {  	
    	if(!resume)
    	NativeFun.preResume();	
    	
    }
    
    
  //Pause Call  
    @Override
    public void pause() 
    {
     Assets.splashscreen.dispose();
     if(Assets.loaderp==100)	
     {
     Assets.dispose();
     }
     resume=true;
     Settings.save(TerVel.fileiohandle);
     NativeFun.prePause();
    }
    //Input Calls
    @Override
    public void update(float deltaTime) 
    {
    	List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        List<KeyEvent> keyEvents= game.getInput().getKeyEvents();
        
        touchPoint=new Vector2();
        
        
        
        int len = keyEvents.size();
        for(int i = 0; i < len; i++)
        {  KeyEvent event =  keyEvents.get(i);
           Assets.keyhandle=NativeFun.updatekey(event.keyCode,event.type);

          // if(event.keyCode==android.view.KeyEvent.KEYCODE_VOLUME_DOWN)
        	//   ((Activity) TerVel.contex).finish();
          // Assets.keyhandle=true;
           //keyEvents.remove(i);
        }
        len = touchEvents.size();
        
        for(int i = 0; i < len; i++)
        {  TouchEvent event = touchEvents.get(i);
           touchPoint.set(event.x, event.y);
           guiCam.touchToWorld(touchPoint);
           
           Assets.vibrate(Assets.vibra);
          
           
           NativeFun.updatetou(touchPoint.x,touchPoint.y,event.type,event.pointer);
          // touchEvents.remove(i);
         
         }
    
        
    NativeFun.onSensorChanged(game.getInput().getAccelX(),game.getInput().getAccelY(),game.getInput().getAccelZ());
    
    }
    
    
    
    //Draw calls
    @Override
    public void present(float deltaTime) 
    {   GL10 gl = glGraphics.getGL();
    	clrscr(gl);
    	//Screen set and now draw calls from native code
    	
    	if(Assets.loaderp!=100)
    	{drawload(deltaTime,gl);
    	if(resume!=true)
    		first=true;
    	}
    	
    	else
    	{
        if(first)
    	{NativeFun.initEngine();
        first=false;
        }
    	NativeFun.draw(deltaTime);
    	
     
    	}
    	f.logFrame();
    	if(Assets.quit==true)
    	{((Activity) TerVel.contex).finish();
    		
    	}
    	
    }
    static int angle=0;
    static float time=0;
    
    private void drawload(float deltaTime,GL10 gl) 
    {                    
    
    batcher.beginBatch(Assets.splashscreen);
    batcher.drawSprite(160, 240, 320, 480,Assets.loadscreen);
    gl.glEnable(GL10.GL_BLEND);
    gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    //batcher.drawSprite(160, 200, 10,156/2,55/2,Assets.loading);
    batcher.drawSprite(50, 50,0+360*Assets.loaderp/100,60,0,Assets.circ);
    time=500*deltaTime;
    
    if(time>2)
    {angle+=5;
     angle%=360;
    time=0;
    }
    batcher.endBatch();
      
	}

	void clrscr(GL10 gl)
    {
    gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    guiCam.setViewportAndMatrices();
    gl.glEnable(GL10.GL_TEXTURE_2D);
    	
    }
    {/*
    void drawbg(GL10 gl)
    {
    batcher.beginBatch(Assets.background);
    batcher.drawSprite(160, 240, 320, 480,Assets.backgroundRegion);
    batcher.endBatch();
    }
    
    void drawfront(GL10 gl)
    {
    gl.glEnable(GL10.GL_BLEND);
    gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    batcher.beginBatch(Assets.items);          
    batcher.drawSprite(320 - 32, 32, -64, 64, Assets.arrow);
    batcher.endBatch();
    gl.glDisable(GL10.GL_BLEND);
    	
    }
    }

    */
    }
	@Override
	public void dispose()
	{
	// TODO Auto-generated method stub
		
	}
    
}
