#pragma once

#include "Arduino.h"
#include "SPI.h"
#include "WiFiNINA.h"
#include "ArduinoHttpClient.h"

#define NETWORK_DEBUG
#define SERVER_URL "192.168.1.14" //"sg.milligan.dev/ws"

class NetworkManager
{
private:
	const String LOCAL_SSID = "SafeGarage";

	String ssid;
	String password;
	String deviceId;

	int status;
	WiFiClient _wifi;
	WebSocketClient server = WebSocketClient(_wifi, SERVER_URL, 5001);

	void ConnectToWIFI();
	void ConnectToServer();

	void printWifiData();
	void printCurrentNet();
	void printMacAddress(byte mac[]);

public:
	NetworkManager();

	void SetSSID(String ssid);
	void SetPassword(String pass);
	void SetDeviceId(String id);
	void EnterPairingMode();

	bool HasNetworkConnection();
	bool HasServerConnection();

	String GetNextCommand();
};
