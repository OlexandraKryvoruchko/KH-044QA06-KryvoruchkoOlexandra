package Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest extends BaseTest{
    By searchProducts = By.cssSelector("section ul.catalog-grid  li");
    By titleText = By.cssSelector("li span.goods-tile__title");
    By textSearchResult = By.cssSelector("div.rz-search-result-qnty");
    String query = "macbook pro";
    WebDriverWait wait;

    @BeforeMethod
    public List<WebElement> searchResult() {

        WebElement searchElement = driver.findElement(By.cssSelector("input.search-form__input"));
        searchElement.clear();
        searchElement.sendKeys(query);
        searchElement.sendKeys(Keys.ENTER);

        WebElement laptopCategory = driver.findElement(By.cssSelector("li a[href ='/search/?section_id=80004&text=macbook%2Bpro']"));
        laptopCategory.click();

        wait = new WebDriverWait (driver, 7);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchProducts, 0));

        List<WebElement> ulProducts = driver.findElements(searchProducts);
        return ulProducts;
    }

    @Test(groups = {"alltest", "positivetest"})
    public void checkTitle() {
        for (WebElement product : searchResult()) {
            WebElement title = product.findElement(titleText);
            String textTitle = title.getText();
            if (textTitle.toLowerCase().contains(query)) {
                System.out.println("[True]");
            } else {
                System.out.println("[False]");
            }
        }
    }

    @Test(groups = {"alltest", "positivetest"})
    public void checkProductQuantity(){
        Integer number = searchResult().size();
        String productNumber = Integer.toString(number);

        WebElement quantity = driver.findElement(textSearchResult);
        String productQuantity = quantity.getText();
        if (productQuantity.toLowerCase().contains(productNumber)) {
                System.out.println("[True]");
            } else {
                System.out.println("[False]");
            }
    }

}

