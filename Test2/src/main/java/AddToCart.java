import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddToCart {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
            driver.get("https://rozetka.com.ua/");

            String query = "macbook pro";

            By categoryResult = By.cssSelector("div ul.categories-filter__list.ng-star-inserted a");
            By searchResult = By.cssSelector("ul li a.goods-tile__picture");
            By addToCart = By.cssSelector("app-buy-button button.buy-button.button_color_green");
            By paragraph = By.cssSelector("ul.product-statuses li p");

            WebElement rozetkaSearchElement = driver.findElement(By.cssSelector("input.search-form__input"));
            rozetkaSearchElement.clear();
            rozetkaSearchElement.sendKeys(query);

            WebElement searchButton = driver.findElement(By.cssSelector("button.search-form__submit"));
            searchButton.click();

            WebDriverWait wait = new WebDriverWait(driver, 7);
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(categoryResult, 0));

            List<WebElement> ulCategories = driver.findElements(categoryResult);
            ulCategories.get(0).click();

            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchResult, 0));

            List<WebElement> ulProduct = driver.findElements(searchResult);
            ulProduct.get(0).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(paragraph));
            WebElement pInUl = driver.findElement(paragraph);
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(paragraph)).perform();

            wait.until(ExpectedConditions.elementToBeClickable(addToCart));
            WebElement buyButton = driver.findElement(addToCart);
            buyButton.click();

            driver.quit();
        } finally {
            driver.quit();

        }


    }
}
