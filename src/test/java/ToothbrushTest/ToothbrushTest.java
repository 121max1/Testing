package ToothbrushTest;

import Pages.CartPage;
import Pages.MainPage;

import Pages.SearchPage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class ToothbrushTest {

    private static WebDriver driver;
    public static SearchPage searchPage;
    public static MainPage mainPage;
    public static CartPage cartPage;

    @BeforeClass
    public  static  void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\user\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.citilink.ru/");
        mainPage = new MainPage(driver);
        searchPage = new SearchPage(driver);
        cartPage = new CartPage(driver);
    }



    @Test
    public void ToothbrushTest() throws InterruptedException {
        mainPage.openForm();
        mainPage.inputLogin("x2w3g4@gmail.com");
        mainPage.inputPassword("192837465Max");
        Thread.sleep(10000);
        mainPage.login();
        searchPage.getToothbrushesSearchPage();
        searchPage.enterMinPriceOnSearchPage(999);
        searchPage.enterMaxPriceOnSearchPage(1999);
        Thread.sleep(3000);
        List<WebElement> toothbrushes = searchPage.getToothbrushes();
        Assert.assertFalse(toothbrushes.isEmpty());
        searchPage.addToCart(toothbrushes.get(toothbrushes.size() - 2));
        searchPage.getCartPage();
        cartPage.clickCheckout();
        cartPage.getDeliveryPage();
        int priceToCheck = cartPage.getDeliveryPrice()+cartPage.getProductPrice();
        Assert.assertEquals(cartPage.getPriceWithDelivery(),priceToCheck);
        searchPage.getCartPage();
        cartPage.changeAmountOfProducts(2);
        cartPage.clickCheckout();
        cartPage.getDeliveryPage();
        Assert.assertTrue(cartPage.getPriceWithDelivery()>2999);
        priceToCheck = cartPage.getDeliveryPrice()+cartPage.getProductPrice();
        Assert.assertEquals(cartPage.getPriceWithDelivery(),priceToCheck);
    }
}
