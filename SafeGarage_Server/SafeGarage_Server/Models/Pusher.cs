using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SafeGarage_Server.Models
{
    public class Pusher
    {
        [JsonProperty(propertyName: "name")]
        public string name;

        [JsonProperty(propertyName: "email")]
        public string email;
    }
}
