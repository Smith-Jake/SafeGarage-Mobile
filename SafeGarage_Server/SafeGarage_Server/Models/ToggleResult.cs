using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Newtonsoft.Json;
using SafeGarage_Server.Enums;

namespace SafeGarage_Server.Models
{
    public class ToggleResult : BaseModel
    {
        [JsonProperty(PropertyName = "success")]
        public bool Success { get; set; }

        [JsonProperty(PropertyName = "state")]
        public GarageState DoorState { get; set; }

        [JsonProperty(PropertyName = "message")]
        public string Message { get; set; }
    }
}
