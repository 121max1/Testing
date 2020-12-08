package Tests;

import Common.CitilinkWatcher;
import Common.Settings;
import Pages.CartPage;
import Pages.MainPage;

import Pages.SearchPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.*;
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
                Settings.webDriverPath);
        driver = new ChromeDriver();
        driver.get("https://www.citilink.ru/");
        mainPage = new MainPage(driver);
        searchPage = new SearchPage(driver);
        cartPage = new CartPage(driver);
        driver.manage().window().maximize();
        try {
            mainPage.goToOldPage();
        }
        catch (Exception exception) {
        }
    }

    @AfterClass
    public  static void logout(){
        mainPage.logout();
        driver.quit();
    }

    @Rule
    public CitilinkWatcher watcher = new CitilinkWatcher(driver);
    @Epic("Тестирование сайта https://www.citilink.ru/")
    @Story(value = "Проверка стоимости зубных счеток")
    @Test
    public void ToothbrushTest() throws InterruptedException {
        mainPage.openForm();
        mainPage.inputLogin(Settings.login);
        mainPage.inputPassword(Settings.password);
        Thread.sleep(10000);
        mainPage.login();
        if (!searchPage.getCartText().contains("Корзина")) {
            searchPage.getCartPage();
            cartPage.removeProductsInCart();
            mainPage.returnToMainPage();
        }
        searchPage.getToothbrushesSearchPage();
        searchPage.enterMinPriceOnSearchPage(999);
        searchPage.enterMaxPriceOnSearchPage(1999);
        List<WebElement> toothbrushes = searchPage.getToothbrushes();
        Assert.assertFalse(toothbrushes.isEmpty());
        searchPage.addToCart(toothbrushes.get(toothbrushes.size() - 2));
        searchPage.getCartPage();
        cartPage.clickCheckout();
        cartPage.getDeliveryPage();
        int priceToCheck = cartPage.getDeliveryPrice()+cartPage.getProductPrice();
        int cnt = (cartPage.getProductPrice()*2 + cartPage.getProductPrice() >2999)?2:3;
        Assert.assertEquals(cartPage.getPriceWithDelivery(),priceToCheck);
        searchPage.getCartPage();
        cartPage.changeAmountOfProducts(cnt);
        cartPage.clickCheckout();
        cartPage.getDeliveryPage();
        Assert.assertTrue(cartPage.getPriceWithDelivery()>2999);
        priceToCheck = cartPage.getDeliveryPrice()+cartPage.getProductPrice();
        Assert.assertEquals(cartPage.getPriceWithDelivery(),priceToCheck);
        searchPage.getCartPage();
        cartPage.removeProductsInCart();

    }
}
