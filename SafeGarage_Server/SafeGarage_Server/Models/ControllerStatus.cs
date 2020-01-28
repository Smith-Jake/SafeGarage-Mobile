using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using SafeGarage_Server.Enums;
using Newtonsoft.Json;
using SafeGarage_Server.Converters;

namespace SafeGarage_Server.Models
{
    public class ControllerStatus : BaseModel
    {
        [JsonProperty(PropertyName = "co")]
        public bool CoAlarm { get; set; }

        [JsonProperty(PropertyName = "smoke")]
        public bool SmokeAlarm { get; set; }

        [JsonProperty(PropertyName = "close_time")]
        [JsonConverter(typeof(TimeConverter))]
        public DateTime CloseTime { get; set; }

        [JsonProperty(PropertyName = "state")]
        public GarageState DoorState { get; set; }

        [JsonProperty(PropertyName = "temp")]
        public int Temp { get; set; }
    }
}
