package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
    private final WebDriver driver;

    public SearchPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void searchSmth(String string)
    {
        WebElement searchField = (new WebDriverWait(driver,10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.id("global_search")));
        //searchField.click();
        searchField.sendKeys(string);
    }

}