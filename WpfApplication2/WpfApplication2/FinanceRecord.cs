using System;
using System.Collections.Generic;

namespace PersonalFinanceTracker
{
    public class FinanceRecord
    {
        public double Income { get; set; }
        public string Mode { get; set; } // "Weekly" or "Monthly"
        public double Grocery { get; set; }
        public double Bills { get; set; }
        public double Fuel { get; set; }
        public double Misc { get; set; }
        public double Savings { get; set; }

        public double SpentGrocery { get; set; }
        public double SpentBills { get; set; }
        public double SpentFuel { get; set; }
        public double SpentMisc { get; set; }

        public FinanceRecord()
        {
            Income = 0;
            Grocery = 0;
            Bills = 0;
            Fuel = 0;
            Misc = 0;
            Savings = 0;
            SpentGrocery = 0;
            SpentBills = 0;
            SpentFuel = 0;
            SpentMisc = 0;
            Mode = "";
        }

        public double TotalSpendings()
        {
            return SpentGrocery + SpentBills + SpentFuel + SpentMisc;
        }

        public bool IsOverBudget()
        {
            return TotalSpendings() > Income;
        }
    }
}
