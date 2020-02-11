using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using SafeGarage_Server.Models;

namespace SafeGarage_Server.REST
{
    public partial class RESTProvider
    {
        [HttpGet("/SetCloseTime")]
        public SetTimeResult SetCloseTime([FromQuery]string closeTime)
        {
            SetTimeResult result = new SetTimeResult();

            DateTime newTime;
            bool success = DateTime.TryParseExact(closeTime, "HH:mm", System.Globalization.CultureInfo.CurrentCulture, System.Globalization.DateTimeStyles.None, out newTime);

            result.Success = success;
            result.CloseTime = success ? newTime : DateTime.MinValue;

            return result;
        }
    }
}
