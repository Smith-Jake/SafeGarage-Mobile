using System;
using Newtonsoft.Json.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;
using System.IO;
using System.Text;

namespace SafeGarage_Server_Upload
{
    //[Route("api/[controller]")]
    [ApiController]
    public class GithubController : Controller
    {
        private void Github(WebhookRequest request)
        {
            if (request == null) return;
            PrintDebug($"Recieved Ref:{request._ref ?? "NULL"}");

            if (request?._ref.Contains("refs/heads/master") ?? false)
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

                var reader = p.StandardOutput;
                string line;
                while ((line = reader.ReadLine()) != null)
                {
                    Console.WriteLine(line);
                }

                PrintDebug("Done Processing");
            }
        }

        [HttpPost]
        [Route("api/github")]
        public string manual()
        {
            using (StreamReader reader = new StreamReader(Request.Body, Encoding.UTF8))
            {
                string body = reader.ReadToEndAsync().Result;
                try
                {
                    Github(Newtonsoft.Json.JsonConvert.DeserializeObject<WebhookRequest>(body));
                }
                catch (Exception e)
                {
                    PrintDebug(e.Message, ConsoleColor.Red);
                    PrintDebug(e.StackTrace, ConsoleColor.Red);
                    throw new Exception();
                }

                return "";
            }
        }

        private static void PrintDebug(string message, ConsoleColor color = ConsoleColor.Green)
        {
            Console.ForegroundColor = color;
            Console.WriteLine($"\n\n");

            Console.WriteLine(message);

            Console.WriteLine($"\n\n");
            Console.ForegroundColor = ConsoleColor.White;
        }

        private static void PrintRequest(WebhookRequest request)
        {
            PrintDebug(
                $"ref: {request._ref}" +
                $""
            );
        }
    }
}
