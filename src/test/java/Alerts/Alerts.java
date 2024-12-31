package Alerts;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)

public class Alerts {

    private WebDriver driver;
    private String URL1 = "http://www.w3schools.com/js/tryit.asp?filename=tryjs_alert";
    private String URL2 = "http://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm";
    private String URL3 = "http://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt";

    @BeforeAll
    public void setupTest() {
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(cop);
    }

    @Test
    public void simpleAlertMessageSample(){
        setupTest();
        driver.navigate().to(URL1);
        driver.switchTo().frame("iframeResult");

        WebElement tryBtn = driver.findElement(By.cssSelector("html>body>button"));
        tryBtn.click();

        String expectedResult = "I am an alert box!";
        String actualResult = driver.switchTo().alert().getText();
        Assertions.assertEquals(expectedResult,actualResult);

        driver.switchTo().alert().accept();
    }

    @Test
    public void confirmationAlertMessageSample(){
        setupTest();
        driver.navigate().to(URL2);
        driver.switchTo().frame("iframeResult");

        WebElement tryBtn = driver.findElement(By.cssSelector("html>body>button"));
        tryBtn.click();

        driver.switchTo().alert().accept();

        WebElement actualMessage = driver.findElement(By.cssSelector("#demo"));
        Assertions.assertEquals("You pressed OK!",actualMessage.getText());
    }

    @Test
    public void promptAlertMessageSample(){
        setupTest();
        driver.navigate().to(URL3);
        driver.switchTo().frame("iframeResult");

        WebElement tryBtn = driver.findElement(By.cssSelector("html>body>button"));
        tryBtn.click();

        driver.switchTo().alert().sendKeys("Patika Dev");

        //Click 'OK' button
        driver.switchTo().alert().accept();

        //Click 'CANCEL' button
        //driver.switchTo().alert().dismiss();

        WebElement actualMessage = driver.findElement(By.cssSelector("#demo"));
        Assertions.assertEquals("Hello Patika Dev! How are you today?",actualMessage.getText());

    }



}
