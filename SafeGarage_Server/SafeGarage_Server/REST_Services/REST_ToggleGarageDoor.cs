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
        public ToggleResult ToggleGarageDoor()
        {
            ToggleResult result = new ToggleResult();

            result.DoorState = Enums.GarageState.TRANSITIONING;
            result.Message = "";
            result.Success = true;

            return result;
        }
    }
}
