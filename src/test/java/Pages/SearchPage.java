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
        WebElement beautyAndhealthPage = (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".link_side-menu.link_active")));
        beautyAndhealthPage.click();
        WebElement toothbrushesAndAcsPage = (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.id("category_id_663")));
        toothbrushesAndAcsPage.click();
        WebElement toothbrushesOnlyPage = (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.id("category_id_216")));
        toothbrushesAndAcsPage.click();

    }

    public void enterMinPriceOnSearchPage(int minPrice) {
        WebElement minPriceField = (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("min-input_js")));
        minPriceField.sendKeys(String.valueOf(minPrice));
        minPriceField.submit();
    }

    public void enterMaxPriceOnSearchPage(int maxPrice) {
        WebElement maxPriceField = (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("max-input_js")));
        maxPriceField.sendKeys(String.valueOf(maxPrice));
        maxPriceField.submit();
    }

    public ArrayList<Integer> getPricesOfToothbrushes(){
        ArrayList<Integer> pricesToReturn = new ArrayList<Integer>();
        List<WebElement> prices = (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.className("subcategory-product-item__price subcategory-product-item__price_standart")));
        for (WebElement price: prices) {
            pricesToReturn.add(Integer.parseInt(price.getText()));
        }
        return  pricesToReturn;

        }
    }

