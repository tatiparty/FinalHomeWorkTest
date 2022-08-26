package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CoursePage {
    public CoursePage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    private By allCourses = By.xpath("//div[@class = 'lessons']/a");
    private By courseInDevelopment = By.xpath("//div[@class = 'lessons']/a/div/div[contains(text(), 'В разработке')]");
    private By courseName = By.xpath("//div[@class = 'course-header2__title']");
    private By courseDescription = By.xpath("//h1[@class = 'course-header2__subtitle']");
    private By courseDuration = By.xpath("//p[contains(text(), 'Длительность')]/../../following-sibling::div/div/p[@class]");
    private By courseFormat = By.xpath("//p[contains(text(), 'Формат')]/../../following-sibling::div/div/p[@class]");

    public int checkCourseCount(){
        List<WebElement> courses = driver.findElements(allCourses);
        List<WebElement> developingCourses = driver.findElements(courseInDevelopment);
        int allCoursesCount = courses.size();
        int devCoursesCount = developingCourses.size();
        int activeCourses = allCoursesCount - devCoursesCount;
        return activeCourses;
    }

    public CoursePage goToCourseCard(){
        List<WebElement> courses = driver.findElements(allCourses);
        WebElement element = courses.get(0);

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()" , element);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();" , element);
        return new CoursePage(driver);
    }

    public ArrayList<String> validateCourseName(){
        String name = getElement(courseName).getText();
        String description = getElement(courseDescription).getText();
        String duration = getElement(courseDuration).getText();
        String format = getElement(courseFormat).getText();

        ArrayList<String> cardData = new ArrayList<String>();
        cardData.add(0, name);
        cardData.add(0, description);
        cardData.add(0, duration);
        cardData.add(0, format);
        //name.matches("Java QA Engineer. Professional");
        return cardData;
    }

    /*public CoursePage validateCourseDuration(){
        String duration = getElement(courseDuration).getText();
        duration.matches("4 месяца");
        return new CoursePage(driver);
    }

    public CoursePage validateCourseDescription(){
        String description = getElement(courseDescription).getText();
        description.matches("Автоматизация тестирования на Java продвинутого уровня");
        return new CoursePage(driver);
    }

    public CoursePage validateCourseFormat(){
        String format = getElement(courseFormat).getText();
        format.matches("Ofline");
        return new CoursePage(driver);
    }*/


    private WebElement getElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /*public String fillAndSaveData(){
        enterToTextArea(getElement(firstName), "Тест");
        enterToTextArea(getElement(secondName), "Тест");
        enterToTextArea(getElement(blogName), "Тест");

        driver.findElement(By.xpath("//button[@name = 'continue']")).click();
        return driver.findElement(title).getText();
    }
    public CoursePage checkData(){
        checkTextInTextArea(getElement(firstName), "Тест");
        checkTextInTextArea(getElement(secondName), "Тест");
        checkTextInTextArea(getElement(blogName), "Тест");
        return new CoursePage(driver);
    }

    private void enterToTextArea(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }
    private void checkTextInTextArea(WebElement element, String expectedText){
        Assert.assertEquals(expectedText, element.getAttribute("value"));
    }

    private WebElement getElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }*/
}
