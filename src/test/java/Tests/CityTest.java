package Tests;

import Common.CitilinkWatcher;
import Common.Settings;
import Pages.MainPage;
import Pages.ProfilePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)
public class CityTest {
    public  static MainPage mainPage;
    public  static ProfilePage profilePage;
    public  static WebDriver driver;
    private static String city;

    public CityTest(String city) {
        this.city = city;
    }

    @BeforeClass
    public  static  void setup() {
        System.setProperty("webdriver.chrome.driver",
                Settings.webDriverPath);
        driver = new ChromeDriver();
        driver.get("https://www.citilink.ru/");
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        driver.manage().window().maximize();
        try {
            mainPage.goToOldPage();
        }
        catch (Exception exception) {
        }
    }
    @Parameterized.Parameters
    public  static Iterable<Object[]> getParameters(){
        return Arrays.asList(new Object[][]{
                {"Самара"},
                {"Москва"},
        });

    }
    @AfterClass
    public  static void logout(){
        driver.quit();
    }

    @Rule
    public CitilinkWatcher watcher = new CitilinkWatcher(driver);
    @Epic("Тестирование сайта https://www.citilink.ru/")
    @Story(value = "Проверка изменения города при авторизации и без неё")
    @Test
    public void CityTest() throws InterruptedException {
        String motherCity = mainPage.getCityOnTheCityButton();
        mainPage.clickCityButton();
        mainPage.changeCityByName(city);
        String currentCity = mainPage.getCityOnTheCityButton();
        Assert.assertEquals(city,currentCity);
        mainPage.openForm();
        mainPage.inputLogin(Settings.login);
        mainPage.inputPassword(Settings.password);
        Thread.sleep(10000);
        mainPage.login();
        profilePage.enterProfilePageFromMainPage();
        String cityOnTheTop = profilePage.getCityOnTheTop();
        String deliveryCity = profilePage.getDeliveryCityOnProfilePage();
        Assert.assertEquals(cityOnTheTop,deliveryCity);
        profilePage.returnToMainPageFromProfilePage();
        mainPage.logout();
    }
}
