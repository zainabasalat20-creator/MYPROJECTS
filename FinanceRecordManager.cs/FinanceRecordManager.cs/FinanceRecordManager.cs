using System;
using System.IO;
using System.Xml.Serialization;

namespace PersonalFinanceTracker
{
    public class FinanceRecordManager
    {
        private static string filePath = "finance_data.xml";

        public static void SaveRecord(FinanceRecord record)
        {
            try
            {
                XmlSerializer serializer = new XmlSerializer(typeof(FinanceRecord));
                using (FileStream fs = new FileStream(filePath, FileMode.Create))
                {
                    serializer.Serialize(fs, record);
                }
            }
            catch (Exception ex)
            {
                System.Windows.MessageBox.Show("Error saving data: " + ex.Message);
            }
        }

        public static FinanceRecord LoadRecord()
        {
            try
            {
                if (File.Exists(filePath))
                {
                    XmlSerializer serializer = new XmlSerializer(typeof(FinanceRecord));
                    using (FileStream fs = new FileStream(filePath, FileMode.Open))
                    {
                        return (FinanceRecord)serializer.Deserialize(fs);
                    }
                }
            }
            catch (Exception ex)
            {
                System.Windows.MessageBox.Show("Error loading data: " + ex.Message);
            }
            return new FinanceRecord();
        }
    }
}
