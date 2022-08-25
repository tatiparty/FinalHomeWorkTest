package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AccountPage {
    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    //private By courseItems = By.xpath("//div[@class = 'header2__logo']/following-sibling::div/div/div[@class = 'header2-menu__item-wrapper']/p");
    //private By courseItems = By.xpath("//div[@class = 'header2__logo']/following-sibling::div/div[contains(@class, 'header2-menu__item_dropdown')]/div[@class = 'header2-menu__dropdown']/div/a");
    //private By menuItem = By.xpath("//div[@id = 'categories_id']/div[contains(@class, 'course-categories__nav-box')]/div/div/a");
    //private By menuItem = By.xpath("//div[@id = 'categories_id']/div/div/div/a");
    private By menuItem = By.xpath("//div[@data-ac = 'course-categories__nav-item_active']/div[@id = 'categories_id']/div/div/div/a[contains(@href, '/categories/testing/')]"); //[contains(@href, '/categories/testing/')]

    public AccountPage openTestingCourse(){

        By css = By.cssSelector("a[href='/categories/testing/']");
        List<WebElement> courseElements = driver.findElements(css);
        WebElement element = courseElements.get(2);

       // WebElement element = driver.findElement(css);

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(menuItem));

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();" , element);

        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", menuItem);

        /*Actions action = new Actions(driver);
        action.moveToElement(webElement);
        action.perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        List<WebElement> courseElements = driver.findElements(courseItems);
        courseElements.get(6).click();*/

        return new AccountPage(driver);
    }

    private WebElement getElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
