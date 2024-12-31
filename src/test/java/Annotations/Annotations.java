package Annotations;

import ch.qos.logback.core.util.FileUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Annotations {

    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    public WebDriver driver;

    //Declare a test URL variable
    public String testURL = "https://www.patika.dev/";

    @BeforeSuite
    public void setup(){

        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(cop);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("The setup process is completed...");
    }
    @BeforeTest
    public void profileSetup(){
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(cop);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("The setup process is completed...");

        driver.manage().window().maximize();
        System.out.println("The profile setup process is completed...");
    }
    @BeforeClass
    public void goToURL(){
        driver.get(testURL);
        System.out.println("The URl setup process is completed...");
    }
    @BeforeMethod
    public void checkLogin(){
        driver.get(testURL);
        driver.findElement(By.xpath(""));
        System.out.println("The login is success...");
    }
    @Test
    public void currectURL(){
        driver.findElement(By.xpath(""));
        String currentUrl=driver.getCurrentUrl();
        Assert.assertEquals(currentUrl,"testURL");
    }
    @AfterMethod
    public void screenShot(){
        TakesScreenshot ss = ((TakesScreenshot)driver);
        File file = ss.getScreenshotAs(OutputType.FILE);
    }
    @AfterClass
    public void close(){
        driver.close();
        System.out.println("The close up process is completed...");

       /* System.out.println("@AfterClass has started.");
        driver.quit();*/
    }
    @AfterTest
    public void reportReady(){
        System.out.println("Report is ready to be shared...");
    }
    @AfterSuite
    public void cleanup(){
        System.out.println("All close up activities are completed...");
    }

}
