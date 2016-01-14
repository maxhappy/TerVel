package inductionlabs.nativ;
import inductionlabs.tervelhd.Assets;
import inductionlabs.tervelhd.GameScreen;

    public class engine
    {




        public static void setcolor(float r, float g, float b, float a)
        {
        	GameScreen.gl.glColor4f(r,g,b,a);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////Draw Call functions////////////////////////////////////////////


        public static void beginBatch(int i)//const char * path)
        {
            GameScreen.batcherBridge.beignBatch(i);

        }

        public static void endBatch()
        {
        	GameScreen.batcherBridge.endBatch();
            return;
        }

        public static void spriteDraw(float x, float y, float width, float height, float angle, float pinx, float piny, int index)
        {
        	GameScreen.batcherBridge.drawSprite(x, y, width, height, angle, pinx, piny, index);
            return;
        }


        public static void scoreDraw(float x, float y, float width, float height, float angle, float pinx, float piny, int index)
        {
        	GameScreen.batcherBridge.scoreDraw(x, y, width, height, angle, pinx, piny, index);
            return;
        }




        public static void textDraw(float x, float y, float width, float height, int font, String text, float sx, float sy, float tx, float ty, String path)
        {
        	GameScreen.batcherBridge.drawText(x, y, width, height, font, text, sx, sy, tx, ty, path);
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////Audio functions binding///////////////////////////////////////


        public static void playsound(String path)
        {

            /////	jmethodID  mid= g_env->GetMethodID(audio,"playsound","(Ljava/lang/String;)I");
            ///// 	jstring name = g_env->NewStringUTF(path);
            //// 	int l=g_env->CallIntMethod(Aud, mid,name);
            return;
        }

        public static void playsound(int i)
        {
        	GameScreen.batcherBridge.playsound(i);
            return;

        }
        //////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////off or on tells to off on play music 1/0///////////////////////////////
        public static void music(int offoron, String path)
        {
        	GameScreen.batcherBridge.music(offoron, path);
            return;

        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////Channel tells about adjusting  volume of sound effect or music////////////
        public static void adjustVolume(float vol, int channel, String name)
        {
        	GameScreen.batcherBridge.adjustvolume(vol, channel, name);
            return;
        }

        public static void vibration(int t)
        {
        	GameScreen.batcherBridge.vibration(t);
            return;

        }

        ///////////////////////////////////////////////////////////////////////////////////////
        //////////////perform android tasks like finishing app ,calling any context ,etc///////
        ///////////////////////////////////////////////////////////////////////////////////////
        public static void performtask(int t)
        {
        	GameScreen.batcherBridge.performtask(t);
            return;

        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////File function bindings/////////////////////////////////////////////

        public static void write(String path)
        {
        	GameScreen.ts_bridge.write(path);
            return;

        }
        public static void write(int score)
        {
        	GameScreen.batcherBridge.write(score);
            return;

        }
        public static String read(String name)
        {
            return GameScreen.ts_bridge.read(name);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public static int assetsize(int i)
        {
            return  Assets.assetsize(i);

        }

        public static int assetdatasize(int i, int j)
        {
            return  Assets.assetdatasize(i, j);
        }

        public static String assetdataname(int i, int j)
        {

            return null;

        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


         static void spriteDraw(double p1, double p2, int spritewidth, int spriteheight, int p3, int p4, int p5, int sprite)
        {
            spriteDraw((float)p1,(float)p2,(float)spritewidth,(float)spriteheight,(float)p3,(float)p4,(float)p5,(int)sprite);
        }

        static void spriteDraw(int p1, int p2, double p3, double p4, int p5, int p6, int p7, int p8)
        {
            spriteDraw((float)p1, (float)p2, (float)p3, (float)p4, (float)p5, (float)p6, (float)p7, (int)p8);
    
        }

        
    }

