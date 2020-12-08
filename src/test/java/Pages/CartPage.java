package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCheckout() {
        (new WebDriverWait(driver,10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("next-basket-step-button__js"))).click();
    }
    public  void getDeliveryPage() {
        (new WebDriverWait(driver,10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("type4"))).click();
        (new WebDriverWait(driver,10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("for_delivery_type_0"))).click();
        (new WebDriverWait(driver,10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.id("delivery_save"))).click();
    }

    public int getProductPrice() {
        return  Integer.parseInt((new WebDriverWait(driver,10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("js--order-amount-without-discount__amount-num"))).getText());
    }
    public int getPriceWithDelivery() {
        return Integer.parseInt((new WebDriverWait(driver,10).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("js--order-amount__amount-num")))).getText());
    }

    public int getDeliveryPrice() {
        return Integer.parseInt((new WebDriverWait(driver,10).
                until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//tr[./td[contains(text(), \"Доставка\")]]"))))).
                findElement(By.className("num")).getText());
    }

    public void changeAmountOfProducts(Integer amount) throws InterruptedException{
        WebElement productAmount = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("product_amount_control")));
        productAmount.sendKeys("\b\b\b\b\b\b");
        productAmount.sendKeys(amount.toString());
        Thread.sleep(2000);
    }

    public void removeProductsInCart(){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("remove_all"))).click();
    }
}
