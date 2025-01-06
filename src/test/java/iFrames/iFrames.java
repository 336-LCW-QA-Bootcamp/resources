package iFrames;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**Goknur BATI*/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)

public class iFrames {

    private WebDriver driver;

    @BeforeAll
    public void setupTest() {

        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(cop);

        driver.navigate().to("http://www.londonfreelance.org/courses/frames/index.html");
        driver.manage().window().maximize();
    }

    @Test
    public void switchTOFrameByIndex(){
        setupTest();

        driver.switchTo().frame(2);
        WebElement title = driver.findElement(By.cssSelector("html>body>h2"));
        Assertions.assertEquals("Title bar (top.html)",title.getText());
    }

    @Test
    public void switchToFrameByName(){
        driver.switchTo().frame("main");

        WebElement title = driver.findElement(By.cssSelector("html>body>h2"));
        Assertions.assertEquals("Title bar (top.html)",title.getText());
    }
    @Test
    public void switchTOFrameByWebElement(){
        WebElement mainFrame = driver.findElement(By.cssSelector("html>frameset>frameset>frameset>frame:nth-child(1)"));
        driver.switchTo().frame(mainFrame);

        WebElement mainFrameTitle = driver.findElement(By.cssSelector("html>body>h2"));
        Assertions.assertEquals("Title bar (top.html)",mainFrameTitle.getText());
    }
    @Test
    public void backToMainFrame(){
        driver.switchTo().defaultContent();
    }

    @Test
    public void returnToDefaultContent(){
        driver.switchTo().defaultContent();
    }

    @AfterAll
    public void teardown(){
        driver.close();
    }
}
