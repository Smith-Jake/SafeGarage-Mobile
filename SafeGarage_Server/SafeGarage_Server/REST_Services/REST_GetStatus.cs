using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using SafeGarage_Server.Enums;
using SafeGarage_Server.Models;

namespace SafeGarage_Server.REST
{
    public partial class RESTProvider
    {
        private static DateTime time = new DateTime();
        private static GarageState state = GarageState.OPEN;

        [HttpGet("GetStatus")]
        public string GetStatus()
        {
            Random rand = new Random();

            ControllerStatus result = new ControllerStatus();
            result.CloseTime = time;
            result.CoAlarm = rand.Next(0, 100) < 10;
            result.DoorState = state;
            result.SmokeAlarm = rand.Next(0, 100) < 10;
            result.Temp = rand.Next(0, 100);

            return result.ToString();
        }
    }
}
