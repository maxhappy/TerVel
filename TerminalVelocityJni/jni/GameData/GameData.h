/*
 * GameData.h
 *
 *  Created on: 26-Oct-2012
 *      Author: Praveen Ojha
 */

#ifndef GAMEDATA_H_
#define GAMEDATA_H_
#include <jni.h>
class GameData
{
public:

	bool musicstate,soundstate,first;
	float cur_ax;
	float cur_ay;
	float cur_az;
	float pointx;
	float pointy;
	float pointx1;
	float pointy1;
	float heroangle;
	int heromaxx,herominx,senstivity;
	int life ;
	int minreg;
	int maxreg;
	int herox;
	int heroy;
	int lift1y;
	int lift2y;
    float score;
    int coins;

    int maxcoins;
    int maxenemey;
	float pointerstate;
	float pointerstate1;
	int key;
	int keystate;
    int current_background;
    float  bgx;float bgy;
    int liftpos;
    int Gamestate;

    bool hit;

    int *coinarrayy;
    int *coinarrayx;
    int *enemyx;
    int *enemyy;
    int *enemytype;
    int *enemylength;
    int *enemyangle;


  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////copied thi sdata using watch after asset loader so this need one time to done per game/////////////////////////
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    enum Ereigons
	     {bg,bg1, bg2, bg3, bg4, bg5, crane, brick, brick1, brick2, brick3, bg6, bg7, platformbar, barbedwire, lift, boly_tesla10, boly_tesla9,
	      boly_tesla1,boly_tesla8, boly_tesla3, boly_tesla5, boly_tesla2, boly_tesla7, boly_tesla6, boly_tesla4, rocket, hero, heroshadow, wingp2,
	      wingp1,flame1, flame2, flame3, flame4, flame5, flame6, wingp3, ghost_0, ghost_1, ghost_2, ghost_3, ghost_4, ghost_5, ghost_6, ghost_7, ghost_8,
	      ghost_9, ghost_10, ghost_11, ghost_12, ghost_13, ghost_14, ghost_15, ghost_16, ghost_17, ghost_18, ghost_19, ghost_20, ghost_21, ghost_22, ghost_23,
	      ghost_24, ghost_25, oil2, emf, light, regis, ph0, ph1, ph2, regmold, pickupbattery, energycell, oil, pmcharge, price, charg2, charg4, charg1,
	      batterybar,charg3, door1, door2, door3, door, door4, platform, platform1, platform2, platform3, bridge, bridge1, bridge3, doorblock, bolt_strike2,
	      bolt_strike6, bolt_strike5, bolt_strike7, bolt_strike3, bolt_strike4, bolt_strike9, bolt_strike10, font1, font2,
	      stf, sound, soundoff, share1, share3, back, music, musicoff
	      ,start,about,resume,help,gameover  };
       enum Esound
	   {Scoin};
       enum Etextures
		{Tbg, Tbg2,Tcur,Thero,Titems,Tover,Tstrike,Tfont,Tsky,Tgui,Tgameover};

 /////////////////////////////////////////////////////////////////////////////////////////////////////////
 /////////////////////////////////////////Always update this data by appling a  watch/////////////////////
 ///////////////////////////////////  Assets.texturenames,Assets.textures and on Assets.soundname/////////
 /////////////////////////////////////////////////////////////////////////////////////////////////////////

	     int screenwidth,screenheight;
	       int curtex;
		   int texturnum;
		   int texturreigonnum;
		   int soundnum;
		   int * associatedtextures;
		   jstring * sounds;
		   int * regionwidth;
		   int * regionheight;
		   enum { };
	//viewManager vm;
	GameData(bool,bool);
	virtual ~GameData();
};


class RegionData
{
public:
	float regionx;
	float regiony;
	int regionspeed;
	int coinsp1;
	int coinsp2;
	int coinsp3;
	int lift1y;
	int lift2y;
	int lift1speed;
	int lift2speed;
    int regionversion;

    int enemyx;
    int enemyy;
    int enemylength;
    int enemytype;
    float enemyangle;



       int enemy1x;
        int enemy1y;
        int enemy1length;
        int enemy1type;
        float enemy1angle;

           int enemy2x;
            int enemy2y;
            int enemy2length;
            int enemy2type;
            float enemy2angle;

            int enemy3x;
                int enemy3y;
                int enemy3length;
                int enemy3type;
                float enemy3angle;







    RegionData();
    virtual ~RegionData();



};

#endif /* GAMEDATA_H_ */
