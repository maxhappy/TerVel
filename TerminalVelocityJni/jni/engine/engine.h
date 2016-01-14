/*
 * engine.h
 *
 *  Created on: 24-Oct-2012
 *      Author: Praveen
 */


#ifndef ENGINE_H_
#define ENGINE_H_
#ifndef Null
#define Null "\0"
#include "inc.h"
//////////////////////////////////////////class  to call java function//////////////////////////////////////////////////////////
class engine
{
 public:
 static jobject Glgame,Batcher,GuiCam,GameScreen,Set,Aud,Asset;
 static jclass assets,javaBatcher,setting,audio;
 static void drawnumber(JNIEnv *env,int n, int x,int y,int stepx);
 //static const char * const  names[];
 //static const char * const  batch[];

static void setcolor(GLfloat r,GLfloat g,GLfloat b,GLfloat a)
{
	glColor4f(r,g,b,a);
}
////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////Draw Call functions////////////////////////////////////////////


static void beginBatch(JNIEnv *g_env,int i)//const char * path)
{
  	jmethodID  mid= g_env->GetMethodID(javaBatcher,"beignBatch","(I)I");
 	//jstring name = g_env->NewStringUTF(path);
    int l=g_env->CallIntMethod(Batcher, mid,i);
	return ;

}

static  void endBatch(JNIEnv *g_env)
{
	jmethodID mid= g_env->GetMethodID(javaBatcher,"endBatch","()I");
	int l=g_env->CallIntMethod(Batcher, mid);
    return;

}

static void spriteDraw(JNIEnv *g_env,float x,float y,float width,float height,float angle,float pinx,float piny,int index)
{
jmethodID mid= g_env->GetMethodID(javaBatcher,"drawSprite","(FFFFFFFI)I");
//jstring name = g_env->NewStringUTF(path);
int l=g_env->CallIntMethod(Batcher, mid, x, y, width,height,angle,pinx,piny,index);
return;
}


static void scoreDraw(JNIEnv *g_env,float x,float y,float width,float height,float angle,float pinx,float piny,int index)
{
jmethodID mid= g_env->GetMethodID(javaBatcher,"scoreDraw","(FFFFFFFI)I");
//jstring name = g_env->NewStringUTF(path);
int l=g_env->CallIntMethod(Batcher, mid, x, y, width,height,angle,pinx,piny,index);
return;
}




static void textDraw(JNIEnv *g_env,float x, float y, float width, float height,int font, const char * text,float sx,float sy ,float tx,float ty ,const char * path)
 { jmethodID mid= g_env->GetMethodID(javaBatcher,"drawText","(FFFFILjava/lang/String;FFFFLjava/lang/String;)I");
   jstring name = g_env->NewStringUTF(path);
   jstring _text = g_env->NewStringUTF(text);
   int l=g_env->CallIntMethod(Batcher, mid,x,y,width,height,font,_text,sx,sy,tx,ty,name);
  return;}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////


 /////////////////////////////////Audio functions binding///////////////////////////////////////


  static   void playsound(JNIEnv *g_env,const char * path)
  {

  /////	jmethodID  mid= g_env->GetMethodID(audio,"playsound","(Ljava/lang/String;)I");
  ///// 	jstring name = g_env->NewStringUTF(path);
  //// 	int l=g_env->CallIntMethod(Aud, mid,name);
  	return ;
  }

  static   void playsound(JNIEnv *g_env,int i)
  {
	  jmethodID mid= g_env->GetMethodID(javaBatcher,"playsound","(I)I");
	  int l=g_env->CallIntMethod(Batcher, mid,i);
	  return;

  }
//////////////////////////////////////////////////////////////////////////////////////////
///////////////////off or on tells to off on play music 1/0///////////////////////////////
  static  void music(JNIEnv *g_env,int offoron,const char * path)
  {
   	jmethodID  mid= g_env->GetMethodID(javaBatcher,"music","(ILjava/lang/String;)I");
   	jstring name = g_env->NewStringUTF(path);
    int l=g_env->CallIntMethod(Batcher, mid,offoron,name);
  	return ;

  }
///////////////////////////////////////////////////////////////////////////////////////////////
////////////////////Channel tells about adjusting  volume of sound effect or music////////////
     static void adjustVolume(JNIEnv *g_env,jfloat vol,int channel,const char * path)
  {
  	jmethodID mid= g_env->GetMethodID(javaBatcher,"adjustvolume","(FILjava/lang/String;)I");
  	jstring name = g_env->NewStringUTF(path);
  	int l=g_env->CallIntMethod(Batcher, mid,vol,channel,name);
      return;

  }

     static void vibration(JNIEnv *g_env,int t)
  {
  	jmethodID mid= g_env->GetMethodID(audio,"vibration","(I)I");
  	int l=g_env->CallIntMethod(Aud, mid,t);
      return;

  }

///////////////////////////////////////////////////////////////////////////////////////
//////////////perform android tasks like finishing app ,calling any context ,etc///////
///////////////////////////////////////////////////////////////////////////////////////
  static void performtask(JNIEnv *g_env,int t)
  {
  	jmethodID mid= g_env->GetMethodID(audio,"performtask","(I)I");
  	int l=g_env->CallIntMethod(Aud, mid,t);
      return;

  }

///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////File function bindings/////////////////////////////////////////////

     static void write(JNIEnv *g_env,const char * path)
  {
  	jmethodID mid= g_env->GetMethodID(setting,"write","(Ljava/lang/String;)I");
	jstring name = g_env->NewStringUTF(path);
  	int l=g_env->CallIntMethod(Set, mid,name);
    return;

  }
   static void write(JNIEnv *g_env,int score)
  {
  	jmethodID mid= g_env->GetMethodID(javaBatcher,"write","(I)I");
	//jstring name = g_env->NewStringUTF(path);
  	int l=g_env->CallIntMethod(Batcher, mid,score);
    return;

  }

     static jstring read(JNIEnv *g_env,const char * path)
     {
     	jmethodID mid= g_env->GetMethodID(setting,"read","(FILjava/lang/String;)Ljava/lang/String;");
    	jstring name = g_env->NewStringUTF(path);
     	jstring f=(jstring)(g_env->CallObjectMethod(Set, mid,name));
       return f;

     }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     static int  assetsize(JNIEnv *g_env,int i )
     {
     	jmethodID mid= g_env->GetMethodID(assets,"assetsize","(I)I");
     	int  f=g_env->CallIntMethod(Asset,mid,i);
        return f;

     }

     static int  assetdatasize(JNIEnv *g_env,int i,int j)
          {
          	jmethodID mid= g_env->GetMethodID(assets,"assetdatasize","(II)I");
          	int  f=g_env->CallIntMethod(Asset,mid,i,j);
             return f;

          }

     static jstring  assetdataname(JNIEnv *g_env,int i,int j)
               {
               	jmethodID mid= g_env->GetMethodID(assets,"assetdatasize","(II)Ljava/lang/String;");
                jstring f=(jstring)(g_env->CallObjectMethod(Asset,mid,i,j));
                return f;
               }
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////

 };
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
jobject engine::Glgame,engine::Batcher,engine::GuiCam,engine::GameScreen,engine::Set,engine::Aud,engine::Asset;
jclass engine::assets,engine::javaBatcher,engine::setting,engine::audio;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////Native function from java to c/cpp////////////////////////////////////////////
 static long k=0;
//////////////////////////////////Game Class all game related update ,Display done here /////////////////////////
 class Game
 {   static bool musci,soundst;

     static RegionData r1,r2,r3;
 public:

     static ViewManager vm;
     static GameData gd;
     Game(JNIEnv *env);
     virtual ~Game();
     static void quit(JNIEnv * env);
     static void genregion(int i);
     static void State(JNIEnv * env,int state);
     static void Draw(JNIEnv * env,float dt);
     static void guiDraw(JNIEnv * env,float dt);
     static void Update(JNIEnv *env);
     static void  Update(JNIEnv *env,float ax,float ay,float az);
     static bool Update(JNIEnv *env,int key,int keycode);
     static void Update(JNIEnv *env,float tx,float ty,int state,int index);
     static void Init(JNIEnv *env);
     static void drawbg(JNIEnv *env,float dt,int x,int y,int index);
     static void drawgui(JNIEnv *env,float dt,int x,int y);
     static void drawwall(JNIEnv *env,float dt,int x,int y,int object,int type,float angle);
     static void drawitems(JNIEnv *env,float dt,int x,int y,int object,int type,float angle);
     static void drawcurrent(JNIEnv *env,float dt,int x,int y,float sx,float sy,int object,int sprite,float angle);
     static void drawhero(JNIEnv *env,float ,int ,int ,int ,int ,float );
     static void drawPattern(JNIEnv *env,float ,int ,int ,int ,int ,float );
     static void draw2(JNIEnv *env,float dt,int x,int y,int object,int type,float angle);
     static void drawstrike(JNIEnv *env,float dt,int x,int y,int object,int type,float angle);
     static void draw4(JNIEnv *env,float dt,int x,int y,int object,int type,float angle);
     static void draw5(JNIEnv *env,float dt,int x,int y,int object,int type,float angle);
     static jstring itoa(JNIEnv *env,int);
     static void  postaudiodata(bool a, bool b)
		{
    	 gd.musicstate=a;
    	 gd.soundstate=b;
    	 //musci=a;
		 //soundst=b;
		}
 };
 bool Game::musci,Game::soundst;
 GameData Game::gd(Game::musci,Game::soundst);RegionData Game::r1,Game::r2,Game::r3;
 ViewManager Game::vm;

#endif
#endif
