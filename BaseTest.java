package RozetkaTests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    @BeforeMethod
    public void setUpDriver(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        driver.get("https://rozetka.com.ua/");
    }

    @AfterMethod
    public void screenshot(){
        WebDriver driver = new ChromeDriver();
        driver.get("");
        File screenFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(screenFile, new File(""));
    }

    @AfterMethod
    public void browserQuit(){
        WebDriver driver = new ChromeDriver();
        driver.quit();
    }



}
