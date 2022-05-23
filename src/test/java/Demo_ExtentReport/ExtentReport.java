package Demo_ExtentReport;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.Status;

public class ExtentReport {
    public static ExtentReports reports;
    ExtentHtmlReporter htmlReporter;
    public  ExtentTest customtesterReport;
    WebDriver driver;
    
    
	@BeforeTest
    public void startTest() {
		//create ExtentReports and attach reporter(s)          
		reports = new ExtentReports();
                  htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/Extentreport.html");
                  reports.attachReporter(htmlReporter);
                  htmlReporter.config().setChartVisibilityOnOpen(true);
                  htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
                  htmlReporter.config().setReportName("My Own Report");
                  htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
                  htmlReporter.config().setTheme(Theme.DARK);
                 customtesterReport = reports.createTest("test");
                  //customtesterReport.assignAuthor("---BeforeTest---");

     }
	  
	  @BeforeMethod
      public void openApplication() {
		          customtesterReport.assignAuthor("---BeforeMethod---");
		         System.setProperty("webdriver.chrome.driver" , "C://seleniumWebdriver//chromedriver.exe");
                  driver = new ChromeDriver();
                  customtesterReport.log(Status.INFO, "loading drivers");
                  driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);//for page load
                  driver.get("https://www.facebook.com");
                  customtesterReport.pass("Navigate to facebook.com");
                  
                  driver.manage().window().maximize();
                  customtesterReport.pass("maximize window has done");
                  
      }
	  @Test (priority = 1)
      public void verifyTittleTest() {
		//creates a toggle for the given test, add all log events under it        
		  customtesterReport = reports.createTest("verifyTittleTest");
		//  customtesterReport = customtesterReport.createNode("Step one");
		  customtesterReport.log(Status.INFO, "Starting test case 1");
          String expetedTitle = "Facebook - log in or sign up";
          String pageTitle = driver.getTitle();
          Assert.assertEquals(pageTitle, expetedTitle);
          customtesterReport.pass("title test is done");
      }
	  @Test (priority = 2)
      public void fillRegistrationformTest() throws InterruptedException {
		          customtesterReport = reports.createTest("fillRegistrationformTest");
		          customtesterReport.log(Status.INFO, "Starting test case 2");
                  WebElement email = driver.findElement(By.name("email"));
                  WebElement password = driver.findElement(By.id("pass"));
                  WebElement submitButton = driver.findElement(By.name("login"));
                
                  
                  customtesterReport.pass("passing email in the email text box");
                  email.sendKeys("Aj@gmail.com");
                  customtesterReport.pass("passing password in the password text box");
                  password.sendKeys("mypasswordTesting");
                  Thread.sleep(10000);
                 //submitButton.click();          
      }
	 @Test (priority = 3)
	    public void functionality1Test1()
	    {
		  customtesterReport = reports.createTest("functionality1Test1");
	        Assert.assertTrue(1 > 0);
	    }
	     
	 @Test (priority =4)
	    public void functionality1Test2()
	    {
	    	customtesterReport = reports.createTest("functionality1Test2");
	        Assert.assertEquals("Krishna", "Sakinala");
	    }
	     
	 @Test (priority = 5)
	    public void functionality1Test3()
	    {
	    	customtesterReport = reports.createTest("functionality1Test3");
	        Assert.assertNotEquals("Krishna", "Krishna");
	    }

	  @AfterMethod
      public void setTestResult(ITestResult result) throws IOException {
                  //String screenShot = CaptureScreenShot.captureScreen(driver);
                  if (result.getStatus() == ITestResult.FAILURE) {
                	  customtesterReport.log(Status.FAIL, result.getName());
                	  customtesterReport.log(Status.FAIL,result.getThrowable());
                  //          test.fail("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
                  } else if (result.getStatus() == ITestResult.SUCCESS) {
                	  customtesterReport.log(Status.PASS, result.getName());
                              //test.pass("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
                  } else if (result.getStatus() == ITestResult.SKIP) {
                	  customtesterReport.skip("Test Case : " + result.getName() + " has been skipped");
                  }
                  reports.flush();
                  driver.close();
                  customtesterReport.pass("closed the browser");
                
                  customtesterReport.info("test completed");
             
      }
      @AfterTest
      public void endTest() {                 
                  reports.flush();
                  
      }		
}
