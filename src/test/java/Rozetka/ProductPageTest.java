package Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

public class ProductPageTest extends BaseTest{
    By addToCartButton = By.cssSelector("app-buy-button button.buy-button.button_color_green");
    By someProduct = By.cssSelector("ul li[data-goods-id]");
    By monitorsCategory = By.cssSelector("ul.menu__hidden-list.ng-star-inserted li a[href$='monitors/c80089/']");
    By categorySearchResult = By.cssSelector("ul li a.goods-tile__picture");
    By sellStatusUnavailable = By.cssSelector("li a[href$='sell_status=unavailable/']");
    WebDriverWait wait;

    @Test(groups = {"alltest", "positivetest"})
    public void checkBuyButtonWithAvailableProduct() {
        wait = new WebDriverWait (driver, 7);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(someProduct, 0));
        List<WebElement> ulSomeProducts = driver.findElements(someProduct);
        ulSomeProducts.get(0).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        WebElement buyButton = driver.findElement(addToCartButton);
        buyButton.click();
    }

    @Test(groups = {"alltest", "negativetest"})
    public void checkUnavailableProductIsNotClickable(){
        wait = new WebDriverWait (driver, 5);

        WebElement catalogue = driver.findElement(By.cssSelector("button.button.button--medium.menu__toggle"));
        catalogue.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(monitorsCategory));
        WebElement monitorCategoryButton = driver.findElement(monitorsCategory);
        monitorCategoryButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(sellStatusUnavailable));
        WebElement sellStatusUnavailableButton = driver.findElement(sellStatusUnavailable);
        sellStatusUnavailableButton.click();

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(categorySearchResult, 0));
        List<WebElement> ulProduct = driver.findElements(categorySearchResult);
        wait.until(ExpectedConditions.elementToBeClickable(ulProduct.get(0)));
        ulProduct.get(0).click();

    }

}
