/*
 * items.h
 *
 *  Created on: 31-Oct-2012
 *      Author: Praveen Ojha
 */

#ifndef ITEMS_H_
#define ITEMS_H_

 static int batch=0;
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 //////////////////////////////////////////////////Draw back ground ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 void Game::drawbg(JNIEnv *env,float dt,int x,int y,int index)
    {
	 if(batch==0){Game::Init(env);batch=1;}
	 if(index>11)index=11;
	 int sprite[]={gd.bg3,gd.bg4,gd.bg1,gd.bg2,gd.bg4,gd.bg5,gd.bg1,gd.bg5,gd.bg4,gd.bg3,gd.bg1,gd.bg2,gd.bg5,gd.bg5};
     int br[]={gd.brick1,gd.brick2,gd.brick3,gd.brick,gd.brick1,gd.brick,gd.brick,gd.brick1,gd.brick1,gd.brick2,gd.brick3,
    		  gd.brick3,gd.brick3,gd.brick2,gd.brick1,gd.brick1,gd.brick3,gd.brick2,gd.brick1,gd.brick2};
     int door[]={gd.door,gd.door1,gd.door2,gd.door3};
     int doorbl[]={gd.bridge,gd.bridge1,gd.bridge3};

	 int sprite1[]={gd.bg6,gd.bg7,gd.lift,gd.barbedwire,gd.platformbar};
	 int spritewidth,spriteheight,gd1,gd2;
	 gd1=y+480;
	 gd2=y-480;

	 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     ////////////////////////                 Draw the Base background                      /////////////////////////////////////
	 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


     ////////////enable transparency//////////////
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     ///////////////////////                 Draw the Over Art on background                 /////////////////////////////////////
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  //  engine::beginBatch(env,gd.Tover);
  //  engine::spriteDraw(env,x,y,gd.regionwidth[door[index/4]]*.7,gd.regionheight[door[index/4]]*.6,0,0,0,door[index/4]);
   // engine::endBatch(env);

     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      ///////////////////////                 Draw the Lift and its holder   barbed wire etc               //////////////////////////////////////
      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	  engine::beginBatch(env,gd.Tbg2);
     // engine::spriteDraw(env,x+15,gd.lift1y,gd.regionwidth[sprite1[2]]*.5,gd.regionheight[sprite1[2]]*.7,0,0,0,sprite1[2]);
     // engine::spriteDraw(env,x-15,gd.lift2y,gd.regionwidth[sprite1[2]]*.5,gd.regionheight[sprite1[2]]*.7,0,0,0,sprite1[2]);
      engine::spriteDraw(env,x,y+155,gd.regionwidth[gd.barbedwire]*1.2,gd.regionheight[gd.barbedwire]*.7,0,0,0,gd.barbedwire);
      engine::spriteDraw(env,x,y-155,gd.regionwidth[gd.barbedwire]*1.2,gd.regionheight[gd.barbedwire]*.7,0,0,0,gd.barbedwire);
      engine::spriteDraw(env,x,y-20,gd.regionwidth[sprite1[4]],gd.regionheight[sprite1[4]]*2.5,0,0,0,sprite1[4]);
      engine::spriteDraw(env,x,y+gd.regionheight[sprite1[4]],gd.regionwidth[sprite1[4]],gd.regionheight[sprite1[4]]*2.5,0,0,0,sprite1[4]);
      //engine::spriteDraw(env,x,y-gd.regionheight[sprite1[4]]*2,gd.regionwidth[sprite1[4]],gd.regionheight[sprite1[4]]*2,0,0,0,sprite1[4]);
     // engine::spriteDraw(env,x,y+gd.regionheight[sprite1[4]]*2,gd.regionwidth[sprite1[4]],gd.regionheight[sprite1[4]]*2,0,0,0,sprite1[4]);
      engine::endBatch(env);

      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      ///////////////////////                 Draw the Over Art on lift foreground                 /////////////////////////////////////
      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


     //engine::beginBatch(env,gd.Tover);
     //engine::spriteDraw(env,x,y-10-gd.regionheight[sprite1[4]]/2,gd.regionwidth[door[index/4]]*.6,gd.regionheight[sprite1[index/4]]*.3,0,0,0,doorbl[index%4]);
     //engine::spriteDraw(env,x,y+10+gd.regionheight[sprite1[4]]/2,gd.regionwidth[door[index/4]]*.6,gd.regionheight[sprite1[index/4]]*.3,0,0,0,doorbl[index%3]);
    // engine::endBatch(env);


     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     ///////////////////////                 Draw side  wall using bricks                  /////////////////////////////////////
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     engine::beginBatch(env,gd.Tbg);
     int k=gd.regionheight[gd.platformbar]/gd.regionheight[gd.brick]+1;
     int i=0;
     int w=gd.regionheight[gd.brick];
     while(i<4*k)
     {   drawwall(env,dt,x-20,y-(w*k/2)+i*gd.regionheight[br[index+i%10]]*.59,0,br[index+i%10],0);
         drawwall(env,dt,x+20,y-(w*k/2)+i*gd.regionheight[br[index+i%10]]*.59,1,br[index+i%10],1);
       i++;
     }  i=0;
        while(i<4*k)
          {  drawwall(env,dt,x-20,y-90-(w*k/2)+i*gd.regionheight[br[index+i%10]]*.59,2,br[index+(40-i)%10],2);
         	 drawwall(env,dt,x+20,y-90-(w*k/2)+i*gd.regionheight[br[index+i%10]]*.59,3,br[index+(40-i)%10],3);i++;
          }i=0;


          engine::endBatch(env);


     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     ////////////////////////////////////////////////////////Nothing more to draw for back ground//////////////////////////////////////////
     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   /*  {
     int nt=0;
     if(gd.pointy<50)
    	 nt=0;
     else if(gd.pointy<100)
    	 nt=1;
     else if(gd.pointy<150)
         nt=2;
     else if(gd.pointy<200)
         nt=3;
     else if(gd.pointy<250)
         nt=4;
     }*/

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }


 //////////////////////////////////////////////////Draw wall brick by brick////////////////////////////////////////////////////////////////////////////////////////////////////////////
void Game::drawwall(JNIEnv *env,float dt,int x,int y,int object,int sprite,float angle)

  { int spritewidth=gd.regionwidth[sprite];
    int spriteheight=gd.regionheight[sprite];
  ////////////index as per there draw order first to last ///////////////////
   switch (object)
	{
	case 0:engine::spriteDraw(env,x-160+spritewidth/2+.46*spritewidth,y-210+spriteheight*.59,spritewidth,spriteheight,0,0,0,sprite);break;
	case 1:engine::spriteDraw(env,x+160-spritewidth/2-.46*spritewidth,y-210+spriteheight*.59,-1*spritewidth,spriteheight,0,0,0,sprite);break;
	case 2:engine::spriteDraw(env,x-160+spritewidth/2,y-210+spriteheight*.72,spritewidth,spriteheight,0,0,0,sprite);break;
	default:engine::spriteDraw(env,x+160-spritewidth/2,y-210+spriteheight*.72,-1*spritewidth,spriteheight,0,0,0,sprite);break;

	}

  }


//////////////////////////////////////////////////draw Enemy,pickup,ghost they don't have privilege of closing and opening batcher///////////////////////////////////////////

static float curtime=0;
void Game::drawitems(JNIEnv *env,float dt,int x,int y,int grp,int index,float angle)
     {

		 int cursig[]={gd.charg1,gd.charg2,gd.charg3,gd.charg4};
	     int ghost[]={ gd.ghost_1, gd.ghost_2, gd.ghost_3, gd.ghost_4, gd.ghost_5, gd.ghost_6, gd.ghost_7, gd.ghost_8,gd.ghost_9, gd.ghost_10,
	    		      gd.ghost_11,gd.ghost_12,gd. ghost_13, gd.ghost_14,gd.ghost_15, gd.ghost_16, gd.ghost_17, gd.ghost_18, gd.ghost_19, gd.ghost_20,
	    		      gd.ghost_21, gd.ghost_22, gd.ghost_23, gd.ghost_24, gd.ghost_25};

		 float spritewidth=gd.regionwidth[gd.emf]*.4;
		 float spriteheight=gd.regionheight[gd.emf]*.4;


		 int cin=((int)(curtime*5))%4;
		 curtime+=dt/2;
		 if(curtime>50000000)
		 {curtime=0;
		 }
		 switch (grp)
		 	{	case 0:engine::spriteDraw(env,x-110,y,spritewidth,spriteheight,0,0,0,gd.emf);
		 	    engine::spriteDraw(env,x-108,y-10,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+1)%4]);
		 	    engine::spriteDraw(env,x-108,y-5,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+2)%4]);
		 	    engine::spriteDraw(env,x-108,y+5,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+3)%4]);
		 	    engine::spriteDraw(env,x-108,y+10,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+4)%4]);

		 	    break;
		    	case 1:engine::spriteDraw(env,x+110,y,-1*spritewidth,spriteheight,0,0,0,gd.emf);
                   		    	engine::spriteDraw(env,x+108,y-10,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+1)%4]);
		    			 	    engine::spriteDraw(env,x+108,y-5,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+2)%4]);
		    			 	    engine::spriteDraw(env,x+108,y+5,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+3)%4]);
		    			 	    engine::spriteDraw(env,x+108,y+10,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+4)%4]);

            	      break;
		    	case 2:engine::spriteDraw(env,x,y,gd.regionwidth[gd.pmcharge],gd.regionheight[gd.pmcharge],0,0,0,gd.pmcharge);break;
		    	case 3:engine::spriteDraw(env,x,y,gd.regionwidth[ghost[index]]*.5,gd.regionheight[ghost[index]]*.5,0,0,0,ghost[index]);break;
		    	case 4:engine::spriteDraw(env,x,y,-1*gd.regionwidth[ghost[index]]*.5,gd.regionheight[ghost[index]]*.5,0,0,0,ghost[index]);break;
		    	case 5:engine::spriteDraw(env,x,y, gd.regionwidth[gd.pickupbattery]*.25,gd.regionheight[gd.pickupbattery]*.25,0,0,0,gd.pickupbattery);
		    	  if((cin+1)%4)
		    	  engine::spriteDraw(env,x+gd.regionwidth[gd.pickupbattery]*.125-gd.regionwidth[gd.batterybar]*.125,y,gd.regionwidth[gd.batterybar]*.25,gd.regionheight[gd.batterybar]*.25,0,0,0,gd.batterybar);
		    	  else
		    	  engine::spriteDraw(env,x+gd.regionwidth[gd.pickupbattery]*.125-gd.regionwidth[gd.batterybar]*.125,y,gd.regionwidth[gd.batterybar]*.25,gd.regionheight[gd.batterybar]*.25,180,0,0,gd.batterybar);
		    	break;


		    }


     }

static int herotime=0;
void Game::drawhero(JNIEnv *env,float dt,int x,int y,int grp,int index,float angle)
     {int flame[]={gd.flame1,gd.flame2,gd.flame3,gd.flame4,gd.flame5,gd.flame6};
      int cin=((int)(herotime))%6;
    		 herotime+=dt*100;
     		 if(herotime>50000000)
    		 {herotime=0;
    		 }
    		switch(grp)
    		 {case 0 :engine::spriteDraw(env,x,y,gd.regionwidth[gd.ph0]*.6,gd.regionheight[gd.ph0]*.6,angle,0,10,gd.ph2);
                  break;
    		 default:
    			 engine::spriteDraw(env,x,y-14,gd.regionwidth[flame[(cin+1)%4]]*.6,gd.regionheight[flame[(cin+1)%4]]*.6,angle,0,0,flame[(cin)%6]);

    			 break;
    		 }


     }
//////////////////////////////////////////////cur draw/////////////////////////////////////////////////////////////
  static float curcurtime=0;
  void Game::drawcurrent(JNIEnv *env,float dt,int x,int y,float sx,float sy,int object,int sprite,float angle)
     {
	   int cin=((int)(curcurtime*5))%9;
	  curcurtime+=dt/1.25;if(curcurtime>50000000){curcurtime=0;}

	     int a[]={gd.boly_tesla1,gd.boly_tesla2,gd.boly_tesla3,gd.boly_tesla4,gd.boly_tesla5,gd.boly_tesla6,gd.boly_tesla7,gd.boly_tesla8,gd.boly_tesla9,gd.boly_tesla10};
	     float spritewidth=gd.regionwidth[a[object]];
	     float spriteheight=gd.regionheight[a[object]];spritewidth*=sx;spriteheight*=sy;
        ////////////index as per there draw order first to last ///////////////////

    	 engine::spriteDraw(env,x,y,spritewidth,spriteheight,angle,0,0,a[cin]);

     }
//////////////////////////////////////strike current /////////////////////////////////////////////////
  void Game::drawstrike(JNIEnv *env,float dt,int x,int y,int object,int sprite,float angle)
     {
	     int a[]={gd.bolt_strike2,gd.bolt_strike3,gd.bolt_strike4,gd.bolt_strike5,gd.bolt_strike6,gd.bolt_strike7,gd.bolt_strike9,gd.bolt_strike10};
	     float spritewidth=gd.regionwidth[a[object]];
	     float spriteheight=gd.regionheight[a[object]];
	     spritewidth*=.1;
	     spriteheight*=.2;

        ////////////index as per there draw order first to last ///////////////////
	     int cin=((int)(curcurtime*5))%6;
	   	  curcurtime+=dt/1.25;if(curcurtime>50000000){curcurtime=0;}
         engine::spriteDraw(env,x,y,spritewidth,spriteheight,angle,0,0,a[cin]);
     }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



#endif /* ITEMS_H_ */
