package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class AccountPage {
    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    //private By courseItems = By.xpath("//div[@class = 'header2__logo']/following-sibling::div/div/div[@class = 'header2-menu__item-wrapper']/p");
    private By menuItem = By.xpath("//div[@id = 'categories_id']/div[contains(@class, 'course-categories__nav-box')]/div/div/a");

    public AccountPage openTestingCourse(){
        List<WebElement> webElementsForCourse = driver.findElements(menuItem);
        webElementsForCourse.get(6).click();
        /*for(WebElement element: webElementsForCourse){
            String checkCourse = element.getText();
            if(checkCourse == "Тестирование"){
                //Actions action = new Actions(driver);
                //action.moveToElement(element);
                //action.perform();
                //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                element.click();
            }
        }*/
        return new AccountPage(driver);
    }

    /*private WebElement getElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }*/
}
