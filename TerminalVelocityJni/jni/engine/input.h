/*
 * input.h
 *
 *  Created on: 31-Oct-2012
 *      Author: Praveen Ojha
 */
#ifndef Update_H_
#define Update_H_
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////updates keyboard accelerometer abd touch screen/////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



 void Game::Update(JNIEnv *env,float ax,float ay,float az)
   {gd.cur_ax=ax;
    gd.cur_ay=ay;
    gd.cur_az=az;
    if(gd.herox>=gd.herominx&&gd.herox<=gd.heromaxx&&gd.Gamestate==0)
    {gd.herox=gd.herox+ax*gd.senstivity;
     }
         if(gd.herox<gd.herominx)
        	gd.herox=gd.herominx;
         if(gd.herox>gd.heromaxx)
        	gd.herox=gd.heromaxx;

   }

 void Game::Update(JNIEnv *env)
  {

  }

int bp=0;

   bool Game::Update(JNIEnv *env,int key,int state)
   {
	 gd.key=key;
     gd.keystate=state;

     if(gd.key==AKEYCODE_BACK&&gd.keystate==0)
    {if(gd.Gamestate==0)
     gd.Gamestate=3;
    else if(vm.screenstate!=0)
    {vm.screenstate=0;
     gd.Gamestate=4;
     }
    if(gd.Gamestate==4&&vm.screenstate==3)
    {
    	if(bp==1)
    	{bp=0;
    	return false;
    	}
    	else
    	{bp++;
    	}
    }
    if(gd.Gamestate==4&&vm.screenstate==0)
        {
        	if(bp==1)
        	{bp=0;
        	return false;
        	}
        	else
        	{bp++;
        	}
        }
    return true;
   }



/////////////////////////////////////hero on emulator via left and right/////////////////
    if(gd.herox>=gd.herominx&&gd.herox<=gd.heromaxx&&gd.Gamestate==0)
       {
    	if(key==AKEYCODE_DPAD_LEFT&&state==AKEY_STATE_DOWN)
    	 gd.herox=gd.herox-gd.senstivity;
        else if(key==AKEYCODE_DPAD_RIGHT &&state==AKEY_STATE_DOWN)
        gd.herox=gd.herox+gd.senstivity;


       if(gd.herox<gd.herominx)
            	gd.herox=gd.herominx;
             if(gd.herox>gd.heromaxx)
            	gd.herox=gd.heromaxx;

     return false;
    }
    return false;
   }


   void Game::Update(JNIEnv *env,float tx,float ty,int state,int index)
    {



     if(index==0)
	 {gd.pointx=tx;
     gd.pointy=ty;
     gd.pointerstate=state;
	 }
     if(index==1)
     {gd.pointx1=tx;
     gd.pointy1=ty;
     gd.pointerstate1=state;
     }






         ////////////////////////////////////touch based action coded here like menu swap //////////





    }


   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


#endif
