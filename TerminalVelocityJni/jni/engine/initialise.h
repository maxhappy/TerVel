/*
 * initialise.h
 *
 *  Created on: 31-Oct-2012
 *      Author: Praveen Ojha
 */

#ifndef INITIALISE_H_
#define INITIALISE_H_




 //////////////////////////////////////////////////Initialize the game data call it when ever you need to reset game data it took a while  //////////////////////////////////////////
 void Game::Init(JNIEnv *env)
  {   r1.regiony=200;
      r2.regiony=1200;
      r3.regiony=2200;
      r1.regionx=160;
      r2.regionx=160;
      r3.regionx=160;
      gd.coins=0;
	  gd.texturnum=engine::assetsize(env,1);
	  gd.texturreigonnum=engine::assetsize(env,2);
	  gd.soundnum=engine::assetsize(env,3);
	  gd.coinarrayx= new int[gd.maxcoins];
	  gd.coinarrayy= new int[gd.maxcoins];
	  gd.enemyx =new int[gd.maxenemey];
	  gd.enemyy= new int[gd.maxenemey];
	  gd.enemytype=new int[gd.maxenemey];
	  gd.enemylength=new int[gd.maxenemey];
	  gd.enemyangle=new int[gd.maxenemey];
	  Game::gd.regionheight=new int[Game::gd.texturreigonnum];
	  Game::gd.regionwidth=new int[Game::gd.texturreigonnum];
	  Game::gd.associatedtextures=new int[Game::gd.texturreigonnum];
	  Game::gd.screenheight=engine::assetsize(env,4);
	  Game::gd.screenwidth=engine::assetsize(env,5);
      Game::gd.bgx=160;
      Game::gd.bgy=210;
      int i=0;
	  while(i<Game::gd.texturreigonnum)
	  { Game::gd.regionwidth[i]= engine::assetdatasize(env,1,i);
	    Game::gd.regionheight[i]=engine::assetdatasize(env,2,i);
	    Game::gd.associatedtextures[i]=engine::assetdatasize(env,3,i);
	  i++;
	 }
	 i=0;
	 gd.herox=160;
	 gd.heroy=50;
	 int k=50;
	 gd.herominx=0+k;
	 gd.heromaxx=360-k-34;
     gd.senstivity=4;
     gd.heroangle=0;
     genregion(1);
  }
 ///////////////////////////////////////////////////////////////Genrate the region data //////////////////////////////////////////

 void Game::genregion(int i)
	 {
	  int k=0;
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

	  }k=0;


	  while(k<gd.maxenemey)
	  {
         gd.enemyx[k]=(k*134)%210;
         gd.enemyx[k]+=55;
         int a= (rand()+rand()+rand()+rand())%100;
         if(gd.enemyx[k]>310||gd.enemyx[k]<50||a<15)
            {   gd.enemyx[k]=2500;
            }


         int ja[]={45,-60,90,-45,30,45,-64,30,-23};
         gd.enemyangle[k]=ja[rand()%9];
         gd.enemytype[k]=rand()%4;

         /////////////////////////////////////rechance to get type 2 enemey//////////////////////////////////////////
         if(gd.enemytype[k]==0||gd.enemytype[k]==1)
         gd.enemytype[k]=rand()%4;
         int b[]={85,60,90,100,80,118};
         gd.enemylength[k]=b[rand()%6];

         if(gd.enemytype[k]==0||gd.enemytype[k]==1)
         gd.enemyy[k]=(k-10)*80;
         else
         gd.enemyy[k]=(k-10)*150;

         if(gd.enemyy[k]+r1.regiony>-100&&gd.enemyy[k]+r1.regiony<250)
         {gd.enemyy[k]+=gd.herox+450-r1.regiony;
         }
         k++;
	  }




	 }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

#endif /* INITIALISE_H_ */
