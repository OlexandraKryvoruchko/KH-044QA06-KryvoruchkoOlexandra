import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(9000, TimeUnit.MILLISECONDS);
            webDriver.get("https://rozetka.com.ua/");

            WebElement rozetkaSearchElement = webDriver.findElement(By.cssSelector("input.search-form__input"));

            rozetkaSearchElement.clear();
            rozetkaSearchElement.sendKeys("Джуди Муди");

            WebElement searchButton = webDriver.findElement(By.cssSelector("button.search-form__submit"));
            searchButton.click();

            List<WebElement> ulProduct = webDriver.findElements(By.cssSelector("section.content ul li a"));
            ulProduct.get(0).click();

            WebElement buyButton = webDriver.findElement(By.cssSelector("app-buy-button.toOrder.ng-star-inserted"));
            buyButton.click();
            webDriver.quit();

        } finally {
            webDriver.quit();
        }

    }
}
