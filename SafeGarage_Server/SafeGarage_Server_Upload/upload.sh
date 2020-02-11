cd /home/safegarage
dotnet build SafeGarage-Mobile/SafeGarage_Server/SafeGarage_Server/SafeGarage_Server.csproj --configuration release

rm server
mv SafeGarage-Mobile/SafeGarage_Server/SafeGarage_Server/bin/netcore3.1 server