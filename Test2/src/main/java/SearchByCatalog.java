import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchByCatalog {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
            driver.get("https://rozetka.com.ua/");

            By searchResult = By.cssSelector("ul li a.goods-tile__heading");
            By monitorsCategory = By.cssSelector("ul.menu__hidden-list.ng-star-inserted li a[href$='monitors/c80089/']");
            By rozetkaSeller = By.cssSelector("a label[for='Rozetka']");

            WebElement catalogue = driver.findElement(By.cssSelector("button.button--medium.menu__toggle"));
            catalogue.click();

            WebDriverWait wait = new WebDriverWait (driver, 7);

            wait.until(ExpectedConditions.visibilityOfElementLocated(monitorsCategory));
            WebElement monitorCategoryButton = driver.findElement(monitorsCategory);
            monitorCategoryButton.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(rozetkaSeller));
            WebElement rozetkaCustomerCheckbox = driver.findElement(rozetkaSeller);
            rozetkaCustomerCheckbox.click();

            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchResult,0));
            List<WebElement> ulProduct = driver.findElements(searchResult);
            ulProduct.get(0).click();

            driver.quit();

        } finally {
            driver.quit();
        }
    }
}
