using System.Windows;

namespace FinanceTrackerAppWPF
{
    public partial class BudgetWindow : Window
    {
        public BudgetWindow()
        {
            InitializeComponent();
        }

        private void DivideBudget_Click(object sender, RoutedEventArgs e)
        {
            // Declare the income variable
            int income = 0;

            // Parse the income value from the TextBox
            if (!int.TryParse(txtIncome.Text, out income))
            {
                MessageBox.Show("Please enter a valid income.");
                return;
            }

            // Calculate the budget distribution
            int grocery = (int)(income * 0.30);
            int bills = (int)(income * 0.25);
            int transport = (int)(income * 0.20);
            int misc = (int)(income * 0.10);
            int savings = income - (grocery + bills + transport + misc);

            // Display the breakdown
            txtBreakdown.Text = "Budget Breakdown:\n" +
                                "Grocery: " + grocery + "\n" +
                                "Bills: " + bills + "\n" +
                                "Transport: " + transport + "\n" +
                                "Misc: " + misc + "\n" +
                                "Estimated Savings: " + savings;

            // Open the SpendingWindow and pass values
            SpendingWindow sw = new SpendingWindow(income, grocery, bills, transport, misc, savings);
            sw.Show();
            this.Close();
        }
    }
}
