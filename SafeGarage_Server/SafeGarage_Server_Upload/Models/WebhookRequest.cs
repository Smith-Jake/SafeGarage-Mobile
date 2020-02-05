using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SafeGarage_Server_Upload
{
    public class WebhookRequest
    {
        [JsonProperty("ref")]
        public string referance { get; set; }

        [JsonProperty("pusher")]
        public Pusher pusher { get; set; }
    }
}
