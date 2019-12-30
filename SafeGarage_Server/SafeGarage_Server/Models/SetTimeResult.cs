using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace SafeGarage_Server.Models
{
    public class SetTimeResult : BaseModel
    {
        [JsonProperty(PropertyName = "success")]
        public bool Success { get; set; }

        [JsonProperty(PropertyName = "close_time")]
        public DateTime CloseTime { get; set; }
    }
}
