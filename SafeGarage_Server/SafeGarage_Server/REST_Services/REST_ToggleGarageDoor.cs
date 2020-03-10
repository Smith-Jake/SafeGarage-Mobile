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
        [HttpGet("/ToggleGarageDoor")]
        public string ToggleGarageDoor()
        {
            ToggleResult result = new ToggleResult();

            state = state == Enums.GarageState.OPEN ? Enums.GarageState.CLOSED : Enums.GarageState.OPEN;

            result.DoorState = Enums.GarageState.TRANSITIONING;
            result.Message = "";
            result.Success = true;

            return result.ToString();
        }
    }
}
