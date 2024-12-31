package Waits;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)

/*An explicit wait is a more flexible wait that allows us to wait for a specific condition to be met before continuing test execution.
We can define the condition, such as the presence or absence of an element, using ExpectedConditions class.
An exception is thrown if the condition is not met within the specified time.
* */

public class ExplicitWait {

    private WebDriver driver;

    private String baseURL="https://www.lcw.com/";

    public void setupTest(){
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(cop);

        driver.navigate().to(baseURL);
    }

    @Test
      public void usingExplicitWait(){
        setupTest();
        WebElement signInIcon = driver.findElement(By.id("user_1_"));

        //Performing the mouse hover action on the target element.
        Actions action = new Actions(driver);
        action.moveToElement(signInIcon).perform();

        //use explicit wait
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='cart-action__btn cart-action__btn--bg-blue']")));

        //verify the Sign-In button and click it

        WebElement signInBtn = driver.findElement(By.xpath("//a[@class='cart-action__btn cart-action__btn--bg-blue']"));
        signInBtn.click();
    }

}
