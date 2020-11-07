package LoginTest;

import Pages.MainPage;
import Pages.ProfilePage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    public  static MainPage mainPage;
    public  static ProfilePage profilePage;
    public  static WebDriver driver;

    @BeforeClass
    public  static  void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\shkodinms\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.citilink.ru/");
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @AfterClass
    public  static void logout(){
        mainPage.logout();
        driver.quit();
    }

    @Test
    public void loginTest() throws InterruptedException {
        mainPage.openForm();
        mainPage.inputLogin("x2w3g4@gmail.com");
        mainPage.inputPassword("192837465Max");
        Thread.sleep(10000);
        mainPage.login();
        //driver.wait(10);
        Thread.sleep(10000);
        profilePage.enterProfilePageFromMainPage();
        Assert.assertEquals("Максим", profilePage.getUsername());
        profilePage.returnToMainPageFromProfilePage();
   }


}
