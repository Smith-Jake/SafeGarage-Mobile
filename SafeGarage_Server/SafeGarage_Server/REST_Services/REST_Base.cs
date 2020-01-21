using Microsoft.AspNetCore.Http;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Threading.Tasks;

namespace SafeGarage_Server.REST
{
    public partial class RESTProvider
    {
        public static async Task HandleRestCall(HttpContext context)
        {
            string requestedFunc = context.Request.PathBase.Value.Substring(1);

            Type providerType = typeof(RESTProvider);
            var methods = providerType.GetMethods();

            MethodInfo func = methods.FirstOrDefault(o => o.Name.Equals(requestedFunc));

            if (func == null)
            {
                throw new FileNotFoundException();
            }

            BaseModel response = (BaseModel)func.Invoke(null, null);

            if (!response.DataSuccessful)
            {
                context.Response.StatusCode = 500;
                return;
            }

            string json = JsonConvert.SerializeObject(response);

            await context.Response.WriteAsync(json);
        }
    }
}
