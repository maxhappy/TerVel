package inductionlabs.tervel;
import javax.microedition.khronos.opengles.GL10;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;

public class resumescreen extends GLScreen 
{
    Camera2D guiCam;
    SpriteBatcher batcher;
    Rectangle nextBounds;
    Vector2 touchPoint;
    Texture splashscreen;
    TextureRegion loadscreen,circ,loading;
	 
    
    public resumescreen (Game game) 
    {
        super(game);
        
        guiCam = new Camera2D(glGraphics, 320, 480);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 2);
        
        
       
    }
    
    @Override
    public void resume() 
    {
       
        
    }
    
    @Override
    public void pause()
    {
        
    }

    @Override
    public void update(float deltaTime) 
    {
     
                   // if(Assets.loaderp==100)
                   // game.setScreen(new GameScreen(game));
                    return;
     
    }
static int angle=0;
static float time=0;
    @Override
    public void present(float deltaTime)
    {
        GL10 gl = glGraphics.getGL();        
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();
        gl.glEnable(GL10.GL_TEXTURE_2D);
        batcher.beginBatch(splashscreen);
        batcher.drawSprite(160, 240, 320, 480, loadscreen);
        batcher.drawSprite(160, 280, 320, 480,loading);
        batcher.drawSprite(160, 290, 320, 480,angle,circ);
        time+=deltaTime;
        
        if(time>20000)
        {angle++;
        time=0;
        }
        batcher.endBatch();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void dispose() 
    {
    }
}
