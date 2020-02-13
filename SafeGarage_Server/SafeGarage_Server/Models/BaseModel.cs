using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
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
