package СityTest;

import Pages.MainPage;
import Pages.ProfilePage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
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
    private static String expectedCity;

    public CityTest(String expectedCity, String city) {
        this.city = city;
        this.expectedCity = expectedCity;
    }

    @BeforeClass
    public  static  void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\user\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.citilink.ru/");
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
    }
    @Parameterized.Parameters
    public  static Iterable<Object[]> getParameters(){
        return Arrays.asList(new Object[][]{
                {"Самара","Самара"},
                {"Москва","Москва"},
                {"Иваново","Иваново"},
                {"Калуга","Калуга"},
                {"Казань","Казань"},
        });

    }
    @AfterClass
    public  static void logout(){
        mainPage.logout();
        driver.quit();
    }
    @Test
    public void CityTest() throws InterruptedException {
        String motherCity = mainPage.getCityOnTheCityButton();
        mainPage.clickCityButton();
        mainPage.changeCityByName("Воронеж");
        Thread.sleep(5000);
        String currentCity = mainPage.getCityOnTheCityButton();
        Assert.assertEquals("Воронеж",currentCity);
        //Assert.assertEquals(expected_city,currentCity);
        mainPage.openForm();
        mainPage.inputLogin("x2w3g4@gmail.com");
        mainPage.inputPassword("192837465Max");
        Thread.sleep(10000);
        mainPage.login();
        profilePage.enterProfilePageFromMainPage();
        String cityOnTheTop = profilePage.getCityOnTheTop();
        String deliveryCity = profilePage.getDeliveryCityOnProfilePage();
        Assert.assertEquals(cityOnTheTop,deliveryCity);
        profilePage.returnToMainPageFromProfilePage();
    }
}
