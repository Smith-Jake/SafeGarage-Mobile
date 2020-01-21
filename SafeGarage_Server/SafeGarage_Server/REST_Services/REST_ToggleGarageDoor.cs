using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using SafeGarage_Server.Models;

namespace SafeGarage_Server.REST
{
    public partial class RESTProvider
    {
        public static ToggleResult ToggleGarageDoor()
        {
            ToggleResult result = new ToggleResult();

            result.DoorState = Enums.GarageState.CLOSED;
            result.Message = "";
            result.Success = true;

            return result;
        }
    }
}
