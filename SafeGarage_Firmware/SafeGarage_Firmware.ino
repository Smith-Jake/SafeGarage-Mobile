/*
 Name:		SafeGarage_Firmware.ino
 Created:	12/6/2019 9:02:38 AM
 Author:	lukeg
*/

#include <ArduinoHttpClient.h>
#include <WiFiNINA.h>
#include "AlarmManager.h"
#include "GarageDoorManager.h"
#include "MainController.h"
#include "NetworkManager.h"
#include "NotificationManager.h"
#include "StorageManager.h"
#include "TemperatureManager.h"
#include "TimeManager.h"

#include "enums.h"

TimeManager time;
StorageManager storage;
NetworkManager network;

void setup() {
	//Initialize serial and wait for port to open:
	Serial.begin(9600);
	while (!Serial) {
		; // wait for serial port to connect. Needed for native USB port only
	}

	Serial.println("Init Complete");
}

// the loop function runs over and over again until power down or reset
void loop() {
	if (time.GetCurrentTime() > 0) {//Set Close Time
		//TODO handle garage door
	}

	if (!network.HasNetworkConnection()) { return; }
	if (!network.HasServerConnection()) { return; }

	//Serial.write("Has Connection");

	String command = network.GetNextCommand();
	if (command != NULL) {
		//TODO Handle command
	}

	//TODO Recalculate Closing Time
}
