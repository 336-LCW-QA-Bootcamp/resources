package Waits;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/*An implicit wait is a global setting that applies to all elements in a Selenium script.
It waits a specified time before throwing an exception if the element is not found.
We can set the wait once per session and can’t change it later.
*/

public class ImplicitWait {
    private WebDriver driver;
    private String baseURL = "http://the-internet.herokuapp.com/dynamic_loading/2";

    @BeforeEach
    public void setupTest(){
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(cop);
        driver.navigate().to(baseURL);
        driver.manage().window().maximize();
    }
    @Test
    public void usingImplicitWait(){
        setupTest();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //select the Start button then click it
        WebElement startBtn = driver.findElement(By.cssSelector("#start>button"));
        startBtn.click();

        // see the final text
        WebElement finalText = driver.findElement(By.cssSelector("#finish>h4"));
        Assertions.assertEquals("Hello World!",finalText.getText());
    }




}
