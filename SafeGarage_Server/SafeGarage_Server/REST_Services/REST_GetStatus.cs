using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using SafeGarage_Server.Models;

namespace SafeGarage_Server.REST
{
    public partial class RESTProvider
    {
        public static ControllerStatus GetStatus()
        {
            ControllerStatus result = new ControllerStatus();
            result.CloseTime = DateTime.Now;
            result.CoAlarm = false;
            result.DoorState = Enums.GarageState.CLOSED;
            result.SmokeAlarm = false;
            result.Temp = 70;

            return result;
        }
    }
}
