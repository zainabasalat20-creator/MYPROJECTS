using System.IO;
using System.Xml.Serialization;

namespace FinanceTrackerAppWPF
{
    public class FinanceRecordManager
    {
        // Serialize object to XML
        public static void SaveToXml(FinanceRecord record, string fileName)
        {
            XmlSerializer serializer = new XmlSerializer(typeof(FinanceRecord));
            using (StreamWriter writer = new StreamWriter(fileName))
            {
                serializer.Serialize(writer, record);
            }
        }

        // Deserialize object from XML
        public static FinanceRecord LoadFromXml(string fileName)
        {
            XmlSerializer serializer = new XmlSerializer(typeof(FinanceRecord));
            using (StreamReader reader = new StreamReader(fileName))
            {
                return (FinanceRecord)serializer.Deserialize(reader);
            }
        }
    }
}
