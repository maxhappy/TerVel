/*
 * gui.h
 *
 *  Created on: 03-Nov-2012
 *      Author: Praveen Ojha
 */

#ifndef GUI_H_
#define GUI_H_

void Game::guiDraw(JNIEnv * env,float dt)
{


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////Draw Gui  Screen Based on Game State and pause game and hit detection if game///////////////////////////////////////
   /////////////////////////////////////////////////////////state is not playing////////////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


int screena[]={70,150,240,330,800};

int butonpush=-1;
int ti=0;
       switch(gd.Gamestate)
       {


      	  ////////////Game Paused///////////
      	  case 1:

      	  case 2:
      	  /////////////Game Resumed/////////
      	  case 3:
      		if(vm.screenstate==0)
      		  vm.screenstate=3;
      		  //else
      		  //vm.screenstate=0;
      		  /* no break */

      	  ////////////////Game new not resumed/////////
      	  case 4:

      	  switch(vm.screenstate)
      	  {
      	             case 0:
      		          engine::setcolor(.2,.6,.1,.4);
      	         	  engine::beginBatch(env,gd.Tbg2);
      	         	  engine::spriteDraw(env,160,200,300,460,0,0,0,gd.bg6);
      	         	  engine::endBatch(env);
      	         	  engine::setcolor(.8,.4,.2,.6);
      	         	  engine::spriteDraw(env,160,screena[(int)gd.pointy/90],300,50,0,0,0,gd.bg7);
      	       	     butonpush=-1;
      	       	      ti=0;
      	         	  while(ti<5)
      	       	     {if((gd.pointerstate==1)&&gd.pointx>80 && gd.pointx<280 &&gd.pointy>screena[4-ti]-25&&gd.pointy<screena[4-ti]+25)
      	       		  {butonpush=ti-1;
      	       		  }ti++;
      	       	      }  if(gd.pointerstate==0 && gd.pointx<60 &&gd.pointy<200&&gd.pointx>0 &&gd.pointy>80)
      	             	 {butonpush=6;
      	             	 }
      	             	 if(gd.pointerstate==0 && gd.pointx>260 &&gd.pointy<200 &&gd.pointx<320 &&gd.pointy>80)
      	             	 {butonpush=7;
      	             	 }
      	             	 switch(butonpush)
      	             	 {
      	             	 case 0:gd.Gamestate=0;Game::Init(env);gd.first=true;gd.life=3;gd.hit=false;gd.score=0;gd.coins=0;engine::playsound(env,2);
      	             	 break;
      	             	 case 1:vm.screenstate=2;engine::playsound(env,2);
      	             	 break;
      	             	 case 2:vm.screenstate=1;engine::playsound(env,2);
      	             	 break;
      	             	 case 3:vm.screenstate=8;engine::playsound(env,2);
      	             	 break;
      	             	 case 4:vm.screenstate=8;engine::playsound(env,2);quit(env);//engine::playsound(env,1);
      	             	 break;

      	             	 /////////////////////////////////disable enable music //////////////////////
      	             	 case 6:
      	             	if(gd.musicstate)gd.musicstate=false;else gd.musicstate=true;
      	             	engine::music(env,(gd.musicstate?1:0),"");//break;
      	             	engine::playsound(env,2);break;
      	             	/////////////////////////////////disable enable sound //////////////////////
      	             	 case 7:
      	             	if(gd.soundstate)gd.soundstate=false;else gd.soundstate=true;
   	             		engine::music(env,(gd.soundstate?3:2),"");break;
   	             		engine::playsound(env,2);break;
      	             	}

                  	  engine::endBatch(env);
      	         	  engine::setcolor(1,1,1,1);
      	        	  engine::beginBatch(env,gd.Tgui);
      	        	  engine::spriteDraw(env,160,240,300,460,0,0,0,gd.start);
      	        	  engine::endBatch(env);
      	        	  engine::beginBatch(env,gd.Tsky);
      	        	  if(gd.musicstate==false)
      	        	  engine::spriteDraw(env,30,130,70,70,0,0,0,gd.musicoff);
      	        	  else
      	        	  engine::spriteDraw(env,30,130,70,70,0,0,0,gd.music);
      	        	  if(gd.soundstate==false)
      	        	  engine::spriteDraw(env,290,130,70,70,0,0,0,gd.soundoff);
      	        	  else
      	        	  engine::spriteDraw(env,290,130,70,70,0,0,0,gd.sound);
      	        	  engine::endBatch(env);
      	        	  if(gd.pointerstate==0)
   	        	  	   __android_log_print(1,"tag","this is pointer 2 y=%d and x =%d",gd.pointy,gd.pointx);

      	        	  break;


      	     ////////////////////////about////////////////////

      	     case 1:

      	    	  engine::beginBatch(env,gd.Tgui);
      	    	  engine::spriteDraw(env,160,240,300,460,0,0,0,gd.about);
                  engine::endBatch(env);
               	  engine::beginBatch(env,gd.Tsky);
      	          engine::spriteDraw(env,30,50,70,70,0,0,0,gd.back);
      	    	  engine::endBatch(env);
      	    	  if(gd.pointerstate==0 && gd.pointx<50 &&gd.pointy<85&&gd.pointx>0 &&gd.pointy>0)
      	    	  {vm.screenstate=0;engine::playsound(env,2);
      	    	  }
      	    	 break;

      	    	 ////////////////////////about////////////////////

      	     case 2:
            	    engine::beginBatch(env,gd.Tgui);
      	  	    engine::spriteDraw(env,160,240,300,460,0,0,0,gd.help);
                  engine::endBatch(env);
      	        engine::beginBatch(env,gd.Tsky);
      	        engine::spriteDraw(env,30,50,70,70,0,0,0,gd.back);
      	        engine::endBatch(env);
      	        if(gd.pointerstate==0 && gd.pointx<50 &&gd.pointy<85&&gd.pointx>0 &&gd.pointy>0)
      	        {vm.screenstate=0;engine::playsound(env,2);
      	        }
      	        break;

      	        ////////////////////////pause////////////////////
      	     case 3:
            	engine::beginBatch(env,gd.Tgui);
      	  	    engine::spriteDraw(env,160,240,300,460,0,0,0,gd.resume);
                engine::endBatch(env);
      	        engine::beginBatch(env,gd.Tsky);
      	        engine::spriteDraw(env,30,50,70,70,0,0,0,gd.back);
      	        engine::endBatch(env);
      	       gd.Gamestate=4;
               if(gd.pointerstate==0 && gd.pointx<90 &&gd.pointy<100&&gd.pointx>0 &&gd.pointy>0)
      	        {  vm.screenstate=0;
      	          //engine::playsound(env,2);
      	        }
      	        //////////////////resume//////////////

      	        if(gd.pointerstate==0 && gd.pointx>90 &&gd.pointy<290&&gd.pointx<270 &&gd.pointy>190)
      	         { gd.Gamestate=0;
      	         }
      	         break;


      	         ///////////////////////Game Over////////////////////


      	     case 4:

      	    	                 engine::beginBatch(env,gd.Tgameover);
      	    	      	    	  engine::spriteDraw(env,160,360,210,100,0,0,0,gd.gameover);
      	    	                  engine::endBatch(env);

      	    	                  //engine::beginBatch(env,gd.Tfont);
      	    	         //engine::textDraw(env,10,300,1.4,1.5,1," Game Over ",2,2,.7,1," ");
      	    		     //engine::endBatch(env);
      	    	engine::beginBatch(env,gd.Tsky);
      	        engine::spriteDraw(env,30,50,70,70,0,0,0,gd.back);
      	        engine::endBatch(env);
      	        if(gd.pointerstate==0 && gd.pointx<90 &&gd.pointy<120&&gd.pointx>0 &&gd.pointy>0)
                  {vm.screenstate=0;gd.Gamestate=4;
                  engine::playsound(env,2);
                  }
      	        break;
///////////////////////////////////////////High Score////////////////////////////////////////////////////////////
      	   case 8:
      	         	    	engine::beginBatch(env,gd.Tfont);
      	         	    	engine::textDraw(env,80,420,1.2,1.2,1,"HighScores",1,1,.5,1," ");
      	         	    	engine::scoreDraw(env,80,340,200,200,0,0,0,1);
      	         	        engine::endBatch(env);
      	         	    	engine::beginBatch(env,gd.Tsky);
      	         	        engine::spriteDraw(env,30,50,70,70,0,0,0,gd.back);
      	         	        engine::endBatch(env);
      	         	        if(gd.pointerstate==0 && gd.pointx<90 &&gd.pointy<120&&gd.pointx>0 &&gd.pointy>0)
      	                     {vm.screenstate=0;gd.Gamestate=4;
      	                     engine::playsound(env,2);
      	                     }
      	         	        break;




      	  }break;

       }




}




#endif /* GUI_H_ */
