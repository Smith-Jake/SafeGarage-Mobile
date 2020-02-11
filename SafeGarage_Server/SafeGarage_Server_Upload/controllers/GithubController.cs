using System;
using Newtonsoft.Json.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;
using System.IO;
using System.Text;

namespace SafeGarage_Server_Upload
{
    [ApiController]
    public class GithubController : Controller
    {
        [HttpPost]
        [Route("api/github")]
        public string Github(WebhookRequest request)
        {
            if (request == null) return "";
            PrintDebug($"Recieved Ref:{request._ref ?? "NULL"}");

            string message = "Success";
            if (request?._ref.Contains("refs/heads/master") ?? false)
            {
                if (!RunUpload()) message = "Error";
            }

            return $"<h1>{message}</h1>";
        }

        private bool RunUpload()
        {
            try
            {
                PrintDebug("Start Processing");
                var p = new Process()
                {
                    StartInfo = new ProcessStartInfo
                    {
                        FileName = "/bin/bash",
                        Arguments = $"-c \"netcoreapp3.1/upload.sh\"",
                        RedirectStandardOutput = true,
                        UseShellExecute = false,
                        CreateNoWindow = true
                    }
                };

                p.Start();

                PrintDebug("Done Processing");
            }
            catch (Exception e)
            {
                PrintDebug(e.Message, ConsoleColor.Red);
                PrintDebug(e.StackTrace, ConsoleColor.Red);
                return false;
            }
            return true;
        }

        private static void PrintDebug(string message, ConsoleColor color = ConsoleColor.Green)
        {
            Console.ForegroundColor = color;
            Console.WriteLine($"\n\n");

            Console.WriteLine(message);

            Console.WriteLine($"\n\n");
            Console.ForegroundColor = ConsoleColor.White;
        }
    }
}
