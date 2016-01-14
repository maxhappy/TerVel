//Class responsible for calling all native functions from java. 
//Each java class calls this class.


package inductionlabs.tervel;
import android.content.Context;

public class NativeFun
{
	static Context contex;
	public  static native void createEngine();
	public static native void postaudiostate(boolean a,boolean b);
	public native static void draw(float deltaTime) ;
	native public static void update();
	native public static int updateacc(float accelX, float accelY,float accelZ);
	native public static void updatetou(float x, float y, int type, int pointer);
	native public static boolean updatekey(int keyCode, int type);
	native public static int postdata(Object [] a,int i, int j);
	native public static int poststate(int i );
	
	public static void onSensorChanged(float accelX, float accelY, float accelZ) 
	{
		
		switch(TerVel.orientation)
		{case 0:updateacc(-1*accelX,-1*accelY,accelZ);break;
		case 1:	updateacc(1*accelY,-1*accelX,accelZ);break;
		case 2:	updateacc(1*accelX,1*accelY,accelZ);break;
		case 3:	updateacc(-1*accelY,1*accelX,accelZ);break;
		}
		

	}
	 
 
	


	public static void preResume()
	{
		poststate(1);
	}

	public static void postResume() 
	{
		 
		 
		
	}

	public static void prePause()
	{	 poststate(3);
		
	}

	public static void postPause()
	{ 
		 
		 
	}
	public static void initEngine()
	{
		
		postaudiostate(Settings.musicEnabled,Settings.soundEnabled);
		createEngine();
	}
	

	 
 
	
	
	

}

