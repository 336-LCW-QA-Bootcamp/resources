package CSSLocators;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)

public class CSSLocators {

    /**
     * https://www.swtestacademy.com/css-selenium/
     * */
    private WebDriver driver;

    final private String baseURL = "https://demoqa.com/automation-practice-form";


    @BeforeAll
    public void setupTest() {
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(cop);
        driver.navigate().to(baseURL);
    }

    @Test
    public void cssSelectorWithIDAttribute(){
        setupTest();

        //to perform Scroll on application using Selenium
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

        WebElement firstName = driver.findElement(By.cssSelector("input#firstName"));
        Assertions.assertEquals("",firstName.getText());
    }

    @Test
    public void cssSelectorWithClassAttribute(){
        setupTest();

        //to perform Scroll on application using Selenium
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

        WebElement currentAddress = driver.findElement(By.cssSelector("textarea.form-control"));
        Assertions.assertEquals("",currentAddress.getText());
    }


    @Test
    public void cssSelectorWithText(){
        setupTest();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement title = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h1.text-center")));

        Assertions.assertEquals("Practice Form",title.getText());

        //to perform Scroll on application using Selenium
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,450)", "");

        WebElement header = driver.findElement(By.cssSelector("h1.text-center"));
        Assertions.assertEquals("Practice Form",header.getText());
    }


    @Test
    public void cssSelectorWithStartingText(){
        setupTest();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement title = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h1.text-center")));

        Assertions.assertEquals("Practice Form",title.getText());

        WebElement startingText = driver.findElement(By.cssSelector("input[id^='userN']"));
        Assertions.assertEquals("",startingText.getText());
    }


    @Test
    public void cssSelectorWithEndingText(){
        setupTest();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement title = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h1.text-center")));

        Assertions.assertEquals("Practice Form",title.getText());

        WebElement endingText = driver.findElement(By.cssSelector("input[id^='firstN']"));
        Assertions.assertEquals("",endingText.getText());
    }

    @Test
    public void cssSelectorWithTagName(){
        setupTest();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement title = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h1.text-center")));

        Assertions.assertEquals("Practice Form",title.getText());

        WebElement asd = driver.findElement(By.tagName("textarea"));
        Assertions.assertEquals("",asd.getText());

    }

    }

