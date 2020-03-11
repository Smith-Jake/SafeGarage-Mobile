#pragma once
#include "enums.h"

class GarageDoorManager
{
private:
	int Prox1Pin;
	int Prox2Pin;

public:
	void ToggleGarage();
	GarageState GetGarageState();
	void SetProximityPins(int prox1, int prox2);
};

