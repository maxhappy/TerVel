/*
 * jni.h
 *
 *  Created on: 01-Nov-2012
 *      Author: Praveen Ojha
 */

#ifndef JNIfun_H_
#define JNIfun_H_
#include<jni.h>

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 ////////////////////////////////Jni glue code below/////////////////////////////////////////////////////////////
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 extern "C"
{

JNIEXPORT int JNICALL Java_inductionlabs_jni_bridge_Bridge_object(JNIEnv * env, jclass clas,jobject obj,jsize i)
{
switch(i)
{case 0:k=0;engine::Glgame=env->NewGlobalRef(obj);break;
 case 1:k=1;engine::Batcher=env->NewGlobalRef(obj);break;
 case 2:k=2;engine::GuiCam=env->NewGlobalRef(obj);break;
 case 3:k=3;engine::GameScreen=env->NewGlobalRef(obj);break;
 case 4:k=4;engine::Set=env->NewGlobalRef(obj);break;
 case 6:k=6;engine::Asset=env->NewGlobalRef(obj);break;
 default:k=12;break;
}
//Gaining class handles so can call their methods.
if(i==0)
{engine::assets=env->FindClass("inductionlabs/tervel/Assets");
engine::javaBatcher=env->FindClass("inductionlabs/jni/bridge/BatcherBridge");
engine::setting=env->FindClass("inductionlabs/jni/bridge/tools_seting_bridge");
//Above they are only local references so converting them to global one
engine::assets= (jclass)(env->NewGlobalRef(engine::assets));
engine::javaBatcher=(jclass)(env->NewGlobalRef(engine::javaBatcher));
engine::setting=(jclass)(env->NewGlobalRef(engine::setting));

}
return i;
}

//////////////////////////////////Frame Draw Update/////////////////////////////////////////////
JNIEXPORT void JNICALL Java_inductionlabs_tervel_NativeFun_draw(JNIEnv * env,jclass clas,float dt)
{ Game::Draw(env,dt);
}
///////////////////////////////////////Init the game////////////////////////////////////////////
JNIEXPORT void JNICALL Java_inductionlabs_tervel_NativeFun_createEngine(JNIEnv * env,jclass clas)
{Game::gd.Gamestate=4;
 Game::vm.screenstate=0;

}
//////////////////////////////////////Input update///////////////////////////////////////////////
JNIEXPORT void JNICALL Java_inductionlabs_tervel_NativeFun_update(JNIEnv * env,jclass clas)
{Game::Update(env);
}
/////////////////////////////////Input accelerometer//////////////////////////////////////////////////
JNIEXPORT int JNICALL Java_inductionlabs_tervel_NativeFun_updateacc(JNIEnv * env,jclass clas,float accelX, float accelY,float accelZ)
{Game::Update(env,accelX,accelY,accelZ);
return 1;
}
////////////////////////////////////Input touch///////////////////////////////////////////////////////
JNIEXPORT bool JNICALL Java_inductionlabs_tervel_NativeFun_updatekey(JNIEnv * env,jclass clas,int keyCode, int type)
{return Game::Update(env,keyCode, type);
}
/////////////////////////////////////Input key///////////////////////////////////////////////////////
JNIEXPORT void JNICALL Java_inductionlabs_tervel_NativeFun_updatetou(JNIEnv * env,jclass clas,float x, float y, int type, int pointer)
{Game::Update(env, x, y, type, pointer);
}
//////////////////////////////////////////State Poster//////////////////////////////////////////////////
JNIEXPORT void JNICALL Java_inductionlabs_tervel_NativeFun_poststate(JNIEnv * env,jclass clas,int state)
{Game::State(env, state);
}


///////////////////////////////////////Init the musci and sound////////////////////////////////////////////
JNIEXPORT void JNICALL Java_inductionlabs_tervel_NativeFun_postaudiostate(JNIEnv * env,jclass clas,bool music,bool sound)
{
	Game::postaudiodata(music,sound);

}
};
/////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////End of Jni call  code///////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////




#endif



//Draw Call Functions End



