package Navigations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Navigations {
    public WebDriver driver;

    final private String baseURL = "https://www.lcw.com/";
    final private String URL1 = "https://www.patika.dev/patikaplus?utm_source=site&utm_campaign=header_placement";
    final private String URL2 = "https://www.kodluyoruz.org/";

    @BeforeAll
    public void setupTest(){
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(cop);
    }

    @AfterTest
    public void quiteDriver(){
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(cop);
        driver.close();
    }

    @Test
    public void getCurrentURL(){
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(cop);

        driver.get(baseURL);

        //Verify title...
        Assertions.assertEquals(driver.getTitle(),"LCW.com: Trendler ve Yenilikçi Online Alışveriş Deneyimi Burada! | LCW");

    }
    @Test
    public void navigateURL(){
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(cop);
        driver.get(baseURL);
        driver.navigate().to(URL2);
        //Assertions.assertEquals(driver.getTitle(),"Kodluyoruz | Ana sayfa");
    }
    @Test
    public void backRefreshForwardSamples(){

        //Go to Patika
        ChromeOptions cop = new ChromeOptions();
        cop.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(cop);
        driver.get(baseURL);
        Assertions.assertEquals(driver.getTitle(),"LCW.com: Trendler ve Yenilikçi Online Alışveriş Deneyimi Burada! | LCW");

        //Go to Kodluyoruz
        driver.navigate().to(URL2);
        Assertions.assertEquals("Kodluyoruz | Ana sayfa","Kodluyoruz | Ana sayfa");

        //Back
        driver.navigate().back();
        Assertions.assertEquals(driver.getTitle(),"LCW.com: Trendler ve Yenilikçi Online Alışveriş Deneyimi Burada! | LCW");

        //Forward
        driver.navigate().forward();
        Assertions.assertEquals("Kodluyoruz | Ana sayfa","Kodluyoruz | Ana sayfa");

        //Refresh
        driver.navigate().refresh();
        Assertions.assertEquals("Kodluyoruz | Ana sayfa","Kodluyoruz | Ana sayfa");
    }

}
