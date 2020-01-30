using System;
using Microsoft.AspNetCore.WebHooks;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json.Linq;

namespace SafeGarage_Server_Upload
{
    public class GithubController : ControllerBase
    {
        [GitHubWebHook(EventName = "push", Id = "It")]
        public IActionResult HandlerForItsPushes(string[] events, JObject data)
        {
            PrintDebug("HandlerForItsPushes");
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            
            return Ok();
        }

        [GitHubWebHook(EventName = "push")]
        public IActionResult HandlerForPush(string id, JObject data)
        {
            PrintDebug("HandlerForPushes");
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            return Ok();
        }

        [GitHubWebHook]
        public IActionResult GitHubHandler(string id, string @event, JObject data)
        {
            PrintDebug("GitHubHandler");
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            return Ok();
        }

        [GeneralWebHook]
        public IActionResult FallbackHandler(string receiverName, string id, string eventName)
        {
            PrintDebug("FallbackHandler");
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            return Ok();
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
