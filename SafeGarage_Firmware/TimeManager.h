#pragma once
#include "Arduino.h"

class TimeManager
{
private:
	int RTCPin;

public:
	void SetRTCPin(int pin);
	long GetCurrentTime();
};

