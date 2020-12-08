package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchPage {
    private final WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getToothbrushesSearchPage() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("menu-item_cat_beauty"))).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Зубные щетки и аксессуары"))).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Зубные щетки"))).click();
    }

    public void enterMinPriceOnSearchPage(Integer minPrice) {
        WebElement minPriceField = (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("min")));
        minPriceField.sendKeys("\b\b\b\b\b\b\b\b");
        minPriceField.sendKeys(minPrice.toString());
    }

    public void enterMaxPriceOnSearchPage(Integer maxPrice) {
        WebElement maxPriceField = (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("max-input_js")));
        maxPriceField.sendKeys("\b\b\b\b\b\b\b\b");
        maxPriceField.sendKeys(maxPrice.toString());
    }

    public List<WebElement> getToothbrushes() throws InterruptedException {
        Thread.sleep(3000);
        return (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.className("js--subcategory-product-item")));

    }

    public void addToCart(WebElement product) {
        product.findElement(By.className("add_to_cart")).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-basket__close-btn"))).click();
    }

    public void getCartPage() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("cart"))).click();
    }

    public String getCartText() {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("basket_storage"))).getText();
    }

}


