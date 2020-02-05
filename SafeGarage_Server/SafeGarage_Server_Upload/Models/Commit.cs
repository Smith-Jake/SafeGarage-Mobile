using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SafeGarage_Server_Upload.Models
{
    public class Commit
    {
        [JsonProperty("id")]
        public string id { get; set; }

        [JsonProperty("tree_id")]
        public string tree_id { get;set; }

        [JsonProperty("message")]
        public string message { get; set; }

        [JsonProperty("timestamp")]
        public string timestamp { get; set; }

        [JsonProperty("author")]
        public Author author { get; set; }

        [JsonProperty("added")]
        public string[] added { get; set; }

        [JsonProperty("removed")]
        public string[] removed { get; set; }

        [JsonProperty("modified")]
        public string[] modified { get; set; }
    }
}
