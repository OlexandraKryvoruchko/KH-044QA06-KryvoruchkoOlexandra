import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddToCart {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
            driver.get("https://rozetka.com.ua/");

            String productsList = "section.content ul li a.goods-tile__picture.ng-star-inserted";
            String laptops = "a[href='/search/?section_id=80004&text=ноутбук%2Bmacbook%2Bgold']"; 
            String buttonBuy = "app-buy-button.toOrder.ng-star-inserted";

            WebDriverWait wait = new WebDriverWait(driver, 5);

            WebElement searchElement = driver.findElement(By.cssSelector("input.search-form__input"));
            searchElement.clear();
            searchElement.sendKeys("ноутбук macbook gold");

            WebElement searchButton = driver.findElement(By.cssSelector("button.search-form__submit"));
            searchButton.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(laptops)));

            WebElement laptopsCategory = driver.findElement(By.cssSelector(laptops));
            laptopsCategory.click();

            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(productsList), 0));

            List<WebElement> ulProducts = driver.findElements(By.cssSelector(productsList));
            ulProducts.get(0).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(buttonBuy)));

            WebElement buyButton = driver.findElement(By.cssSelector(buttonBuy));
            buyButton.click();

            driver.quit();

        } finally {
            driver.quit();
        }
    }
}
