package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import library.SelectBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePageTesting;

import java.util.concurrent.TimeUnit;

public class TestForEbay {
    //creating driver object
    private static WebDriver driver;
    //setting expected title from the ebay.com/Daraz.lk
    static String expectedTitleebay ="Electronics, Cars, Fashion, Collectibles & More | eBay";
    static String expectedTitledaraz ="Online Shopping Sri Lanka: Clothes, Electronics & Phones | Daraz.lk";
    //create the htmlReporter object
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test1,test2;
    HomePageTesting homepage;

    @BeforeClass
    public void setup() throws InterruptedException {
        htmlReporter = new ExtentHtmlReporter("extentReport.html");
//create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //initializing and starting the browser
        // here you can change the browser
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Navigate to daraz.lk
        driver.get("https://www.daraz.lk/#");
        Thread.sleep(1000);
        //test1.pass("Navigate to Ebay.com");

    }

    @Test(priority = 1)
    public void verifyTitle()
    {
        test2 = extent.createTest("Daraz Search Test", "test to validate search box ");
        test2.log(Status.INFO, "Starting test case");
        homepage = new HomePageTesting();
        String actualtitle = homepage.veryHeader();
        Assert.assertEquals(actualtitle, expectedTitleebay);
        test1.pass("title is correct");
    }
    @Test(priority = 2)
    public void searchProduct()
    {
        homepage = new HomePageTesting();
        homepage.searchfacilityTest();
        test1.pass("Entered text in the text box");
        test1.pass("Press keybopard enter key");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        test1.pass("closed the browser");
        test2.pass("closed the browser");
        test1.info("test completed");
        test2.info("test completed");

        //write results into the file
        extent.flush();
    }

}
