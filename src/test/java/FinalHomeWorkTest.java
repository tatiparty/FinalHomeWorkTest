import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AccountPage;
import pages.MainPage;
import pages.CoursePage;
import webDriverFactory.WebDriverFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class FinalHomeWorkTest {

    private String url = System.getProperty("baseURL", "https://otus.ru");

    private String login;
    private String password;
    public String browser;

    private Logger logger = LogManager.getLogger(Logger.class);

    private WebDriver driver;

    @Before
    public void setUp(){
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get("config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        login = properties.getProperty("login");
        password = properties.getProperty("password");
        browser = properties.getProperty("browser");

        driver = WebDriverFactory.create(browser.toUpperCase());
    }

    /*@After
    public void setDown(){
        if ( driver != null)
            driver.quit();
    }*/

    @Test
    public void testOtusSite(){
        //Войти в Личный кабинет, перейти в Курсы->Тестирование и проверить количество действующих курсов
        /*int activeCourses = new MainPage(driver).
                openUrl(url).
                goToAuth().
                auth(login, password).
                openTestingCourse().
                checkCourseCount();

        Assert.assertEquals(activeCourses, 11);*/

        //Перейти на карточку курса и проверить информацию по ней
        ArrayList<String> newCourseCard = new MainPage(driver).
                openUrl(url).
                goToAuth().
                auth(login, password).
                openTestingCourse().
                goToCourseCard().
                validateCourseName();

        ArrayList<String> goldenValues = new ArrayList<String>();
        goldenValues.add(0, "Java QA Engineer. Professional");
        goldenValues.add(1, "Автоматизация тестирования на Java продвинутого уровня");
        goldenValues.add(2, "4 месяца");
        goldenValues.add(3, "Online");

        Collections.sort(newCourseCard);
        Collections.sort(goldenValues);

        boolean outcome = newCourseCard.equals(goldenValues);
        Assert.assertTrue(outcome);

        logger.info(driver.manage().getCookies());
    }

}
