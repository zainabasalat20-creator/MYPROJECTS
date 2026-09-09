
namespace PersonalFinanceTracker
{
    public class FinanceRecord
    {
        public double Income { get; set; }
        public double SpentGrocery { get; set; }
        public double SpentBills { get; set; }
        public double SpentFuel { get; set; }
        public double SpentMisc { get; set; }

        
        public double TotalSpendings()
        {
            return SpentGrocery + SpentBills + SpentFuel + SpentMisc;
        }

        
        public bool IsOverBudget()
        {
            double totalSpend = TotalSpendings();
            return totalSpend > Income;
        }
    }
}
