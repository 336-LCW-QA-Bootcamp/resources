package Actions;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)

public class Actions {
    private WebDriver driver;
    private WebDriverWait wait;
    final private String baseURL = "http://the-internet.herokuapp.com";

    @BeforeAll
    public void setupTest() {

        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(cop);
        driver.navigate().to(baseURL);
    }

    @Test
    public void clickElement(){
        setupTest();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clickElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/abtest']")));
        clickElement.click();

    }
    @Test
    public void checkBoxElement(){
        setupTest();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkboxTextLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/checkboxes']")));
        checkboxTextLink.click();

        WebElement checkbox1 = driver.findElement(By.xpath("//input[1]"));
        assertTrue(checkbox1.isEnabled());

        WebElement checkbox2 = driver.findElement(By.xpath("//input[1]"));
        assertFalse(checkbox2.isSelected());
    }
    @Test
    public void dropDownElement(){
        setupTest();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdownTextLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/dropdown']")));
        dropdownTextLink.click();

        WebElement dropdownText = driver.findElement(By.xpath("//select[@id='dropdown']"));
        dropdownText.click();

        //select by index
        WebElement dropdownElement1 = driver.findElement(By.xpath("//option[@value='1']"));
        dropdownElement1.isSelected();

        WebElement dropdownElement2 = driver.findElement(By.xpath("//option[@value='2']"));
        dropdownElement2.isDisplayed();

    }
    @Test
    public void clickAndHoldElement(){
        setupTest();
        driver.navigate().to("https://www.lcw.com/");

        WebElement loginIcon = driver.findElement(By.id("user_1_"));


        //Creating object of an Actions class
        org.openqa.selenium.interactions.Actions action = new org.openqa.selenium.interactions.Actions(driver);
        //Performing the mouse hover action on the target element.
        action.clickAndHold(loginIcon).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //WebElement signupButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cart-action__btn.cart-action__btn--bg-blue")));
        WebElement signupButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='cart-action__btn cart-action__btn--bg-blue']")));
        signupButton.click();
    }
    @Test
    public void mouseHoverAction(){
        setupTest();
        driver.navigate().to("https://demoqa.com/menu/");

        //maximize the screen
        driver.manage().window().maximize();

        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        WebElement secondMenuItem = driver.findElement(By.xpath("//ul[@id='nav']/li[2]/a[@href='#']"));

        //go to the Menu Item-2
        actions.moveToElement(secondMenuItem).perform();

        driver.close();
    }
    @Test
    public void doubleClickAction(){
        setupTest();
        driver.navigate().to("https://www.google.com/");

        org.openqa.selenium.interactions.Actions action = new org.openqa.selenium.interactions.Actions(driver);
        WebElement loginBtn = driver.findElement(By.linkText("Oturum açın"));

        action.doubleClick(loginBtn).perform();
    }
    @Test
    public void sendKeyAction(){
        setupTest();
        driver.navigate().to(baseURL+"/login");

        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);

        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("Test Lover");

    }
    @Test
    public void keyboardAction(){
        setupTest();
        driver.navigate().to("https://demoqa.com/text-box");

        org.openqa.selenium.interactions.Actions action = new org.openqa.selenium.interactions.Actions(driver);

        WebElement username = driver.findElement(By.id("userName"));
        username.sendKeys("Göknur BATI");

        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("goknurbati@patikadev.com");

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("Zemin İstanbul - Beyoğlu");

        action.keyDown(Keys.CONTROL);
        action.sendKeys("A");
        action.keyUp(Keys.CONTROL);
        action.build().perform();

        // Copy the Current Address using CTRL + C
        action.keyDown(Keys.CONTROL);
        action.sendKeys("C");
        action.keyUp(Keys.CONTROL);
        action.build().perform();

        //Press the TAB Key to Switch Focus to Permanent Address
        action.sendKeys(Keys.TAB);
        action.build().perform();

        //click the tab for switch the permanennt address textbox

        action.sendKeys(Keys.TAB);
        action.build().perform();

        //paste the current address to the  permanent address textbox

        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        permanentAddress.sendKeys(Keys.CONTROL);
        permanentAddress.sendKeys("Zemin İstanbul - Beyoğlu");

        //verify the address textboxes...
        assertEquals(currentAddress.getAttribute("value"),permanentAddress.getAttribute("value"));

    }

}
