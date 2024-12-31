package FindElements;

import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)

public class FindElements {

    private WebDriver driver;

    final private String baseURL = "https://www.lcw.com/";


    @BeforeAll
    public void setupTest() {
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(cop);
    }

    @Test
    public void goToLoginPage(){
        setupTest();
        driver.navigate().to(baseURL);

        WebElement loginIcon = driver.findElement(By.id("user_1_"));
        //Creating object of an Actions class
        Actions action = new Actions(driver);
        //Performing the mouse hover action on the target element.
        action.moveToElement(loginIcon).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //WebElement signupButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cart-action__btn.cart-action__btn--bg-blue")));
        WebElement signupButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='cart-action__btn cart-action__btn--bg-blue']")));
        signupButton.click();

    }
   @Test
    public void loginWithValidCredentials(){
       setupTest();
       driver.navigate().to(baseURL);

       WebElement loginIcon = driver.findElement(By.id("user_1_"));
       //Creating object of an Actions class
       Actions action = new Actions(driver);
       //Performing the mouse hover action on the target element.
       action.moveToElement(loginIcon).perform();

       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       //WebElement signupButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cart-action__btn.cart-action__btn--bg-blue")));
       WebElement signupButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='cart-action__btn cart-action__btn--bg-blue']")));
       signupButton.click();

       WebElement emailTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='emailAndPhone']")));
       emailTextBox.sendKeys("goknurbati@gmail.com");

       WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(" //button[@class='login-form__button login-form__button--bg-blue']")));
       continueButton.click();

       WebElement passwordTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='password']")));
       passwordTextBox.sendKeys("Galata8559*");

   }
    @AfterAll
    public void quiteTest(){
        driver.quit();
    }

}
