using Newtonsoft.Json;

namespace SafeGarage_Server_Upload
{
    public class Pusher
    {
        [JsonProperty("name")]
        public string name { get; set; }

        [JsonProperty("email")]
        public string email { get; set; }
    }
}