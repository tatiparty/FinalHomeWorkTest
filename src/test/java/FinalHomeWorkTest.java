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
import java.nio.file.Files;
import java.nio.file.Paths;
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
        //Войти в Личный кабинет, перейти в Курсы->Тестирование
        AccountPage firstTitle = new MainPage(driver).
                openUrl(url).
                goToAuth().
                auth(login, password).
                openTestingCourse();

        //Assert.assertEquals(firstTitle, "Данные успешно сохранены");


        logger.info(driver.manage().getCookies());
    }

}
