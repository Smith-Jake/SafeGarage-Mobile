using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.DependencyInjection;
using System.Reflection;
using SafeGarage_Server.REST;
using Newtonsoft.Json;
using System.IO;
using SafeGarage_Server.Socket;

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

            app.UseWebSockets();

            app.Run(async (context) =>
            {
                try
                {
                    if (context.WebSockets.IsWebSocketRequest)
                    {
                        Console.WriteLine("\n\n\nWEB SOCKET\n\n\n");
                        await ControllerManager.HandleSocketConnection(context);
                    }
                    else
                    {
                        Console.WriteLine("\n\n\nREST\n\n\n");
                        await RESTProvider.HandleRestCall(context);
                    }
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
                catch (FileNotFoundException)
                {
                    context.Response.StatusCode = 404;
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
