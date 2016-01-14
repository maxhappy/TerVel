package inductionlabs.nativ;

import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.Toast;
import inductionlabs.tervelhd.Assets;
import inductionlabs.tervelhd.GameScreen;
import inductionlabs.tervelhd.TerVel;


public class Game
    

    {
    //////////////////////////////////Game Class all game related update ,Display done here /////////////////////////
    static boolean musci,soundst;
    public static RegionData r1,r2,r3;
    public static ViewManager vm;
    public static GameData gd;
    static long k=0;
    static int tspeed=-300, lk=1,relex_time=0;
    static int speed=-300;
    static float boost=1;
    static float r=.4f,g=.6f,b=.8f;
    static float []sr={.2f,.4f,.4f,.2f,.4f,.5f,.6f,.5f,.3f,.5f,.4f};
    static float []sg={.2f,.3f,.5f,.7f,.2f,.4f,.5f,.6f,.7f,.4f,.5f};
    static float []sb={.5f,.6f,.5f,.3f,.5f,.4f,.2f,.4f,.4f,.2f,.4f};
    static float curtime=0;    
    static int batch=0;

    public  static void Draw(float dt)
                {
                /// if(gd.first)
                /// {relex_time=0;
                ///}

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////Base Background Drawn///////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                engine.beginBatch((int)GameData.Etex.Tsky.ordinal());
                engine.spriteDraw(160,240,400,550,0,0,0f,(int)GameData.Ereg.stf.ordinal());
                engine.endBatch();
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////Enabled Transparency///////////////////////////////////////////////////////////////////////
                // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                   
                GameScreen.gl.glEnable(GL10.GL_BLEND);
               GameScreen.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
                
                boost=1;
                if(gd.coins>50)//&&gd.pointerstate==1)
                {
                boost=1.5f;gd.coins-=1;
                }


                speed=(int)(tspeed*boost);
                speed=speed-(int)(gd.score/50);
                if(gd.Gamestate==0)
                {gd.score+=.1f*boost-speed/300;
                
                 if (gd.score%3600 < 1&&gd.life<5)
                { gd.life++; 
                }
                relex_time+=(int)(-1*dt*speed);
                }
                lk =3*speed/300;

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////sending  the bottom regions to top ///////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                int a;

                if(r1.regiony<gd.minreg)
                { r1.regiony=gd.maxreg;a=2;
                r1.regionversion=rand()%10;
                
                int sa=(((int)(gd.score))/800)%10;
                r=sr[sa];
                g= sg[sa];
                b=sb[sa];
                genregion(2);

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

                engine.setcolor(.4f,.5f,.8f,1f);

                drawbg(dt, (int)r3.regionx, (int)r3.regiony, r3.regionversion);
                drawbg(dt, (int)r1.regionx, (int)r1.regiony, r1.regionversion);

                engine.setcolor(r,b,g,1);
                drawbg(dt, (int)r2.regionx, (int)r2.regiony, r2.regionversion);
                engine.setcolor(1,1,1,1);

                genregion(1);

                //////////////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////////////
                ////////////////////////////Draw Enemies//////////////////////////////////////
                ////////////type 0 left wall emf, type 1 right wall emf and type 2////////////
                //////////////////////electric shock n type 3 rotating electric shock/////////
                //////////////////////////////////////////////////////////////////////////////
                engine.beginBatch(GameData.Etex.Titems.ordinal());

                int l=0;
                if(gd.Gamestate==0)
                while(l<gd.maxenemey)
                {  switch (gd.enemytype[l])
                {
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////Drawing emf and appling their effect on hero that is a suction effect ///////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



                case 0:  drawitems(dt, r1.regionx, gd.enemyy[l], 0, 0, 0);
                if(gd.enemyy[l]>0&&gd.enemyy[l]<70&&gd.herox<120)
                gd.herox+=lk;break;
                case 1: drawitems(dt, r1.regionx,  gd.enemyy[l], 1, 0, 0);
                if(gd.enemyy[l]>0&&gd.enemyy[l]<70&&gd.herox>180)
                gd.herox-=lk;
                break;
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////Drawing current fields and appling their effect on hero///////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


                case 2:
                case 3: int aq = (int)(gd.enemylength[l] * Math.cos(gd.enemyangle[l] * 0.017453) / 2); int bq = (int)(gd.enemylength[l] * Math.sin(gd.enemyangle[l] * 0.017453) / 2);
                drawitems(dt,gd.enemyx[l]-aq,(float)gd.enemyy[l]-bq,2,0,0);
                drawitems(dt,gd.enemyx[l]+aq,(float)gd.enemyy[l]+bq,2,0,0);

                if(gd.Gamestate==0&&(     ((pow((gd.herox -(gd.enemyx[l]-aq)),2)+pow((gd.heroy -(gd.enemyy[l]-bq)),2))<500)||
                ((pow((gd.herox -(gd.enemyx[l]+aq)),2)+pow((gd.heroy -(gd.enemyy[l]+bq)),2))<500)||
                ((pow((gd.herox -(gd.enemyx[l])),2)+pow((gd.heroy -(gd.enemyy[l])),2))<800)

                ))
                {

                if(gd.life==0)
                {gd.hit=true;
                }

                /////////////////////////////relexing player to survi some time//////////////////////////
                if(relex_time>400)
                {gd.life--;relex_time=0;
                engine.playsound( 1);
                }
                }

                break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////end of single enemy drawing based on its type///////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


                }
                gd.enemyy[l]+=dt*speed;
                l++;
                }
                
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //////////////////////Draw Coins and check collision we are using square distance method to detect collison ///////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                if(gd.hit&&gd.Gamestate==0)
                {gd.Gamestate=4;
                vm.SetState(4);
                engine.write((int)gd.score);
                }

                int  ki=0;
                while(ki<gd.maxcoins)
                {
                int ax=gd.coinarrayx[ki],ay=gd.coinarrayy[ki];

                if((pow((float)(gd.herox -(ax)),2)+pow((float)(gd.heroy -(ay+r1.regiony)),2))<400)
                {gd.coins+=20;engine.playsound((int)GameData.Eso.Scoin.ordinal());
                gd.coinarrayx[ki]-=500;
                }
                drawitems(dt,ax,ay+r1.regiony,5,0,0);
                ki++;
                }

                ////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////Draw PatternCoins not in this //////////////////
                /////////////////////////////ersion but draw shapes from coins//////////////////
                ////////////////////////////////////////////////////////////////////////////////
                /*
                drawPattern( dt,r2.regionx ,r2.regiony-400,r2.coinsp1,4,0);
                drawPattern( dt,r2.regionx ,r2.regiony,r2.coinsp2,1,0);
                drawPattern( dt,r2.regionx ,r2.regiony+400,r2.coinsp3,6,0);
                drawPattern( dt,r1.regionx ,r1.regiony-400,r1.coinsp1,4,0);
                drawPattern( dt,r1.regionx ,r1.regiony,r1.coinsp2,1,0);
                drawPattern( dt,r1.regionx ,r1.regiony+400,r1.coinsp3,6,0);
                drawPattern( dt,r1.regionx ,r1.regiony-400,r1.coinsp1,4,0);
                drawPattern( dt,r3.regionx ,r3.regiony,r1.coinsp2,1,0);
                drawPattern( dt,r3.regionx ,r3.regiony+400,r1.coinsp3,6,0);*/
                ///////////////////////////////////////////////////////////////////////////////
                //////////////////////////////Draw hero //////////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////////
                engine.endBatch();
                
                engine.setcolor(1,1,1,1);
                if(relex_time<450)
                {engine.setcolor(1f,1f,1f,(relex_time%60==0?1f:.5f));
                }
                drawhero(dt,gd.herox ,gd.heroy,0,3,gd.heroangle);
                engine.endBatch();






                if(gd.coins>50)//&&gd.pointerstate==1)
                {
                ///////////////////////////////////////////////////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////////
                /////////////////////////////Draw blue  flame///////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////////

                engine.beginBatch(GameData.Etex.Thero.ordinal());
                engine.setcolor(.5f,.8f,1f,.9f);
                drawhero(dt,gd.herox,gd.heroy-25,3,0,gd.heroangle+180);
                 engine.endBatch();
                }
                else {
                ///////////////////////////////////////////////////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////////
                /////////////////////////////Draw red flame///////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////////

                 engine.beginBatch(GameData.Etex.Thero.ordinal());
                engine.setcolor(1f,.8f,.8f,.9f);
                drawhero(dt,gd.herox,gd.heroy-25,3,0,gd.heroangle+180);
                 engine.endBatch();

                }

                engine.setcolor(1,1,1,1);










                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////Draw current over current fields//////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(gd.Gamestate==0)
                {
                 engine.beginBatch(GameData.Etex.Tcur.ordinal());
                int i=0;

                while(i<gd.maxenemey)
                { if(gd.enemytype[i]==3||gd.enemytype[i]==2)
                drawcurrent( dt, gd.enemyx[i],gd.enemyy[i]+10,.3*gd.enemylength[i]/140,.4*gd.enemylength[i]/140,0,0, gd.enemyangle[i]);
                else
                drawcurrent( dt, gd.enemyx[i]-800,gd.enemyy[i]-800,.2,.3,0,0, gd.enemyangle[i]+90);
                i++;
                }
                 engine.endBatch();
                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                ///////////////////////////////////////////////Draw score and coins//////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////




                engine.beginBatch( GameData.Etex.Tfont.ordinal());
                engine.textDraw( 10f,460f,2f,1f,1,"Score",1f,1f,.4f,1f," ");
                drawnumber( (int)gd.score,80,430,15);
                engine.textDraw( 10f,400f,2f,1f,1,"Life",1f,1f,.4f,1f," ");
                drawnumber( (int)gd.life,50,380,15);
                engine.textDraw( 200f,460f,2f,1f,1,"Battery",1f,1f,.3f,1f," ");
                drawnumber( gd.coins,(gd.score>999?260:280),430,15);
                engine.setcolor(.8f, .8f, .8f, .8f);
                engine.textDraw(290f, 400f, 2f, 1f, 0, "II", 1f, 1f, .3f, 1f, " ");
                engine.setcolor(1f, 1f, 1f, 1f);
                if (gd.pointerstate == 1 && gd.pointx >270 && gd.pointy < 405 && gd.pointx < 365 && gd.pointy > 377)
                {
                    gd.Gamestate = 3;
                }
                    engine.endBatch();
                ///////GamePlaying////////////////
                //////////////////////////
                r1.regiony+=dt*speed;
                r2.regiony+=dt*speed;
                r3.regiony+=dt*speed;
                ///////////////////////////////////////		  break;

                }
                guiDraw( dt);
                GameScreen.gl.glDisable(GameScreen.gl.GL_BLEND);
                }

    private static void drawcurrent(float p1, int p2, float p3, double p4, double p5, int p6, int p7, int p8)
    {
        drawcurrent(p1, p2, (int)p3, (float)p4, (float)p5, p6, p7, (float)p8);
    }

    private static void drawitems(float dt, float p1, float p2, int p3, int p4, int p5)
    {
        drawitems((float)dt, (int)p1, (int)p2, (int)p3, (int)p4, (float)p5); 
    }

    private static void drawitems(float dt, int p1, float p2, int p3, int p4, int p5)
    {
        drawitems((float) dt,(int) p1,(int) p2,(int) p3,(int) p4,(float) p5);
    }
    public  static float pow(float p1, int p2)
{
    return  p1*p1;
}
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 //////////////////////////////////////////////////Draw back ground ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 public  static  void drawbg(float dt,int x,int y,int index)
    {
	 if(batch==0){
		 Init();
		 batch=1;
		 }
	 if(index>11)index=11;
     int[] sprite = { (int)GameData.Ereg.bg3.ordinal(), (int)GameData.Ereg.bg4.ordinal(), (int)GameData.Ereg.bg1.ordinal(), (int)GameData.Ereg.bg2.ordinal(), (int)GameData.Ereg.bg4.ordinal(), (int)GameData.Ereg.bg5.ordinal(), (int)GameData.Ereg.bg1.ordinal(), (int)GameData.Ereg.bg5.ordinal(), (int)GameData.Ereg.bg4.ordinal(), (int)GameData.Ereg.bg3.ordinal(), (int)GameData.Ereg.bg1.ordinal(), (int)GameData.Ereg.bg2.ordinal(), (int)GameData.Ereg.bg5.ordinal(), (int)GameData.Ereg.bg5.ordinal() };
     int[] br ={(int)GameData.Ereg.brick1.ordinal(),(int)GameData.Ereg.brick2.ordinal(),(int)GameData.Ereg.brick3.ordinal(),(int)GameData.Ereg.brick.ordinal(),(int)GameData.Ereg.brick1.ordinal(),(int)GameData.Ereg.brick.ordinal(),(int)GameData.Ereg.brick.ordinal(),(int)GameData.Ereg.brick1.ordinal(),(int)GameData.Ereg.brick1.ordinal(),(int)GameData.Ereg.brick2.ordinal(),(int)GameData.Ereg.brick3.ordinal(),
    		  (int)GameData.Ereg.brick3.ordinal(),(int)GameData.Ereg.brick3.ordinal(),(int)GameData.Ereg.brick2.ordinal(),(int)GameData.Ereg.brick1.ordinal(),(int)GameData.Ereg.brick1.ordinal(),(int)GameData.Ereg.brick3.ordinal(),(int)GameData.Ereg.brick2.ordinal(),(int)GameData.Ereg.brick1.ordinal(),(int)GameData.Ereg.brick2.ordinal()};
     int[] door = { (int)GameData.Ereg.door.ordinal(), (int)GameData.Ereg.door1.ordinal(), (int)GameData.Ereg.door2.ordinal(), (int)GameData.Ereg.door3.ordinal() };
     int[] doorbl = { (int)GameData.Ereg.bridge.ordinal(), (int)GameData.Ereg.bridge1.ordinal(), (int)GameData.Ereg.bridge3.ordinal() };

     int[] sprite1 = { (int)GameData.Ereg.bg6.ordinal(), (int)GameData.Ereg.bg7.ordinal(), (int)GameData.Ereg.lift.ordinal(), (int)GameData.Ereg.barbedwire.ordinal(), (int)GameData.Ereg.platformbar.ordinal() };
     int gd1, gd2;//, spritewidth, spriteheight ;
	 gd1=y+480;
	 gd2=y-480;

	 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     ////////////////////////                 Draw the Base background                      /////////////////////////////////////
	 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


     ////////////enable transparency//////////////
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     ///////////////////////                 Draw the Over Art on background                 /////////////////////////////////////
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  //  engine.beginBatch(gd.Tover);
  //  engine.spriteDraw(x,y,gd.regionwidth[door[index/4]]*.7,gd.regionheight[door[index/4]]*.6,0,0,0,door[index/4]);
   // engine.endBatch();

     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      ///////////////////////                 Draw the Lift and its holder   barbed wire etc               //////////////////////////////////////
      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	  engine.beginBatch(GameData.Etex.Tbg2.ordinal());
     // engine.spriteDraw(x+15,gd.lift1y,gd.regionwidth[sprite1[2]]*.5,gd.regionheight[sprite1[2]]*.7,0,0,0,sprite1[2]);
     // engine.spriteDraw(x-15,gd.lift2y,gd.regionwidth[sprite1[2]]*.5,gd.regionheight[sprite1[2]]*.7,0,0,0,sprite1[2]);
     engine.spriteDraw(x, y + 155, gd.regionwidth[(int)GameData.Ereg.barbedwire.ordinal()] * 1.2, gd.regionheight[(int)GameData.Ereg.barbedwire.ordinal()] * .7, 0, 0, 0, (int)GameData.Ereg.barbedwire.ordinal());
     engine.spriteDraw(x, y - 155, gd.regionwidth[(int)GameData.Ereg.barbedwire.ordinal()] * 1.2, gd.regionheight[(int)GameData.Ereg.barbedwire.ordinal()] * .7, 0, 0, 0,  (int)GameData.Ereg.barbedwire.ordinal());
      engine.spriteDraw(x,y-20,gd.regionwidth[sprite1[4]],gd.regionheight[sprite1[4]]*2.5,0,0,0,sprite1[4]);
      engine.spriteDraw(x,y+gd.regionheight[sprite1[4]],gd.regionwidth[sprite1[4]],gd.regionheight[sprite1[4]]*2.5,0,0,0,sprite1[4]);
      //engine.spriteDraw(x,y-gd.regionheight[sprite1[4]]*2,gd.regionwidth[sprite1[4]],gd.regionheight[sprite1[4]]*2,0,0,0,sprite1[4]);
     // engine.spriteDraw(x,y+gd.regionheight[sprite1[4]]*2,gd.regionwidth[sprite1[4]],gd.regionheight[sprite1[4]]*2,0,0,0,sprite1[4]);
      engine.endBatch();

      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      ///////////////////////                 Draw the Over Art on lift foreground                 /////////////////////////////////////
      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


     //engine.beginBatch(gd.Tover);
     //engine.spriteDraw(x,y-10-gd.regionheight[sprite1[4]]/2,gd.regionwidth[door[index/4]]*.6,gd.regionheight[sprite1[index/4]]*.3,0,0,0,doorbl[index%4]);
     //engine.spriteDraw(x,y+10+gd.regionheight[sprite1[4]]/2,gd.regionwidth[door[index/4]]*.6,gd.regionheight[sprite1[index/4]]*.3,0,0,0,doorbl[index%3]);
    // engine.endBatch();


     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     ///////////////////////                 Draw side  wall using bricks                  /////////////////////////////////////
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     engine.beginBatch(GameData.Etex.Tbg.ordinal());
      int k = gd.regionheight[(int)GameData.Ereg.platformbar.ordinal()] / gd.regionheight[(int)GameData.Ereg.brick.ordinal()] + 1;
     int i=0;
     int w = gd.regionheight[(int)GameData.Ereg.brick.ordinal()];
     while(i<4*k)
     {   drawwall(dt,x-15,y-(w*k/2)+i*gd.regionheight[br[index+i%10]]*.59f,0,br[index+i%10],0);
         drawwall(dt,x+15,y-(w*k/2)+i*gd.regionheight[br[index+i%10]]*.59f,1,br[index+i%10],0);
       i++;
     }  i=0;
        while(i<4*k)
          {  drawwall(dt,x-15,y-90-(w*k/2)+i*gd.regionheight[br[index+i%10]]*.59f,2,br[index+(40-i)%10],0);
         	 drawwall(dt,x+15,y-90-(w*k/2)+i*gd.regionheight[br[index+i%10]]*.59f,3,br[index+(40-i)%10],0);
            i++;
          }i=0;


          engine.endBatch();


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

 private static void drawwall(float dt, int p1, float p2, int p3, int p4, int p5)
 {
   drawwall(dt, p1, (int)p2, p3, p4, (float)p5); 
 }

 //////////////////////////////////////////////////Draw wall brick by brick////////////////////////////////////////////////////////////////////////////////////////////////////////////
 public static void drawwall(float dt, int x, int y, int _object, int sprite, float angle)
 {
     int spritewidth = gd.regionwidth[sprite];
     int spriteheight = gd.regionheight[sprite];
     ////////////index as per there draw order first to last ///////////////////
     switch (_object)
     {
         case 0: engine.spriteDraw(x - 160 + spritewidth / 2 + .46 * spritewidth, y - 210 + spriteheight * .59, spritewidth, spriteheight, 0, 0, 0, sprite); break;
         case 1: engine.spriteDraw(x + 160 - spritewidth / 2 - .46 * spritewidth, y - 210 + spriteheight * .59, -1 * spritewidth, spriteheight, 0, 0, 0, sprite); break;
         case 2: engine.spriteDraw(x - 160 + spritewidth / 2, y - 210 + spriteheight * .72, spritewidth, spriteheight, 0, 0, 0, sprite); break;
         default: engine.spriteDraw(x + 160 - spritewidth / 2, y - 210 + spriteheight * .72, -1 * spritewidth, spriteheight, 0, 0, 0, sprite); break;

     }
 }
  
//////////////////////////////////////////////////draw Enemy,pickup,ghost they don't have privilege of closing and opening batcher///////////////////////////////////////////


 public static void drawitems(float dt,int x,int y,int grp,int index,float angle)
     {
         int[] cursig = { (int)GameData.Ereg.charg1.ordinal(), (int)GameData.Ereg.charg2.ordinal(), (int)GameData.Ereg.charg3.ordinal(), (int)GameData.Ereg.charg4.ordinal() };
         int[] ghost ={ (int)GameData.Ereg.ghost_1.ordinal(), (int)GameData.Ereg.ghost_2.ordinal(), (int)GameData.Ereg.ghost_3.ordinal(), (int)GameData.Ereg.ghost_4.ordinal(), (int)GameData.Ereg.ghost_5.ordinal(), (int)GameData.Ereg.ghost_6.ordinal(), (int)GameData.Ereg.ghost_7.ordinal(), (int)GameData.Ereg.ghost_8.ordinal(),(int)GameData.Ereg.ghost_9.ordinal(), (int)GameData.Ereg.ghost_10.ordinal(),
	    		      (int)GameData.Ereg.ghost_11.ordinal(),(int)GameData.Ereg.ghost_12.ordinal(),(int)GameData.Ereg. ghost_13.ordinal(), (int)GameData.Ereg.ghost_14.ordinal(),(int)GameData.Ereg.ghost_15.ordinal(), (int)GameData.Ereg.ghost_16.ordinal(), (int)GameData.Ereg.ghost_17.ordinal(), (int)GameData.Ereg.ghost_18.ordinal(), (int)GameData.Ereg.ghost_19.ordinal(), (int)GameData.Ereg.ghost_20.ordinal(),
	    		      (int)GameData.Ereg.ghost_21.ordinal(), (int)GameData.Ereg.ghost_22.ordinal(), (int)GameData.Ereg.ghost_23.ordinal(), (int)GameData.Ereg.ghost_24.ordinal(), (int)GameData.Ereg.ghost_25.ordinal()};

		 float spritewidth=gd.regionwidth[ (int)GameData.Ereg.emf.ordinal()]*.4f;
         float spriteheight = gd.regionheight[(int)GameData.Ereg.emf.ordinal()] * .4f;


		 int cin=((int)(curtime*5))%4;
		 curtime+=dt/2;
		 if(curtime>50000000)
		 {curtime=0;
		 }
		 switch (grp)
         {
             case 0:
                engine.spriteDraw(x - 110, y, spritewidth, spriteheight, 0f, 0f, 0f, (int)GameData.Ereg.emf.ordinal());
		 	    engine.spriteDraw(x-108,y-10,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+1)%4]);
		 	    engine.spriteDraw(x-108,y-5,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+2)%4]);
		 	    engine.spriteDraw(x-108,y+5,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+3)%4]);
		 	    engine.spriteDraw(x-108,y+10,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+4)%4]);

		 	    break;
             case 1: engine.spriteDraw(x + 110, y, -1 * spritewidth, spriteheight, 0, 0, 0f, (int)GameData.Ereg.emf.ordinal());
                   		    	engine.spriteDraw(x+112,y-10,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+1)%4]);
		    			 	    engine.spriteDraw(x+112,y-5,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+2)%4]);
		    			 	    engine.spriteDraw(x+112,y+5,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+3)%4]);
		    			 	    engine.spriteDraw(x+112,y+10,gd.regionwidth[cursig[(cin+1)%4]]*.5,gd.regionheight[cursig[(cin+1)%4]]*.3,0,0,0,cursig[(cin+4)%4]);

            	      break;
             case 2: engine.spriteDraw(x, y, gd.regionwidth[(int)GameData.Ereg.pmcharge.ordinal()], gd.regionheight[(int)GameData.Ereg.pmcharge.ordinal()], 0, 0, 0f, (int)GameData.Ereg.pmcharge.ordinal()); break;
		    	case 3:engine.spriteDraw(x,y,gd.regionwidth[ghost[index]]*.5,gd.regionheight[ghost[index]]*.5,0,0,0,ghost[index]);break;
		    	case 4:engine.spriteDraw(x,y,-1*gd.regionwidth[ghost[index]]*.5,gd.regionheight[ghost[index]]*.5,0,0,0,ghost[index]);break;
                case 5: engine.spriteDraw(x, y, gd.regionwidth[(int)GameData.Ereg.pickupbattery.ordinal()] * .25, gd.regionheight[(int)GameData.Ereg.pickupbattery.ordinal()] * .25, 0, 0, 0, (int)GameData.Ereg.pickupbattery.ordinal());
		    	  if((cin+1)%4!=0)
                      engine.spriteDraw(x + gd.regionwidth[(int)GameData.Ereg.pickupbattery.ordinal()] * .125f - gd.regionwidth[(int)GameData.Ereg.batterybar.ordinal()] * .125f, y, gd.regionwidth[(int)GameData.Ereg.batterybar.ordinal()] * .25f, gd.regionheight[(int)GameData.Ereg.batterybar.ordinal()] * .25f, 0, 0, 0f, (int)GameData.Ereg.batterybar.ordinal());
		    	  else
                      engine.spriteDraw(x + gd.regionwidth[(int)GameData.Ereg.pickupbattery.ordinal()] * .125f - gd.regionwidth[(int)GameData.Ereg.batterybar.ordinal()] * .125f, y, gd.regionwidth[(int)GameData.Ereg.batterybar.ordinal()] * .25f, gd.regionheight[(int)GameData.Ereg.batterybar.ordinal()] * .25f, 180, 0, 0f, (int)GameData.Ereg.batterybar.ordinal());
		    	break;


		    }


     }

static int herotime=0;
static void drawhero(float dt,int x,int y,int grp,int index,float angle)
{
    int[] flame = { (int)GameData.Ereg.flame1.ordinal(), (int)GameData.Ereg.flame2.ordinal(), (int)GameData.Ereg.flame3.ordinal(), (int)GameData.Ereg.flame4.ordinal(), (int)GameData.Ereg.flame5.ordinal(), (int)GameData.Ereg.flame6.ordinal() };
             int cin=((int)(herotime))%6;
    		 herotime+=(int)(dt*100);
     		 if(herotime>5000000)
    		 {herotime=0;
    		 }
    		switch(grp)
            {
                case 0: engine.spriteDraw(x, y, gd.regionwidth[(int)GameData.Ereg.ph0.ordinal()] * .6f, gd.regionheight[(int)GameData.Ereg.ph0.ordinal()] * .6f, angle, 0, 10, (int)GameData.Ereg.ph2.ordinal());
                  break;
    		 default:
    			 engine.spriteDraw(x,y-14,gd.regionwidth[flame[(cin+1)%4]]*.6f,gd.regionheight[flame[(cin+1)%4]]*.6f,angle,0,0,flame[(cin)%6]);

    			 break;
    		 }


     }
//////////////////////////////////////////////cur draw/////////////////////////////////////////////////////////////
  static float curcurtime=0;
  static void drawcurrent(float dt,int x,int y,float sx,float sy,int _object,int sprite,float angle)
     {
	   int cin=((int)(curcurtime*5))%9;
	  curcurtime+=dt/1.25f;if(curcurtime>50000000){curcurtime=0;}

      int[] a = { (int)GameData.Ereg.boly_tesla1.ordinal(), (int)GameData.Ereg.boly_tesla2.ordinal(), (int)GameData.Ereg.boly_tesla3.ordinal(), (int)GameData.Ereg.boly_tesla4.ordinal(), (int)GameData.Ereg.boly_tesla5.ordinal(), (int)GameData.Ereg.boly_tesla6.ordinal(), (int)GameData.Ereg.boly_tesla7.ordinal(), (int)GameData.Ereg.boly_tesla8.ordinal(), (int)GameData.Ereg.boly_tesla9.ordinal(), (int)GameData.Ereg.boly_tesla10.ordinal() };
	     float spritewidth=gd.regionwidth[a[_object]];
	     float spriteheight=gd.regionheight[a[_object]];spritewidth*=sx;spriteheight*=sy;
        ////////////index as per there draw order first to last ///////////////////

    	 engine.spriteDraw(x,y,spritewidth,spriteheight,angle,0,0,a[cin]);

     }
//////////////////////////////////////strike current /////////////////////////////////////////////////
  static void drawstrike(float dt,int x,int y,int _object,int sprite,float angle)
     {
	     int []a={(int)GameData.Ereg.bolt_strike2.ordinal(),(int)GameData.Ereg.bolt_strike3.ordinal(),(int)GameData.Ereg.bolt_strike4.ordinal(),(int)GameData.Ereg.bolt_strike5.ordinal(),(int)GameData.Ereg.bolt_strike6.ordinal(),(int)GameData.Ereg.bolt_strike7.ordinal(),(int)GameData.Ereg.bolt_strike9.ordinal(),(int)GameData.Ereg.bolt_strike10.ordinal()};
	     float spritewidth=gd.regionwidth[a[_object]];
	     float spriteheight=gd.regionheight[a[_object]];
	     spritewidth*=.1f;
	     spriteheight*=.2f;

        ////////////index as per there draw order first to last ///////////////////
	     int cin=((int)(curcurtime*5))%6;
	   	  curcurtime+=dt/1.25f;if(curcurtime>50000000){curcurtime=0;}
         engine.spriteDraw(x,y,spritewidth,spriteheight,angle,0,0,a[cin]);
     }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////abo DRAW///////////////////////////////////////////////////////////////////////////////////////////// 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




  static int butonpush = -1;

  static void guiDraw(float dt)
  {


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      ////////////////////////////////////////////Draw Gui  Screen Based on Game State and pause game and hit detection if game///////////////////////////////////////
      /////////////////////////////////////////////////////////state is not playing////////////////////////////////////////////////////////////////////////////////////
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


      int[] screena = { 70, 150, 240, 330, 800 };

      
      int ti = 0;
      switch (gd.Gamestate)
      {


          ////////////Game Paused///////////
          case 1:

          case 2:
          /////////////Game Resumed/////////
          case 3:
              if (vm.GetState() == 0)
                  vm.SetState(3);
             
          //else
          //vm.screenstate=0;
          /* no break */

          ////////////////Game new not resumed/////////
          case 4:
              //vm.SetState(0);
              //gd.Gamestate = 0;
              switch (vm.GetState())
              {
                  case 0:
                      engine.setcolor(.2f, .2f, .7f, .4f);
                      engine.beginBatch(GameData.Etex.Tbg2.ordinal());
                      engine.spriteDraw(160, 200, 300, 460, 0, 0, 0f, (int)GameData.Ereg.bg6.ordinal());
                      if(gd.pointy>0&&gd.pointy<400)
                      engine.spriteDraw(160, screena[(int)gd.pointy / 90], 300, 50, 0, 0, 0f,(int)GameData.Ereg.bg7.ordinal());
                      
                      engine.endBatch();
                      
                      ti = 0;
                      while (ti < 5)
                      {
                          if ((gd.pointerstate == 1) && gd.pointx > 80 && gd.pointx < 280 && gd.pointy > screena[4 - ti] - 25 && gd.pointy < screena[4 - ti] + 25)
                          {
                              butonpush = ti - 1;
                          } ti++;
                      } 
                   //   30,130
                      if (gd.pointerstate == 0 && gd.pointx < 100 && gd.pointy < 220 && gd.pointx > 0 && gd.pointy > 55)
                      {
                          butonpush = 6;
                      }
                      
                      if (gd.pointerstate == 0 && gd.pointx > 220 && gd.pointy < 220 && gd.pointx < 320 && gd.pointy > 55)
                      {
                          butonpush = 7; 
                      }
                      
                     switch (butonpush)
                      {
                          case 0: gd.Gamestate = 0; gd.first = true; gd.life = 5; gd.hit = false; gd.score = 0; gd.coins = 0; engine.playsound(2);
                              //gd.life = 123;
                              butonpush = -1; Init();
                              break;
                          case 1: vm.SetState(2); engine.playsound(2); butonpush = -1;
                              break;
                          case 2: vm.SetState(1); engine.playsound(2); butonpush = -1;
                              break;
                          case 3: vm.SetState(8); engine.playsound(2); butonpush = -1;
                              break;
                          case 4: vm.SetState(8); engine.playsound(2); butonpush = -1; quit();//engine.playsound(1);
                              break;

                          /////////////////////////////////disable enable music //////////////////////
                          case 6:
                              
                                     
                                   if (gd.musicstate) 
                                	   gd.musicstate = false;
                                   else 
                                	  gd.musicstate = true;
                                 
                                  engine.music((gd.musicstate ? 1 : 0), "");//break;
                                  engine.playsound(2);
                                  engine.write(-40);
                                  butonpush = -1;
                                  break;
                          /////////////////////////////////disable enable sound //////////////////////
                          case 7:
                              
                                 if (gd.soundstate) gd.soundstate = false; else gd.soundstate = true;
                                  engine.music((gd.soundstate ? 3 : 2), "");// break;
                                  engine.playsound(2);
                                  engine.write(-40);
                                  butonpush = -1;
                                   break;
                              
                      }

                      engine.endBatch();
                      engine.setcolor(1, 1, 1, 1);
                      engine.beginBatch(GameData.Etex.Tgui.ordinal());
                      engine.spriteDraw(160, 240, 300, 460, 0, 0, 0f, (int)GameData.Ereg.start.ordinal());
                      engine.endBatch();
                      engine.beginBatch(GameData.Etex.Tsky.ordinal());
                      if (gd.musicstate == false)
                          engine.spriteDraw(30, 130, 70, 70, 0, 0, 0f, (int)GameData.Ereg.musicoff.ordinal());
                      else
                          engine.spriteDraw(30, 130, 70, 70, 0, 0, 0f, (int)GameData.Ereg.music.ordinal());
                      
                      if (gd.soundstate == false)
                          engine.spriteDraw(290, 130, 70, 70, 0, 0, 0f, (int)GameData.Ereg.soundoff.ordinal());
                      else
                          engine.spriteDraw(290, 130, 70, 70, 0, 0, 0f, (int)GameData.Ereg.sound.ordinal());
                      engine.endBatch();

                      engine.spriteDraw(300,20, 30, 60, 90, 0, 0f, (int)GameData.Ereg.back.ordinal());
                      engine.endBatch();
                      
                      if (gd.pointerstate == 1 && gd.pointx < 330 && gd.pointy <50  && gd.pointx > 290 && gd.pointy >0)
                      {
                        ((Activity) TerVel.contex).finish();
                      }

                      //if (gd.pointerstate == 0)
                       //   __android_log_print(1, "tag", "this is pointer 2 y=%d and x =%d", gd.pointy, gd.pointx);

                      break;


                  ////////////////////////about////////////////////

                  case 1:

                      engine.beginBatch(GameData.Etex.Tgui.ordinal());
                      engine.spriteDraw(160, 225, 300, 460, 0, 0, 0f, (int)GameData.Ereg.about.ordinal());
                      engine.endBatch();
                      engine.beginBatch(GameData.Etex.Tsky.ordinal());
                      engine.spriteDraw(30, 50, 70, 70, 0, 0, 0f, (int)GameData.Ereg.back.ordinal());
                      engine.endBatch();
                      if (gd.pointerstate == 1 && gd.pointx < 90 && gd.pointy < 85 && gd.pointx > 0 && gd.pointy > 0)
                      {
                          vm.SetState(0); engine.playsound(2);
                      }
                      break;

                  ////////////////////////help////////////////////

                  case 2:
                      engine.beginBatch(GameData.Etex.Tgui.ordinal());
                      engine.spriteDraw(160, 225, 300, 460, 0, 0, 0f, (int)GameData.Ereg.help.ordinal());
                      engine.endBatch();
                      engine.beginBatch(GameData.Etex.Tsky.ordinal());
                      engine.spriteDraw(30, 50, 70, 70, 0, 0, 0f, (int)GameData.Ereg.back.ordinal());
                      engine.endBatch();
                      if (gd.pointerstate == 1 && gd.pointx < 90 && gd.pointy < 85 && gd.pointx > 0 && gd.pointy > 0)
                      {
                          vm.SetState(0); engine.playsound(2);
                      }
                      break;

                  ////////////////////////pause////////////////////
                  case 3:
                      engine.beginBatch(GameData.Etex.Tgui.ordinal());
                      engine.spriteDraw(160, 240, 300, 460, 0, 0, 0f, (int)GameData.Ereg.resume.ordinal());
                      engine.endBatch();
                      engine.beginBatch(GameData.Etex.Tsky.ordinal());
                      engine.spriteDraw(30, 50, 70, 70, 0, 0, 0f, (int)GameData.Ereg.back.ordinal());
                      engine.endBatch();
                      gd.Gamestate = 4;
                      if (gd.pointerstate == 1 && gd.pointx < 90 && gd.pointy < 100 && gd.pointx > 0 && gd.pointy > 0)
                      {
                          vm.SetState(0);
                          //engine.playsound(2);
                      }
                      //////////////////resume//////////////

                      else if (gd.pointerstate == 1 && gd.pointx > 90 && gd.pointy < 290 && gd.pointx < 270 && gd.pointy > 190)
                      {
                          gd.Gamestate = 0;
                          
                          
                      }
                      break;


                  ///////////////////////Game Over////////////////////


                  case 4:

                      engine.beginBatch(GameData.Etex.Tgameover.ordinal());
                      engine.spriteDraw(160, 360, 210, 100, 0, 0, 0f,(int)GameData.Ereg.gameover.ordinal());
                      engine.endBatch();

                      engine.beginBatch(GameData.Etex.Tfont.ordinal());
                      engine.textDraw(80, 220, 1.2f, 1.2f, 1, "YourScore", 1, 1, .5f, 1, " ");
                      engine.scoreDraw(80, 223, 200, 200, 0, 0, 0, 1);
                      //engine.beginBatch(gd.Tfont);
                      //engine.textDraw(10,300,1.4,1.5,1," Game Over ",2,2,.7,1," ");
                      engine.endBatch();


                      engine.beginBatch(GameData.Etex.Tsky.ordinal());
                      engine.spriteDraw(30, 50, 70, 70, 0, 0, 0f, (int)GameData.Ereg.back.ordinal());
                      engine.endBatch();
                      if (gd.pointerstate == 1 && gd.pointx < 90 && gd.pointy < 120 && gd.pointx > 0 && gd.pointy > 0)
                      {
                          vm.SetState(0); gd.Gamestate = 4;
                          engine.playsound(2);
                      }
                      break;
                  ///////////////////////////////////////////High Score////////////////////////////////////////////////////////////
                  case 8:
                      engine.beginBatch(GameData.Etex.Tfont.ordinal());
                      engine.textDraw(80, 420, 1.2f, 1.2f, 1, "HighScores", 1, 1, .5f, 1, " ");
                      engine.scoreDraw(80, 340, 200, 200, 0, 0, 0, 1);
                      engine.endBatch();
                      engine.beginBatch(GameData.Etex.Tsky.ordinal());
                      engine.spriteDraw(30, 50, 70, 70, 0, 0, 0f, (int)GameData.Ereg.back.ordinal());
                      engine.endBatch();
                      if (gd.pointerstate == 1 && gd.pointx < 90 && gd.pointy < 120 && gd.pointx > 0 && gd.pointy > 0)
                      {
                          vm.SetState(0); gd.Gamestate = 4;
                          engine.playsound(2);
                      }
                      break;




              } break;

      }




  }























////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////









        public static void drawnumber( int n, int x,int y,int stepx)
        {   if(n==0){engine.textDraw( x,y,2f,1f,1,"0",1f,1f,.4f,1f," "); return;}
        while(n > 0)
        {switch(n%10)
        {case 0:engine.textDraw( x,y,2f,1f,1,"0",1f,1f,.4f,1f," ");break;
        case 1:engine.textDraw( x,y,2f,1f,1,"1",1f,1f,.4f,1f," ");break;
        case 2:engine.textDraw( x,y,2f,1f,1,"2",1f,1f,.4f,1f," ");break;
        case 3:engine.textDraw( x,y,2f,1f,1,"3",1f,1f,.4f,1f," ");break;
        case 4:engine.textDraw( x,y,2f,1f,1,"4",1f,1f,.4f,1f," ");break;
        case 5:engine.textDraw( x,y,2f,1f,1,"5",1f,1f,.4f,1f," ");break;
        case 6:engine.textDraw( x,y,2f,1f,1,"6",1f,1f,.4f,1f," ");break;
        case 7:engine.textDraw( x,y,2f,1f,1,"7",1f,1f,.4f,1f," ");break;
        case 8:engine.textDraw( x,y,2f,1f,1,"8",1f,1f,.4f,1f," ");break;
        case 9:engine.textDraw( x,y,2f,1f,1,"9",1f,1f,.4f,1f," ");break;
        }
        n = n / 10;
        x-=stepx;
        }

        }

        public static void State(int state)
        {  if(gd!=null)
        	gd.Gamestate=state;
        }

        static void quit()
        {
        engine.performtask( -34);

        }



        public static void postaudiodata(boolean a, boolean b)
        {
        gd.musicstate = a;
        gd.soundstate = b;
        }
 

        //////////////////////////////////////////////////Initialize the game data call it when ever you need to reset game data it took a while  //////////////////////////////////////////
        public static void Init()
        {
        if(r1==null)
        	r1=new RegionData();
        if(r2==null)
        r2=new RegionData();
        if(r3==null)
        r3=new RegionData();
        if(gd==null)
        gd=new GameData(false,false);
        r1.regiony=200;
        r2.regiony=1200;
        r3.regiony=2200;
        r1.regionx=160;
        r2.regionx=160;
        r3.regionx=160;
        gd.coins=0;
        gd.texturnum=engine.assetsize(1);
        gd.texturreigonnum=engine.assetsize(2);
        gd.soundnum=engine.assetsize(3);
        gd.coinarrayx= new int[gd.maxcoins];
        gd.coinarrayy= new int[gd.maxcoins];
        gd.enemyx =new int[gd.maxenemey];
        gd.enemyy= new int[gd.maxenemey];
        gd.enemytype=new int[gd.maxenemey];
        gd.enemylength=new int[gd.maxenemey];
        gd.enemyangle=new int[gd.maxenemey];
        gd.regionheight=new int[gd.texturreigonnum];
        gd.regionwidth=new int[gd.texturreigonnum];
        gd.associatedtextures=new int[gd.texturreigonnum];
        gd.screenheight=engine.assetsize(4);
        gd.screenwidth=engine.assetsize(5);
        gd.musicstate=inductionlabs.tervelhd.Settings.musicEnabled;
        gd.soundstate=inductionlabs.tervelhd.Settings.soundEnabled;
        gd.bgx=160;
        gd.bgy=210;
        int i=0;
        while(i<gd.texturreigonnum)
        { gd.regionwidth[i]= engine.assetdatasize(1,i);
        gd.regionheight[i]=engine.assetdatasize(2,i);
        gd.associatedtextures[i]=engine.assetdatasize(3,i);
        i++;
        }
        i=0;
        gd.herox=160;
        gd.heroy=50;
        int k=50;
        gd.herominx=0+k;
        gd.heromaxx=360-k-34;
        gd.senstivity=(int)(Math.min(6+(float)(gd.score/500),16));
        gd.heroangle=0;
        genregion(0);
        
        }
        ///////////////////////////////////////////////////////////////Genrate the region data //////////////////////////////////////////

        public static void genregion(int i)
        {
        	
        int k=0;
        if(i==2||i==0)
        while(k<gd.maxcoins)
        {
        gd.coinarrayx[k]= (k%7)*40+30;
        gd.coinarrayy[k]=(k/7)*45;
        int a= rand()%10;
        if(gd.coinarrayx[k]>310||gd.coinarrayx[k]<50||a<7)
        { gd.coinarrayx[k]-=600;
        }
        //gd.coinarrayy[k]=(k/8-2)*90;
        k++;

        }
        k=0;
        if(i==2)
         return; 	

        while(k<gd.maxenemey)
        {
        if(gd.enemyy[k]<-200&&i!=0)
        {
        gd.enemyy[k]+=gd.heroy+gd.maxenemey*210;
        
        gd.enemyx[k]=(k*134)%210;
        gd.enemyx[k]+=55;
        int a= (rand()+rand()+rand()+rand())%100;
        if(gd.enemyx[k]>310||gd.enemyx[k]<50||a<15)
        {   gd.enemyx[k]=2500;
        }
        int [] ja={45,-60,90,-45,30,45,-64,30,-23};
        gd.enemyangle[k]=ja[rand()%9];
        gd.enemytype[k]=rand()%4;
        /////////////////////////////////////rechance to get type 2 enemey//////////////////////////////////////////
        if(gd.enemytype[k]==0||gd.enemytype[k]==1)
        gd.enemytype[k]=rand()%4;
        int []b={85,60,90,100,80,118};
        gd.enemylength[k]=b[rand()%6];
        }
        if (i==0)
        {
        	gd.enemyx[k]=(k*134)%210;
            gd.enemyx[k]+=55;
            int a= (rand()+rand()+rand()+rand())%100;
            if(gd.enemyx[k]>310||gd.enemyx[k]<50||a<15)
            {   gd.enemyx[k]=2500;
            }
            int [] ja={45,-60,90,-45,30,45,-64,30,-23};
            gd.enemyangle[k]=ja[rand()%9];
            gd.enemytype[k]=rand()%4;
            /////////////////////////////////////rechance to get type 2 enemey//////////////////////////////////////////
            if(gd.enemytype[k]==0||gd.enemytype[k]==1)
            gd.enemytype[k]=rand()%4;
            int []b={85,60,90,100,80,118};
            gd.enemylength[k]=b[rand()%6];
            //if(gd.enemytype[k]==0||gd.enemytype[k]==1)
        	//gd.enemyy[k]=(k)*100;
        	//else
        	 gd.enemyy[k]=(k)*210;
        	   
        	
        }
       k++;
       }




        }

        private static int rand()
        {
        return Assets.rand.nextInt(31065);
        }

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   
    
    
    
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////input////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        static int bp=0;
    
        public static void Update(float ax,float ay,float az)
        {gd.cur_ax=ax;
        gd.cur_ay=ay;
        gd.cur_az=az;
        if(gd.herox>=gd.herominx&&gd.herox<=gd.heromaxx&&gd.Gamestate==0)
        {gd.herox=(int)(gd.herox+ax*gd.senstivity);
        }
        if(gd.herox<gd.herominx)
        gd.herox=gd.herominx;
        if(gd.herox>gd.heromaxx)
        gd.herox=gd.heromaxx;

        }
        public static void Update()
        {

        }
        public static boolean Update(int key,int state)
        {
        	if(Assets.loaderp!=100)
        return true;
        gd.key=key;
        gd.keystate=state;

        if(gd.key==50 &&gd.keystate==1)
        {gd.pointx=-201;
         gd.pointy=-201;
        if(gd.Gamestate==0)
         gd.Gamestate=3;
         else  if(vm.GetState()!=0&&gd.Gamestate!=3)
         {vm.SetState(0);
         gd.Gamestate=4;
          bp=0;
         }
        if(gd.Gamestate!=4)
        {
        bp++;
        }
        if(gd.Gamestate==4&&vm.GetState()==0)
        {
        if(bp==2)
        {bp=0;
        //Main.context.Exit();
        ((Activity) TerVel.contex).finish();
        }
        else
        {bp++;
        if(Game.gd.Gamestate==4)
         Toast.makeText(TerVel.contex, "Press Back Once more to exit ", Toast.LENGTH_SHORT).show();
        
        }
        }
        return true;
       }



        /////////////////////////////////////hero on emulator via left and right/////////////////
        if(gd.herox>=gd.herominx&&gd.herox<=gd.heromaxx&&gd.Gamestate==0)
        {
        if(key==20&&state==1)
        gd.herox=gd.herox-gd.senstivity;
        else if(key==21 &&state==1)
        gd.herox=gd.herox+gd.senstivity;


        if(gd.herox<gd.herominx)
        gd.herox=gd.herominx;
        if(gd.herox>gd.heromaxx)
        gd.herox=gd.heromaxx;

        return false;
        }
        return false;
        }
        public static void Update(float tx,float ty,int state,int index)
        {
        if(index==0)
        {gd.pointx1=tx;
        gd.pointy1=ty;
        gd.pointerstate=state;
        }

        gd.pointx = gd.pointx1;
        gd.pointy =gd.pointy1;

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////input////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
        
 }