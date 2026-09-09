
using System;
using System.Windows;

namespace PersonalFinanceTracker
{
    public partial class BudgetDivision : Window
    {
        public static int Income;
        public static int Grocery;
        public static int Bills;
        public static int Fuel;
        public static int Misc;
        public static int Savings;

        public BudgetDivision()
        {
            InitializeComponent();
        }

        private void DivideBudget_Click(object sender, RoutedEventArgs e)
        {
            if (!int.TryParse(incomeTextBox.Text, out Income))
            {
                MessageBox.Show("Please enter a valid numeric income.");
                return;
            }

            if (weeklyRadio.IsChecked == true)
            {
                if (Income < 5000)
                {
                    MessageBox.Show("Weekly income must be at least 5000 PKR.");
                    return;
                }
            }
            else if (monthlyRadio.IsChecked == true)
            {
                if (Income < 25000)
                {
                    MessageBox.Show("Monthly income must be at least 25000 PKR.");
                    return;
                }
            }
            else
            {
                MessageBox.Show("Please select Weekly or Monthly option.");
                return;
            }

            Grocery = (int)(Income * 0.30);
            Bills = (int)(Income * 0.25);
            Fuel = (int)(Income * 0.20);
            Misc = (int)(Income * 0.15);
            Savings = Income - (Grocery + Bills + Fuel + Misc);

            budgetDivisionTextBlock.Text =
                "Grocery: " + Grocery + " PKR\n" +
                "Bills: " + Bills + " PKR\n" +
                "Fuel/Transport: " + Fuel + " PKR\n" +
                "Miscellaneous: " + Misc + " PKR\n\n" +
                "If you follow this budget, your savings will be: " + Savings + " PKR";
        }

        private void Next_Click(object sender, RoutedEventArgs e)
        {
            SpendingWindow spendPage = new SpendingWindow();
            spendPage.Show();
            this.Close();
        }
    }
}
