package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;
public class HomePageTesting {
    WebDriver driver;
    @FindBy(xpath = "//*[@id=\"q\"]")
    WebElement SearchField;

    @FindBy(xpath = "//*[@id=\"topActionHeader\"]/div/div[2]/div/div[2]/form/div/div[2]/button")
    WebElement Seachbutton;

    public HomePageTesting() {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loadWebPage(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public String veryHeader() {
        String actualTitle = driver.getTitle();
        return actualTitle;
        //String actualTitle=driver.findElement(Header).getText();
        //System.out.println("Home page heading : " + actualTitle);
        }
        public void searchfacilityTest()
        {
            SearchField.sendKeys("Mobile");
            Seachbutton.sendKeys(Keys.RETURN);
        }
    }
