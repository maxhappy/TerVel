#include "inc.h"
#include <stdlib.h>

 Game::Game(JNIEnv *env){}
 Game::~Game(){}
int tspeed=-250; int lk=1,relex_time=0;
int speed=-250;float boost=1;
 void Game::Draw(JNIEnv * env,float dt)
{
	/// if(gd.first)
	/// {relex_time=0;
	 ///}

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////Base Background Drawn///////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 engine::beginBatch(env,gd.Tsky);
 engine::spriteDraw(env,160,240,400,550,0,0,0,gd.stf);
 engine::endBatch(env);

 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 ////////////////////////////////////////////Enabled Transparency///////////////////////////////////////////////////////////////////////
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


 glEnable(GL_BLEND);
 glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
 boost=1;
 if(gd.coins>50&&gd.pointerstate==1)
 {boost=1.5;gd.coins-=1;
 }
 speed=tspeed*boost;
 speed=speed-(gd.score/50);
 if(gd.Gamestate==0)
 {gd.score+=.1*boost-speed/250;
 relex_time+=-1*dt*speed;
 }
 lk =3*speed/250;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////sending  the bottom regions to top ///////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    int a;

      if(r1.regiony<gd.minreg)
       { r1.regiony=gd.maxreg;a=2;
       r1.regionversion=rand()%10;
       genregion(1);
       }
      if(r2.regiony<gd.minreg)
       {  r2.regiony=gd.maxreg;a=3;
       r1.regionversion=rand()%10;
       }
      if(r3.regiony<gd.minreg)
        { r3.regiony=gd.maxreg;a=1;
        r1.regionversion=rand()%10;
        }
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      ////////////////////////////////////////////Drawing the regions///////////////////////////////////////////////////////////////////////
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

      engine::setcolor(.4,.6,.4,1);
      drawbg(env,dt,r3.regionx,(int)r3.regiony,r3.regionversion);
      drawbg(env,dt,r1.regionx,(int)r1.regiony,r1.regionversion);
      drawbg(env,dt,r2.regionx,(int)r2.regiony,r2.regionversion);
      engine::setcolor(1,1,1,1);



      //////////////////////////////////////////////////////////////////////////////
      //////////////////////////////////////////////////////////////////////////////
      ////////////////////////////Draw Enemies//////////////////////////////////////
      ////////////type 0 left wall emf, type 1 right wall emf and type 2////////////
      //////////////////////electric shock n type 3 rotating electric shock/////////
      //////////////////////////////////////////////////////////////////////////////
      engine::beginBatch(env,gd.Titems);

      int l=0;
      if(gd.Gamestate==0)
      while(l<gd.maxenemey)
      {  switch (gd.enemytype[l])
    	  {
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      ////////////////////////////////////////////Drawing emf and appling their effect on hero that is a suction effect ///////////////////////////////////////////////////////////////////////
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



          case 0:
    	  drawitems(env,dt,r1.regionx,r1.regiony+gd.enemyy[l],0,0,0);
    	  if(r1.regiony+gd.enemyy[l]>0&&r1.regiony+gd.enemyy[l]<70&&gd.herox<120)
    	  gd.herox+=lk;break;
    	  case 1:drawitems(env,dt,r1.regionx,r1.regiony+gd.enemyy[l],1,0,0);
    	  if(r1.regiony+gd.enemyy[l]>0&&r1.regiony+gd.enemyy[l]<70&&gd.herox>180)
    	  gd.herox-=lk;
    	  break;
       /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       ////////////////////////////////////////////Drawing current fields and appling their effect on hero///////////////////////////////////////////////////////////////////////
       ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    	    case 2:
    	    case 3:int a=gd.enemylength[l]*cos(gd.enemyangle[l]*0.017453)/2;int b=gd.enemylength[l]*sin(gd.enemyangle[l]*0.017453)/2;
    		drawitems(env,dt,gd.enemyx[l]-a,r1.regiony+gd.enemyy[l]-b,2,0,0);
    		drawitems(env,dt,gd.enemyx[l]+a,r1.regiony+gd.enemyy[l]+b,2,0,0);

    		if(gd.Gamestate==0&&(     ((pow((gd.herox -(gd.enemyx[l]-a)),2.0)+pow((gd.heroy -(r1.regiony+gd.enemyy[l]-b)),2.0))<500)||
    				((pow((gd.herox -(gd.enemyx[l]+a)),2.0)+pow((gd.heroy -(r1.regiony+gd.enemyy[l]+b)),2.0))<500)||
    				((pow((gd.herox -(gd.enemyx[l])),2.0)+pow((gd.heroy -(r1.regiony+gd.enemyy[l])),2.0))<800)

    				))
    		{

    			if(gd.life==0)
    			{gd.hit=true;
    			}

    			/////////////////////////////relexing player to survi some time//////////////////////////
    			if(relex_time>200)
    	        {gd.life--;relex_time=0;
                 engine::playsound(env,1);
    	        }
    		}

              break;
          ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          ////////////////////////////////////////////end of single enemy drawing based on its type///////////////////////////////////////////////////////////////////////
          /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    	  }
    	 l++;
      }

     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //////////////////////Draw Coins and check collision we are using square distance method to detect collison ///////////////////
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

if(gd.hit&&gd.Gamestate==0)
{gd.Gamestate=4;
vm.screenstate=4;
engine::write(env,gd.score);
}

      int  ki=0;
      while(ki<gd.maxcoins)
       {
    	  int ax=gd.coinarrayx[ki],ay=gd.coinarrayy[ki];

    	  if((pow((gd.herox -(ax)),2.0)+pow((gd.heroy -(ay+r1.regiony)),2.0))<400)
    	  {gd.coins+=50;engine::playsound(env,gd.Scoin);
    	   gd.coinarrayx[ki]-=500;
    	  }
          drawitems(env,dt,ax,ay+r1.regiony,5,0,0);
    	 ki++;
        }

	  ////////////////////////////////////////////////////////////////////////////////
	  ////////////////////////////////////////////////////////////////////////////////
	  ////////////////////////////////Draw PatternCoins not in this //////////////////
	  /////////////////////////////ersion but draw shapes from coins//////////////////
      ////////////////////////////////////////////////////////////////////////////////
       /*
	  drawPattern(env,dt,r2.regionx ,r2.regiony-400,r2.coinsp1,4,0);
      drawPattern(env,dt,r2.regionx ,r2.regiony,r2.coinsp2,1,0);
      drawPattern(env,dt,r2.regionx ,r2.regiony+400,r2.coinsp3,6,0);
      drawPattern(env,dt,r1.regionx ,r1.regiony-400,r1.coinsp1,4,0);
      drawPattern(env,dt,r1.regionx ,r1.regiony,r1.coinsp2,1,0);
      drawPattern(env,dt,r1.regionx ,r1.regiony+400,r1.coinsp3,6,0);
      drawPattern(env,dt,r1.regionx ,r1.regiony-400,r1.coinsp1,4,0);
      drawPattern(env,dt,r3.regionx ,r3.regiony,r1.coinsp2,1,0);
      drawPattern(env,dt,r3.regionx ,r3.regiony+400,r1.coinsp3,6,0);*/
      ///////////////////////////////////////////////////////////////////////////////

      ///////////////////////////////////////////////////////////////////////////////
      //////////////////////////////Draw hero //////////////////////////////////////
      ///////////////////////////////////////////////////////////////////////////////
      engine::setcolor(1,1,1,1);
      if(relex_time<200)
      {engine::setcolor(1,1,1,(relex_time%1?.5:1));
      }
      drawhero(env,dt,gd.herox ,gd.heroy,0,3,gd.heroangle);
      engine::endBatch(env);

      ///////////////////////////////////////////////////////////////////////////////
      ///////////////////////////////////////////////////////////////////////////////
      /////////////////////////////Draw hero flame///////////////////////////////////
      engine::beginBatch(env,gd.Thero);
      drawhero(env,dt,gd.herox,gd.heroy-25,3,0,gd.heroangle+180);
      engine::endBatch(env);



      /////////////////////////////////////////////////////////////////////////////////////////////////////////
      /////////////////////////////Draw current over current fields//////////////////////////////////
      /////////////////////////////////////////////////////////////////////////////////////////////////////////
      if(gd.Gamestate==0)
         {
    	  engine::beginBatch(env,gd.Tcur);
         int i=0;

         while(i<gd.maxenemey)
         { if(gd.enemytype[i]==3||gd.enemytype[i]==2)
             drawcurrent(env, dt, gd.enemyx[i],gd.enemyy[i]+r1.regiony,.3*gd.enemylength[i]/140,.4*gd.enemylength[i]/140,0,0, gd.enemyangle[i]);
        	 else
             drawcurrent(env, dt, gd.enemyx[i]-800,gd.enemyy[i]-800,.2,.3,0,0, gd.enemyangle[i]+90);
         i++;
         }
         engine::endBatch(env);
      /////////////////////////////////////////////////////////////////////////////////////////////////////////
      /////////////////////////////////////////////////////////////////////////////////////////////////////////
      /////////////////////////////////////////////////////////////////////////////////////////////////////////
     /////////////////////////////////////////////////////////////////////////////////////////////////////////
      ///////////////////////////////////////////////Draw score and coins//////////////////////////////////////
      /////////////////////////////////////////////////////////////////////////////////////////////////////////




      engine::beginBatch(env,gd.Tfont);
         engine::textDraw(env,10,460,2,1,1,"Score",1,1,.4,1," ");
	     engine::drawnumber(env,(int)gd.score,80,430,15);
	     engine::textDraw(env,10,400,2,1,1,"Life",1,1,.4,1," ");
	     engine::drawnumber(env,(int)gd.life,50,380,15);
	     engine::textDraw(env,200,460,2,1,1,"Battery",1,1,.3,1," ");
	     engine::drawnumber(env,gd.coins,(gd.score>999?260:280),430,15);
	     engine::endBatch(env);
   ///////GamePlaying////////////////
//////////////////////////
            r1.regiony+=dt*speed;
            r2.regiony+=dt*speed;
            r3.regiony+=dt*speed;
     ///////////////////////////////////////		  break;

  }
 guiDraw(env,dt);
 glDisable(GL_BLEND);
}




 void engine::drawnumber(JNIEnv *env,int n, int x,int y,int stepx)
 {    while(n > 0)
	   {switch(n%10)
	     {case 0:engine::textDraw(env,x,y,2,1,1,"0",1,1,.4,1," ");break;
	      case 1:engine::textDraw(env,x,y,2,1,1,"1",1,1,.4,1," ");break;
	      case 2:engine::textDraw(env,x,y,2,1,1,"2",1,1,.4,1," ");break;
	      case 3:engine::textDraw(env,x,y,2,1,1,"3",1,1,.4,1," ");break;
	      case 4:engine::textDraw(env,x,y,2,1,1,"4",1,1,.4,1," ");break;
	      case 5:engine::textDraw(env,x,y,2,1,1,"5",1,1,.4,1," ");break;
	      case 6:engine::textDraw(env,x,y,2,1,1,"6",1,1,.4,1," ");break;
	      case 7:engine::textDraw(env,x,y,2,1,1,"7",1,1,.4,1," ");break;
	      case 8:engine::textDraw(env,x,y,2,1,1,"8",1,1,.4,1," ");break;
          case 9:engine::textDraw(env,x,y,2,1,1,"9",1,1,.4,1," ");break;
	    }
	     n = n / 10;
	     x-=stepx;
	   }

 }

void Game::State(JNIEnv *env,int state)
{gd.Gamestate=state;
}

void Game::quit(JNIEnv *env)
{
	engine::performtask(env,-34);

}


jstring Game::itoa(JNIEnv *env,int x)
{ char *  dest;
  int i = (int) log10((double) x);
  while(x > 0)
  { dest[i] = (x % 10) + '0';
    x = x / 10;
    i = i - 1;
  }
  dest[i] = '\0';
  return env->NewStringUTF(dest);
}
