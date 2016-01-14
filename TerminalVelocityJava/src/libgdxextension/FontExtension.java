package libgdxextension;

import com.badlogic.androidgames.framework.gl.Font;
import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;

public class FontExtension extends Font{

    public final Texture texture;
    public final int glyphWidth;
    public final int glyphHeight;
    public final TextureRegion[] glyphs = new TextureRegion[96];   
    
	public FontExtension(Texture texture, int offsetX, int offsetY, int glyphsPerRow,int glyphWidth, int glyphHeight) 
	{
		super(texture, offsetX, offsetY, glyphsPerRow, glyphWidth, glyphHeight);
		
        this.texture = texture;
        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;
        int x = offsetX;
        int y = offsetY;
        for(int i = 0; i < 96; i++) {
            glyphs[i] = new TextureRegion(texture, x, y, glyphWidth, glyphHeight);
            x += glyphWidth;
            if(x == offsetX + glyphsPerRow * 50) {
                x = offsetX;
                y += 50;
            }
        }        
    }
    
    public void drawText(spritebatchextension batcher, String text, float x, float y) 
    {
    	//drawText(batcher,  text, x, y,);
      }
  public void drawText(spritebatchextension batcher, String text, float x, float y,float sx,float sy,float tx,float ty)
   {
            int len = text.length();
            for(int i = 0; i < len; i++) 
            {
                int c = text.charAt(i) - ' ';
                if(c < 0 || c > glyphs.length - 1) 
                    continue;
                
                TextureRegion glyph = glyphs[c];
                batcher.drawSprite(x, y, sx*glyphWidth,sy*glyphHeight, glyph);
                x += glyphWidth*(tx);
            }
    }
}
