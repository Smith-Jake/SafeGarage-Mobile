using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using SafeGarage_Server.Models;

namespace SafeGarage_Server.Services
{
    public partial class Provider
    {
        public static SetTimeResult SetCloseTime(DateTime closeTime)
        {
            SetTimeResult result = new SetTimeResult();

            result.Success = true;
            result.CloseTime = DateTime.Now;

            return result;
        }
    }
}
