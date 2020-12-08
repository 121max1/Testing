package Tests;

import Common.CitilinkWatcher;
import Common.Settings;
import Pages.MainPage;
import Pages.ProfilePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    public  static MainPage mainPage;
    public  static ProfilePage profilePage;
    public  static WebDriver driver;

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

    @AfterClass
    public  static void logout(){
        mainPage.logout();
        driver.quit();
    }

    @Rule
    public CitilinkWatcher watcher = new CitilinkWatcher(driver);
    @Epic("Тестирование сайта https://www.citilink.ru/")
    @Story(value = "Проверка имени пользователя при входе")
    @Test
    public void loginTest() throws InterruptedException {
        mainPage.openForm();
        mainPage.inputLogin(Settings.login);
        mainPage.inputPassword(Settings.password);
        Thread.sleep(10000);
        mainPage.login();
        profilePage.enterProfilePageFromMainPage();
        Assert.assertEquals("Максим", profilePage.getUsername());
        profilePage.returnToMainPageFromProfilePage();
   }


}
