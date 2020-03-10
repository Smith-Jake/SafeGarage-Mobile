using System;
using Newtonsoft.Json;

namespace SafeGarage_Server
{
    public class BaseModel
    {
        [NonSerialized]
        public bool DataSuccessful = true;

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
