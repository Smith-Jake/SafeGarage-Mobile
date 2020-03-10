using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Net.WebSockets;
using System.Text.RegularExpressions;
using System.Threading;
using System.Threading.Tasks;

namespace SafeGarage_Server.Socket
{
    public class SafeGarageController
    {
        private WebSocket socket;
        private ConcurrentQueue<string> commands;
        private byte[] buffer = new byte[1024 * 4];
        WebSocketReceiveResult result;

        public SafeGarageController(WebSocket socket)
        {
            this.socket = socket;
        }

        public async Task HandleConnection()
        {
            await SendMessage("READY");
            do
            {
                string message = await GetMessage();
                await SendMessage(message);
            } while (!result.CloseStatus.HasValue);
            await socket.CloseAsync(result.CloseStatus.Value, result.CloseStatusDescription, CancellationToken.None);
        }

        public async Task<string> InitializeConnection()
        {
            await SendMessage("INIT");
            do
            {
                string message = await GetMessage();
                Match match = Regex.Match(message, "^id:");
                if (match.Success)
                {
                    return message.Replace("id:", "");
                }
            } while (!result.CloseStatus.HasValue) ;

            await socket.CloseAsync(result.CloseStatus.Value, result.CloseStatusDescription, CancellationToken.None);
            return "";
        }

        public async Task SendMessage(string message)
        {
            buffer = System.Text.Encoding.Default.GetBytes(message);
            await socket.SendAsync(new ArraySegment<byte>(buffer, 0, message.Length), WebSocketMessageType.Text, true, CancellationToken.None);
        }

        private async Task <string> GetMessage()
        {
            buffer = new byte[1024 * 4];
            result = await socket.ReceiveAsync(new ArraySegment<byte>(buffer), CancellationToken.None);
            return System.Text.Encoding.Default.GetString(buffer);
        }
    }
}
