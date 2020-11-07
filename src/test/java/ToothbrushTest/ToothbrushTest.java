package ToothbrushTest;

import Pages.MainPage;

import Pages.SearchPage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class ToothbrushTest {

    private static WebDriver driver;
    public static SearchPage searchPage;
    public static MainPage mainPage;

    @BeforeClass
    public  static  void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\shkodinms\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.citilink.ru/");
        mainPage = new MainPage(driver);
        searchPage = new SearchPage(driver);
    }



    @Test
    public void ToothbrushTest() {
        searchPage.getToothbrushesSearchPage();
        searchPage.enterMinPriceOnSearchPage(999);
        searchPage.enterMaxPriceOnSearchPage(1999);
        ArrayList<Integer> prices = searchPage.getPricesOfToothbrushes();
        for (Integer price:prices) {
            Assert.assertEquals(1499, price,500);
        }

    }
}
