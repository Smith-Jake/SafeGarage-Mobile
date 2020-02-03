using System;
using Newtonsoft.Json.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace SafeGarage_Server_Upload
{
    [Route("api/[controller]")]
    [ApiController]
    public class GithubController
    {
        [HttpPost]
        public string Github(WebhookRequest request)
        {
            PrintDebug("Recieved");

            PrintDebug(
                $"Ref:\t{request?.referance ?? "NULL"}\n" +
                $"Name:\t{request?.pusher?.name ?? "NULL"}\n" +
                $"Email:\t{request?.pusher?.email ?? "NULL"}"
            );

            return "";
        }

        private static void PrintDebug(string message)
        {
            Console.ForegroundColor = ConsoleColor.Green;
            Console.WriteLine($"\n\n");

            Console.WriteLine(message);

            Console.WriteLine($"\n\n");
            Console.ForegroundColor = ConsoleColor.White;
        }
    }
}
