package WindowsHandling;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)

public class Windows {

    private WebDriver driver;
    final private String baseURL="http://www.w3schools.com/tags/tryit.asp?filename=tryhtml_link_target";

    @BeforeAll
    public void setupTest(){
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(cop);

        driver.navigate().to(baseURL);
    }

    @Test
    public void switchToWindow(){
        setupTest();
        //get current window
        System.out.println("Current Window" + driver.getWindowHandle());

        //switch to iframe
        driver.switchTo().frame("iframeResult");

        //click the textlink
        WebElement linkText = driver.findElement(By.linkText("Visit W3Schools.com!"));
        linkText.click();

        //get all windows in a list
        Set<String> allWindows = driver.getWindowHandles();
        List<String> windowHandleList = new ArrayList<>(allWindows);

        //write total window on console
        System.out.println("Total Window Number:"+windowHandleList);

        //switch to second window
        driver.switchTo().window(windowHandleList.get(1));

        //verify logo in second window
        WebElement logo = driver.findElement(By.cssSelector(".fa.fa-logo"));
        Assertions.assertTrue(logo.isDisplayed());

        //back to first window
        driver.switchTo().window(windowHandleList.get(0));

        //get current window
        System.out.println("Current Window:"+driver.getWindowHandle());

        //verify "Run" button
        WebElement runButton = driver.findElement(By.id("runbtn"));
        Assertions.assertEquals("Run",runButton.getText());
    }

    @Test
    public void manageWindow(){
        setupTest();

        //navigate to URL
        driver.navigate().to(baseURL);

        //maximaze the window
        driver.manage().window().maximize();

        //get the current window height and width
        Dimension windowSize = driver.manage().window().getSize();
        System.out.println("Window's Widht:"+windowSize.getWidth());

        //minimize the current window by 1/2
        driver.manage().window().setSize(new Dimension(windowSize.getWidth()/2,windowSize.getHeight()/2));
        Dimension halfOfTheWindow = driver.manage().window().getSize();

        System.out.println("Minimized The Window's Width :"+halfOfTheWindow.getWidth());
        System.out.println("Minimize The Window's Height :"+halfOfTheWindow.getHeight());

        //get current window position
        Point newWindowPosition = new Point(200,200);
        driver.manage().window().setPosition(newWindowPosition);

        System.out.println("New X Coordinate Position:" + driver.manage().window().getPosition().getX());
        System.out.println("New Y Coordinate Position:" + driver.manage().window().getPosition().getY());

    }





}
