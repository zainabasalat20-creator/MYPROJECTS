
using System;
using System.Windows;

namespace PersonalFinanceTracker
{
    public partial class BudgetDivision : Window
    {
        public double Income { get; set; }
        public string BudgetDivisionResult { get; set; }

        public BudgetDivision()
        {
            InitializeComponent();
        }

        private void DivideBudget_Click(object sender, RoutedEventArgs e)
        {
            double parsedIncome;
            if (string.IsNullOrWhiteSpace(incomeTextBox.Text) || !double.TryParse(incomeTextBox.Text, out parsedIncome))
            {
                MessageBox.Show("Please enter a valid income amount in PKR.");
                return;
            }
            Income = parsedIncome;

            if (weeklyRadio.IsChecked == false && monthlyRadio.IsChecked == false)
            {
                MessageBox.Show("Please select either Weekly or Monthly option.");
                return;
            }

            double grocery, bills, fuel, misc;

            if (weeklyRadio.IsChecked == true && Income >= 5000)
            {
                grocery = Income * 0.25;
                bills = Income * 0.20;
                fuel = Income * 0.15;
                misc = Income * 0.10;
            }
            else if (monthlyRadio.IsChecked == true && Income >= 25000)
            {
                grocery = Income * 0.20;
                bills = Income * 0.15;
                fuel = Income * 0.10;
                misc = Income * 0.05;
            }
            else
            {
                MessageBox.Show("Income is below the minimum required for the selected option.");
                return;
            }

            BudgetDivisionResult = "Grocery: " + grocery.ToString("F2") + " PKR\n" +
                                   "Bills: " + bills.ToString("F2") + " PKR\n" +
                                   "Fuel/Transport: " + fuel.ToString("F2") + " PKR\n" +
                                   "Miscellaneous: " + misc.ToString("F2") + " PKR";
            budgetDivisionTextBlock.Text = BudgetDivisionResult;
        }

        private void Next_Click(object sender, RoutedEventArgs e)
        {
            FinanceRecord record = new FinanceRecord
            {
                Income = this.Income
            };

            SpendingWindow spendingWindow = new SpendingWindow(record);
            spendingWindow.Show();
            this.Close();
        }
    }
}
