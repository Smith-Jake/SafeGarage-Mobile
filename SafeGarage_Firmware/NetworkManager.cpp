#include "NetworkManager.h"

void NetworkManager::printWifiData()
{
#ifdef NETWORK_DEBUG
	// print your board's IP address:
	IPAddress ip = WiFi.localIP();
	Serial.print("IP Address: ");
	Serial.println(ip);
	Serial.println(ip);

	// print your MAC address:
	byte mac[6];
	WiFi.macAddress(mac);
	Serial.print("MAC address: ");
	printMacAddress(mac);
#endif // !NetworkDebug
}

void NetworkManager::printCurrentNet()
{
#ifdef NETWORK_DEBUG
	// print the SSID of the network you're attached to:
	Serial.print("SSID: ");
	Serial.println(WiFi.SSID());

	// print the MAC address of the router you're attached to:
	byte bssid[6];
	WiFi.BSSID(bssid);
	Serial.print("BSSID: ");
	printMacAddress(bssid);

	// print the received signal strength:
	long rssi = WiFi.RSSI();
	Serial.print("signal strength (RSSI):");
	Serial.println(rssi);

	// print the encryption type:
	byte encryption = WiFi.encryptionType();
	Serial.print("Encryption Type:");
	Serial.println(encryption, HEX);
	Serial.println();
#endif // NETWORK_DEBUG

}

void NetworkManager::printMacAddress(byte mac[])
{
#ifdef NETWORK_DEBUG
	for (int i = 5; i >= 0; i--) {
		if (mac[i] < 16) {
			Serial.print("0");
		}
		Serial.print(mac[i], HEX);
		if (i > 0) {
			Serial.print(":");
		}
	}
	Serial.println();
#endif // NETWORK_DEBUG

}

NetworkManager::NetworkManager() {
	this->ssid = "Lukes WIFI";
	this->password = "b4053a44";
	this->status = WL_IDLE_STATUS;
	this->deviceId = "TEST";
}

void NetworkManager::SetSSID(String ssid)
{
	this->ssid = ssid;
}

void NetworkManager::EnterPairingMode()
{
}

void NetworkManager::ConnectToWIFI()
{
	if (WiFi.status() == WL_NO_MODULE) {
		Serial.println("Communication with WiFi module failed!");
		// don't continue
		while (true);
	}

	String fv = WiFi.firmwareVersion();
	if (fv < WIFI_FIRMWARE_LATEST_VERSION) {
		Serial.println("Please upgrade the firmware");
	}

	// attempt to connect to Wifi network:
	while (status != WL_CONNECTED) {
		#ifdef NETWORK_DEBUG
			Serial.print("Attempting to connect to WPA SSID: ");
			Serial.println(ssid);
		#endif // NETWORK_DEBUG

		// Connect to WPA/WPA2 network:
		status = WiFi.begin(ssid.c_str(), password.c_str());

		// wait 10 seconds for connection:
		delay(10000);
	}

	// you're connected now, so print out the data:
	Serial.print("You're connected to the network");
	printCurrentNet();
	printWifiData();
}

void NetworkManager::ConnectToServer()
{
	#ifdef NETWORK_DEBUG
		Serial.println("Attempting connection to server");
	#endif // NETWORK_DEBUG
		
	server.begin("/ws");

	bool initComplete = false;
	while (server.connected() && !initComplete) {
		int messageSize = server.parseMessage();
		if (messageSize > 0) {
			String message = server.readString();
			if (message == "INIT") {
				server.beginMessage(TYPE_TEXT);
				server.print("id:");
				server.print(deviceId);
				server.endMessage();
			}
			else if (message == "READY") {
				initComplete = true;
			}

			#ifdef NETWORK_DEBUG
				Serial.println("Received a message:");
				Serial.println(message);
			#endif // NETWORK_DEBUG
		}
	}
}

bool NetworkManager::HasNetworkConnection()
{
	if (WiFi.status() != WL_CONNECTED) {
		ConnectToWIFI();
	}
	#ifdef NETWORK_DEBUG
		Serial.print("Network Connection Status: ");
		Serial.println(WiFi.status());
	#endif // NETWORK_DEBUG
	return WiFi.status() == WL_CONNECTED;
}

bool NetworkManager::HasServerConnection()
{
	if (WiFi.status() != WL_CONNECTED) { return false; }
	if (!server.connected()) {
		ConnectToServer();
	}
	#ifdef NETWORK_DEBUG
		Serial.print("Server Connection Status: ");
		Serial.println(server.connected());
	#endif // NETWORK_DEBUG
	return server.connected();
}

String NetworkManager::GetNextCommand()
{
	return String("");
}
