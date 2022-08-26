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

    private By menuItem = By.xpath("//div[@data-ac = 'course-categories__nav-item_active']/div[@id = 'categories_id']/div/div/div/a[contains(@href, '/categories/testing/')]"); //[contains(@href, '/categories/testing/')]

    public CoursePage openTestingCourse(){

        By css = By.cssSelector("a[href='/categories/testing/']");

        Actions action = new Actions(driver);
        action.moveToElement(getElement(menuItem));
        action.perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        List<WebElement> courseElements = driver.findElements(css);
        WebElement element = courseElements.get(2);

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()" , element);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();" , element);

        return new CoursePage(driver);
    }

    private WebElement getElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
