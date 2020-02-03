using Newtonsoft.Json;

namespace SafeGarage_Server_Upload
{
    public class Pusher
    {
        [JsonProperty(PropertyName = "name")]
        public string name;

        [JsonProperty(PropertyName = "email")]
        public string email;
    }
}