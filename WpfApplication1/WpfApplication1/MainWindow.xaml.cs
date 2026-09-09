using System;
using System.Windows;
using System.Windows.Threading;

namespace FinanceTrackerAppWPF
{
    public partial class MainWindow : Window
    {
        private DispatcherTimer timer;
        private int progressValue;

        public MainWindow()
        {
            InitializeComponent();

            progressValue = 0;
            progressBar.Value = progressValue;

            timer = new DispatcherTimer();
            timer.Interval = TimeSpan.FromMilliseconds(50); // 50ms * 100 = 5000ms = 5 sec
            timer.Tick += Timer_Tick;
            timer.Start();
        }

        private void Timer_Tick(object sender, EventArgs e)
        {
            if (progressValue < 100)
            {
                progressValue++;
                progressBar.Value = progressValue;
            }
            else
            {
                timer.Stop();
                btnNext.IsEnabled = true; // Enable the button after 100%
            }
        }

        private void Next_Click(object sender, RoutedEventArgs e)
        {
            BudgetWindow budgetWindow = new BudgetWindow();
            budgetWindow.Show();
            this.Close();
        }
    }
}
