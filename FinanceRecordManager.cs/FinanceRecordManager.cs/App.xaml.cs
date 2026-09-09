using System.Windows;

namespace PersonalFinanceTracker
{
    public partial class App : Application
    {
        protected override void OnStartup(StartupEventArgs e)
        {
            base.OnStartup(e);
            WelcomePage window = new WelcomePage();
            window.Show();
        }
    }
}
