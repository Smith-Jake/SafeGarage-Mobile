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
        [HttpGet("GetStatus")]
        public string GetStatus()
        {
            Random rand = new Random();

            ControllerStatus result = new ControllerStatus();
            result.CloseTime = DateTime.Now;
            result.CoAlarm = rand.Next(0, 100) < 10;
            result.DoorState = rand.Next(0, 100) < 50 ? Enums.GarageState.CLOSED : Enums.GarageState.OPEN;
            result.SmokeAlarm = rand.Next(0, 100) < 10;
            result.Temp = rand.Next(0, 100);

            return result.ToString();
        }
    }
}
