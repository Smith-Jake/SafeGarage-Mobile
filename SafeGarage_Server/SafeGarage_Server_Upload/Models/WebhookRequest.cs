using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SafeGarage_Server_Upload
{
    public class WebhookRequest
    {
        [JsonProperty(PropertyName = "ref")]
        string referance;

        [JsonProperty(propertyName: "pusher")]
        public Pusher pusher;
    }
}
