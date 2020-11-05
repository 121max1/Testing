package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage
{
    private WebDriver driver;

    public MainPage(WebDriver webDriver){
        this.driver = webDriver;

    }
    private WebElement loginForm;

    private WebElement loginInput;

    private  WebElement passwordInput;

    private  WebElement captchaInput;//To be done: Connect RuCaptcha

    private  WebElement loginButton;

    public void openForm() {
        //WebElement explicitWait = (new WebDriverWait(driver,10))
        //        .until(ExpectedConditions.visibilityOfElementLocated(By.id("login_form_show_js")));
        WebElement openForm = driver.findElement(By.className("auth-popup__expandable-text"));
        openForm.click();
    }
    public void inputLogin(String login){
        WebElement explicitWait = (new WebDriverWait(driver,10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        explicitWait.sendKeys(login);
    }

    public void inputPassword(String password){
        WebElement explicitWait = (new WebDriverWait(driver,10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("pass")));
        explicitWait.sendKeys(password);
        passwordInput = explicitWait;
    }

    public  void login(){
        passwordInput.submit();
    }

    public void logout(){
        WebElement menu = driver.findElement(By.className("auth-user-popup__text"));
        menu.click();
        WebElement exitButton = driver.findElement(By.className("auth-user-popup__icon-exit"));
        exitButton.click();
    }
    public String getCityOnTheCityButton() {
        return (new WebDriverWait(driver,10)).
        until(ExpectedConditions.visibilityOfElementLocated(By.className("popup_close "))).getText();
    }

    public  void clickCityButton() {
        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("popup_close "))).click();
    }
    public void changeCityByName(String cityName){
         WebElement city = (new WebDriverWait(driver,10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(cityName)));
         city.click();
    }
}
