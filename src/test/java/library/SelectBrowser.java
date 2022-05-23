package library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SelectBrowser {
    static WebDriver driver;

    public static WebDriver StartBrowser(String browsername)
    {
        // ---If the browser is Firefox----
        if(browsername.equalsIgnoreCase("Firefox"))
        {
            // Set the path for geckodriver.exe        System.setProperty("webdriver.firefox.marionette","C://seleniumWeb//drivergeckodriver-v0.30.0-win64geckodriver.exe ");
            driver = new FirefoxDriver();
        }
        //---- If the browser is Chrome--
        else if(browsername.equalsIgnoreCase("Chrome"))
        {
            // Set the path for chromedriver.exe
            System.setProperty("webdriver.chrome.driver" , "C://seleniumWebdriver//chromedriver.exe");
            driver = new ChromeDriver();
        }
        // ----If the browser is  EdgeIE----
        else if(browsername.equalsIgnoreCase("EdgeExplore"))
        {
            // Set the path for IEdriver
            System.setProperty("webdriver.edge.driver", "C://seleniumWebdriver//msedgedriver.exe");
            // Instantiate a EdgeDriverclass.
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
        }
        driver.manage().window().maximize();
        return driver;
    }
}

