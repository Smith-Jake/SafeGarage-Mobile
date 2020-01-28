#pragma once

class NetworkManager
{
private:
	const String LOCAL_SSID = "SafeGarage";

	String ssid;
	String password;

public:
	void SetSSID(String ssid);
	void EnterPairingMode();
	void ConnectToWIFI();

	bool HasNetworkConnection();
	bool HasServerConnection();

	String GetNextCommand();
};

