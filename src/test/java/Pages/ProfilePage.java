package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ProfilePage {
    private final WebDriver driver;

    public ProfilePage(WebDriver driver)
    {
        this.driver = driver;
    }



    public void enterProfilePageFromMainPage() throws InterruptedException {
        WebElement menu = (new WebDriverWait(driver,10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("auth-user-popup__text ")));
        menu.click();
        Thread.sleep(1000);
        WebElement profilePage = (new WebDriverWait(driver,10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"layout\"]/header/div/div[2]/div/div[3]/div/div/div/ul/li[4]/a/span")));
        profilePage.click();
    }

    public String getUsername()
    {
        return (new WebDriverWait(driver,10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("user_name"))).getText();
    }

    public void returnToMainPageFromProfilePage(){
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.className("screen"))).click();
    }

    public String getCityOnTheTop()
    {
        return (new WebDriverWait(driver,10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("popup_close "))).getText();
    }

    public String getDeliveryCityOnProfilePage(){
        WebElement address = (new WebDriverWait(driver,10).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("delivery_address"))));
        return address.getText().split(",")[0].replace(" ","");
    }
}
