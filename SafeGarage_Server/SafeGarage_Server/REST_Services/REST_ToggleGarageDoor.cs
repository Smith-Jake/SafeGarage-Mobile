using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using SafeGarage_Server.Models;

namespace SafeGarage_Server.REST
{
    [HttpGet("/ToggleGargageDoor")]
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
