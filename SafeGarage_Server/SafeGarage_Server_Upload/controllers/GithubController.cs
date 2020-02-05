using System;
using Newtonsoft.Json.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

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

            if (request == null) return "";

            if (request.referance == "refs/heads/master")
            {
                Process p = new Process();
                p.StartInfo.FileName = "upload.sh";
                p.Start();
            }

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
