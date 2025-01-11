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

    /**
     * BeforeSuite: It will run only once, before all tests in the suite are executed.
     * */
    @BeforeSuite
    public void setup(){

        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(cop);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("The setup process is completed...");
    }

    /**BeforeTest: This will be executed before the first @Test annotated method. It can be executed multiple times before the test case.
     **/
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

    /**BeforeClass: This will be executed before first @Test method execution. It will be executed one only time throughout the test case.
     **/
    @BeforeClass
    public void goToURL(){
        driver.get(testURL);
        System.out.println("The URl setup process is completed...");
    }

    /**BeforeMethod: This will be executed before every @test annotated method
     * */
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

    /**AfterMethod: This will be executed after every @test annotated method.
     **/
    @AfterMethod
    public void screenShot(){
        TakesScreenshot ss = ((TakesScreenshot)driver);
        File file = ss.getScreenshotAs(OutputType.FILE);
    }

    /**AfterClass: This will be executed after all test methods in the current class have been run*/
    @AfterClass
    public void close(){
        driver.close();
        System.out.println("The close up process is completed...");

       /* System.out.println("@AfterClass has started.");
        driver.quit();*/
    }

    /**AfterTest: A method with this annotation will be executed when all @Test annotated
     * methods complete the execution of those classes inside the <test> tag in the TestNG.xml file.
     **/
    @AfterTest
    public void reportReady(){
        System.out.println("Report is ready to be shared...");
    }

    /**AfterSuite: A method with this annotation will run once after the execution of all tests in the suite is complete.*/
    @AfterSuite
    public void cleanup(){
        System.out.println("All close up activities are completed...");
    }

}
