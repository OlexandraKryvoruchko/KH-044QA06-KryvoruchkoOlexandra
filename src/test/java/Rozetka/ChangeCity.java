package Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class ChangeCity extends BaseTest{
    By menu = By.cssSelector("button.header__button use[href='#icon-menu']");
    By city = By.cssSelector("button.city-toggle.button.button--small.button--white");
    By inputField = By.cssSelector("div input.autocomplete__input.ng-untouched.ng-pristine.ng-valid");
    By applyCity = By.cssSelector("div.header-location__footer button");
    By cityList = By.cssSelector("div ul.autocomplete__list.dialog li");
    WebDriverWait wait;

    @DataProvider(name = "cityTrue")
    public Object [][] getTrueCityName(){
        return new Object[][] {
                {"Харьков"},
                {"ХАРЬКОВ"},
                {"харьков"}
        };
    }

    @DataProvider(name = "cityFalse")
    public Object[][] getFalseCityName(){
        return new Object[][]{
                {"  "},
                {"/."},
                {"059"}
        };
    }

    @BeforeMethod
    public void  findCityInputField() {

        wait = new WebDriverWait(driver,7);

        wait.until(ExpectedConditions.visibilityOfElementLocated(menu));
        WebElement menuButton = driver.findElement(menu);
        menuButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(city));
        WebElement cityButton = driver.findElement(city);
        cityButton.click();
    }


    @Test(dataProvider = "cityTrue", groups = {"alltest", "positivetest"})
    public void checkApplyButtonIsClickableWithInputWithValidData(String cityTrue){

        wait.until(ExpectedConditions.visibilityOfElementLocated(inputField));
        WebElement inputCity = driver.findElement(inputField);
        inputCity.clear();
        inputCity.sendKeys(cityTrue);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(cityList,0));
        List<WebElement> dropDownCityList = driver.findElements(cityList);
        dropDownCityList.get(0).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(applyCity));
        WebElement applyButton = driver.findElement(applyCity);
        applyButton.click();
    }

    @Test(dataProvider = "cityFalse", groups = {"alltest", "negativetest"})
    public void  checkApplyButtonIsNotClickableWithInputWithInvalidData(String cityFalse){

        wait.until(ExpectedConditions.visibilityOfElementLocated(inputField));
        WebElement inputCity = driver.findElement(inputField);
        inputCity.clear();
        inputCity.sendKeys(cityFalse);

        wait.until(ExpectedConditions.visibilityOfElementLocated(applyCity));
        WebElement applyButton = driver.findElement(applyCity);
        applyButton.click();
    }

    }
