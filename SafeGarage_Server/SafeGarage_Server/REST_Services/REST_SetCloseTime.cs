using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using SafeGarage_Server.Models;

namespace SafeGarage_Server.REST
{
    public partial class RESTProvider
    {
        [HttpGet("/SetCloseTime")]
        public string SetCloseTime([FromQuery]string closeTime)
        {
            SetTimeResult result = new SetTimeResult();

            DateTime newTime;
            bool success = DateTime.TryParseExact(closeTime, "HH:mm", System.Globalization.CultureInfo.CurrentCulture, System.Globalization.DateTimeStyles.None, out newTime);

            result.Success = success;
            if (success)
            {
                time = newTime;
            }
            result.CloseTime = time;

            return result.ToString();
        }
    }
}
