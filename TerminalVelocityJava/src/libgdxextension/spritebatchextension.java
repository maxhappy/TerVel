package libgdxextension;



import javax.microedition.khronos.opengles.GL10;
import android.util.FloatMath;
import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.gl.Vertices;
import com.badlogic.androidgames.framework.impl.GLGraphics;
import com.badlogic.androidgames.framework.math.Vector2;


public class spritebatchextension 
{        
    

	final float[] verticesBuffer;
    int bufferIndex;
    final Vertices vertices;
    int numSprites;    
    
    public spritebatchextension(GLGraphics glGraphics, int maxSprites)
    {  
        this.verticesBuffer =new float[maxSprites*4*4];        
        this.vertices = new Vertices(glGraphics, maxSprites*4, maxSprites*6, false, true);
        this.bufferIndex = 0;
        this.numSprites = 0;
                
        short[] indices = new short[maxSprites*6];
        int len = indices.length;
        short j = 0;
        for (int i = 0; i < len; i += 6, j += 4)
        {
                indices[i + 0] = (short)(j + 0);
                indices[i + 1] = (short)(j + 1);
                indices[i + 2] = (short)(j + 2);
                indices[i + 3] = (short)(j + 2);
                indices[i + 4] = (short)(j + 3);
                indices[i + 5] = (short)(j + 0);
        }
        vertices.setIndices(indices, 0, indices.length);                
    }       
    
    public void beginBatch(Texture texture)
    {
        texture.bind();
        numSprites = 0;
        bufferIndex = 0;
    }
    
    public void endBatch() {
        vertices.setVertices(verticesBuffer, 0, bufferIndex);
        vertices.bind();
        vertices.draw(GL10.GL_TRIANGLES, 0, numSprites * 6);
        vertices.unbind();
    }
    
    public void drawSprite(float x, float y, float width, float height, TextureRegion region) 
    {
    	drawSprite(x, y,width,  height,  0F,0F,0F, region);
        
    }
    
    public void drawSprite(float x, float y, float width, float height, float angle, TextureRegion region) 
    {
    	drawSprite(x, y,width,  height,  angle,0F,0F, region);
        
    }
	
	 public void drawSprite(float x, float y, float width, float height, float angle,float px,float py, TextureRegion region) 
	 {
	        float halfWidth = width / 2;
	        float halfHeight = height / 2;
	        
	        float rad = angle * Vector2.TO_RADIANS;
	        float cos = FloatMath.cos(rad);
	        float sin = FloatMath.sin(rad);

	        //Rotation about a point
	        ///////////////////////////////////////////////////////////////
	        
	        float x1 =(-halfWidth-px) * cos -(-halfHeight-py) * sin;
	        float x2 =(halfWidth-px )* cos -(-halfHeight-py) * sin;
	        float x3 =(halfWidth-px) * cos-(halfHeight-py) * sin;
	        float x4 =(-halfWidth-px) * cos-(halfHeight-py) * sin;
	        
	        float y1 = (-halfWidth-px) * sin + (-halfHeight-py) * cos;
	        float y2 = (halfWidth-px )* sin + (-halfHeight-py) * cos;
	        float y3 = (halfWidth-px )* sin + (halfHeight-py )* cos;
	        float y4 = (-halfWidth-px) * sin + (halfHeight-py )* cos;
	        
	        x1 += x;
	        y1 += y;
	        x2 += x;
	        y2 += y;
	        x3 += x;
	        y3 += y;
	        x4 += x;
	        y4 += y;
	        //////////////////////////////////////////////////////////////////
	        verticesBuffer[bufferIndex++] = x1;
	        verticesBuffer[bufferIndex++] = y1;
	        verticesBuffer[bufferIndex++] = region.u1;
	        verticesBuffer[bufferIndex++] = region.v2;
	        
	        verticesBuffer[bufferIndex++] = x2;
	        verticesBuffer[bufferIndex++] = y2;
	        verticesBuffer[bufferIndex++] = region.u2;
	        verticesBuffer[bufferIndex++] = region.v2;
	        
	        verticesBuffer[bufferIndex++] = x3;
	        verticesBuffer[bufferIndex++] = y3;
	        verticesBuffer[bufferIndex++] = region.u2;
	        verticesBuffer[bufferIndex++] = region.v1;
	        
	        verticesBuffer[bufferIndex++] = x4;
	        verticesBuffer[bufferIndex++] = y4;
	        verticesBuffer[bufferIndex++] = region.u1;
	        verticesBuffer[bufferIndex++] = region.v1;
	        
	        numSprites++;
	    }

}

	
