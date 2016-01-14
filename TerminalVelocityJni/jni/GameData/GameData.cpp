/*
 * GameData.cpp
 *
 *  Created on: 26-Oct-2012
 *      Author: Praveen Ojha
 */

#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include "GameData.h"

GameData::GameData(bool musci, bool soundst)
{
 this->cur_ax=0;
 this->cur_ay=0;
 this->cur_az=0;
 this->minreg=-1000;
 this->maxreg=1000;
this->hit=false;
this->first=true;
this->maxcoins=50;
this->maxenemey=25;
this->musicstate=musci;
this->soundstate=soundst;
this->life=3;
}

GameData::~GameData()
{
}
RegionData::RegionData()
{

}

RegionData::~RegionData()
{

}





