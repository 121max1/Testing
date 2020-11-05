package ToothbrusTest;

import Pages.MainPage;
import Pages.ProfilePage;

import Pages.SearchPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToothbrushTest {

    private static WebDriver driver;
    public static SearchPage searchPage;
    public  static MainPage mainPage;

    @BeforeClass
    public  static  void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\user\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.citilink.ru/");
        mainPage = new MainPage(driver);
        searchPage = new SearchPage(driver);
    }



    @Test
    public void ToothbrushTest()
    {
        searchPage.searchSmth("Зубные щетки");
    }

}
