#pragma once
class StorageManager
{
private:
	//TODO Storage Device - Complete after parts arrive

public:
	bool WriteWifiInfo(String ap, String ssid, String password);
	bool WriteClosingTimes(String times[]);
	bool WriteSetClosingTime(String time);
};

