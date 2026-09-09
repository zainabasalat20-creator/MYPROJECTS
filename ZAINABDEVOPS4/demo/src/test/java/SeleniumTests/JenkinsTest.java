package SeleniumTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class JenkinsTest {

    @Test
    @SuppressWarnings("UseSpecificCatch")
    public void testAutomation() {

        // Manually set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        // Set Chrome browser path
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

        // Launch Chrome
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        try {
            // Open Amazon and search Nike shoes
            driver.get("https://www.amazon.in");
            driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nike shoes");
            driver.findElement(By.id("nav-search-submit-button")).click();
            Thread.sleep(3000);

            // Open Facebook and attempt login
            driver.get("https://www.facebook.com");
            driver.findElement(By.name("email")).sendKeys("abcd@gmail.com");
            driver.findElement(By.name("pass")).sendKeys("1234");
            driver.findElement(By.name("login")).click();
            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}