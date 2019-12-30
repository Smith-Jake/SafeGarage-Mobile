using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.DependencyInjection;
using System.Reflection;
using SafeGarage_Server.Services;
using Newtonsoft.Json;

namespace SafeGarage_Server
{
    public class Startup
    {
        // This method gets called by the runtime. Use this method to add services to the container.
        // For more information on how to configure your application, visit https://go.microsoft.com/fwlink/?LinkID=398940
        public void ConfigureServices(IServiceCollection services)
        {
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.Run(async (context) =>
            {
                string requestedFunc = context.Request.PathBase.Value.Substring(1);
                
                Type providerType = typeof(Provider);
                var methods = providerType.GetMethods();

                MethodInfo func = methods.FirstOrDefault(o => o.Name.Equals(requestedFunc));
                
                if (func == null)
                {
                    context.Response.StatusCode = 404;
                    return;
                }

                try
                {
                    BaseModel response = (BaseModel)func.Invoke(null, null);

                    if (!response.DataSuccessful)
                    {
                        context.Response.StatusCode = 500;
                        return;
                    }

                    string json = JsonConvert.SerializeObject(response);

                    await context.Response.WriteAsync(json);
                } 
                catch (TargetInvocationException e)
                {
                    if (e.InnerException.GetType() == typeof(NotImplementedException))
                    {
                        context.Response.StatusCode = 501;
                    }
                    else
                    {
                        context.Response.StatusCode = 500;
                    }
                }
                /*
                await context.Response.WriteAsync($"Path: {context.Request.Path}\n\n");
                await context.Response.WriteAsync($"Parameters\n");

                foreach (var key in context.Request.Query.Keys)
                {
                    string val = context.Request.Query[key];
                    await context.Response.WriteAsync($"{key}: {val}\n");
                }
                */
            });
        }
    }
}
