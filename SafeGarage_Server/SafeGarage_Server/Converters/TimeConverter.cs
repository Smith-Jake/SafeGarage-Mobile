using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Newtonsoft.Json.Converters;

namespace SafeGarage_Server.Converters
{
    class TimeConverter : IsoDateTimeConverter
    {
        public TimeConverter()
        {
            base.DateTimeFormat = "HH:mm";
        }
    }
}
