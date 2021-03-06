﻿using System;
using Newtonsoft.Json;
using SafeGarage_Server.Converters;

namespace SafeGarage_Server.Models
{
    public class SetTimeResult : BaseModel
    {
        [JsonProperty(PropertyName = "success")]
        public bool Success { get; set; }

        [JsonProperty(PropertyName = "close_time")]
        [JsonConverter(typeof(TimeConverter))]
        public DateTime CloseTime { get; set; }
    }
}
