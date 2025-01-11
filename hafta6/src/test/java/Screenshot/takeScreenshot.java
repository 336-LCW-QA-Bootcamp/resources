package Screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class takeScreenshot  {
    public WebDriver driver;
    private String baseURL = "https://www.n11.com/";

    //current directory
    private String currentDir = System.getProperty("user.dir");

    //getScreenShot methods Directory
    private File getScreenShotMethodImage = new File(currentDir+".\\amazonscreenshot.png");

    //element screenshot directory
    private File webElementFile = new File(currentDir+".\\logo.png");

    //entire screenshot directory
    private File entirePageImageFile = new File(currentDir+".\\entirepage.png");

    @BeforeClass
    public void setup(){
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(cop);
        driver.get(baseURL);
        driver.manage().window().maximize();
    }

    @Test
    public void screenshotVisiblePart() throws IOException {
        //take screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        //write screenshot
        FileUtils.copyFile(scrFile,getScreenShotMethodImage);

    }
    @Test
    public void screenshotWebElement() throws IOException {
        //find element
        WebDriverWait wait = new WebDriverWait(driver,50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='iconsSearch']")));

        WebElement searchIcon = driver.findElement(By.xpath("//span[@class='iconsSearch']"));


        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImage = ImageIO.read(screenshot);

        //get location of element
        Point point = searchIcon.getLocation();

        int iconWidth = searchIcon.getSize().getWidth();
        int iconHeight = searchIcon.getSize().getHeight();

        //crop element
        BufferedImage elementSS =fullImage.getSubimage(point.getX(),point.getY(),iconWidth,iconHeight);
        ImageIO.write(elementSS,"png",screenshot);

        //write screenshot
        FileUtils.copyFile(screenshot,webElementFile);
    }
    @Test
    public void screenWebElementWithAshot(){
        //find element
        WebDriverWait wait = new WebDriverWait(driver,50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='iconsSearch']")));

        WebElement searchIcon = driver.findElement(By.xpath("//span[@class='iconsSearch']"));
        Screenshot elementScreenShot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, searchIcon);
        try{
            ImageIO.write(elementScreenShot.getImage(),"PNG",webElementFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Test
    public void entireScreenShotWithAshot() throws IOException {
        //take entire screenshot with ashot
        Screenshot entirePageScreenShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        //write screenshot
        ImageIO.write(((Screenshot) entirePageScreenShot).getImage(),"PNG", entirePageImageFile);
    }
    @AfterClass
    public void quite(){
        driver.quit();
    }
}
