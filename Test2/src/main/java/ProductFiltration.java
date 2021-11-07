import jdk.jfr.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductFiltration {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String categories = "ul.menu-categories.ng-star-inserted li a.menu-categories__link";
        String laptopsComputersCategory = "ul.portal-grid.portal-grid_type_1_6 li a.tile-cats__heading";
        String  filterResult= "section.content ul li a.goods-tile__picture.ng-star-inserted";

        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
            driver.get("https://rozetka.com.ua/");

            WebElement catalogButton = driver.findElement(By.cssSelector("button.button#fat-menu"));
            catalogButton.click();

            WebDriverWait wait = new WebDriverWait(driver, 7);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(categories)));

            List<WebElement> ulCategories = driver.findElements(By.cssSelector(categories));
            ulCategories.get(0).click();

            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(laptopsComputersCategory), 0));

            List<WebElement> ulLaptopsComputersCategory = driver.findElements(By.cssSelector(laptopsComputersCategory));
            ulLaptopsComputersCategory.get(2).click();

            WebElement customerFilter = driver.findElement(By.cssSelector("div.scrollbar__layout ul li a label[for='Rozetka']"));
            customerFilter.click();

            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(filterResult), 0));

            List<WebElement> ulProducts = driver.findElements(By.cssSelector(filterResult));
            ulProducts.get(0);

            driver.quit();

        } finally {
            driver.quit();
        }

        }
}
