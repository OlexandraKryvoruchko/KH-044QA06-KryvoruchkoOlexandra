package Rozetka;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver = null;

    @BeforeMethod
    public void setUpDriver(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        driver.get("https://rozetka.com.ua/");
    }

   /* @AfterMethod
    public void screenshot(){
        File screenFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
        FileHandler.copy(screenFile, new File("C:\\Users\\admin\\Pictures\\Screenshots\\ScrTest3\\"));
    } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @AfterMethod
    public void browserQuit(){
        driver.quit();
    }
}
