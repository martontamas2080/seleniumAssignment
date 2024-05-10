import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.Page;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;  

import java.net.URL;
import java.net.MalformedURLException;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;


public class FirstSeleniumTest {
    public WebDriver driver;
    public ConfigReader configFile;
    public String[] pages;

    public void loginWithCredentials(PageBase currentPage) {
        String username = configFile.getUsername();
        String password = configFile.getPassword();

        currentPage.login(username, password);
    }
    
    @Before
    public void setup()  throws MalformedURLException  {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");

        configFile = new ConfigReader("src/test/java/config.properties");
        
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);

        driver.manage().window().maximize();

    }




    @Test
    public void loginTest() {
        MainPage mainPage = new MainPage(this.driver);

        loginWithCredentials(mainPage);

        String bodyText = mainPage.getBodyText();
        Assert.assertTrue(bodyText.contains("LOG OUT"));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    @Test 
    public void changeProfileSettings() {
        MainPage mainPage = new MainPage(this.driver);

        loginWithCredentials(mainPage);

        String bodyText = mainPage.getBodyText();
        Assert.assertTrue(bodyText.contains("LOG OUT"));

        ProfilePage profile = mainPage.goProfilePage();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://brickset.com/profile"));
        profile.editProfile();
        currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://brickset.com/profile/edit"));

        profile.changeAllowContactFromMembers();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement saveInfo = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='info']//p[contains(text(),'Changes to your profile have been saved.')]")));

        Assert.assertTrue(saveInfo.getText().contains("Changes to your profile have been saved"));
        bodyText = mainPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Changes to your profile have been saved"));


    }
    /* 
    @Test
    public void searchTest() {
        MainPage mainPage = new MainPage(this.driver);

        SearchResultPage searchResultPage = mainPage.search("Star Wars");
        String bodyText = searchResultPage.getBodyText();
        System.out.println(bodyText);

        Assert.assertTrue(bodyText.contains("Search results"));
        Assert.assertTrue(bodyText.contains("Sets"));

        searchResultPage.searchResultsGoToSetsPage();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://brickset.com/sets?query"));

    }


    @Test
    public void historyTest() {
        MainPage mainPage = new MainPage(this.driver);

        String pageTitle = driver.getTitle();
        Assert.assertEquals(mainPage.getPageTitle(),pageTitle); 

        BrowsePage browsePage = mainPage.goBrowseMenuPage();
        pageTitle = driver.getTitle();
        Assert.assertEquals("Browse | Brickset",pageTitle);   

        driver.navigate().back();
        Assert.assertEquals("Home | Brickset",driver.getTitle()); 

    }



    @Test
    public void pageTitleTest() {
        MainPage mainPage = new MainPage(this.driver);

        String pageTitle = driver.getTitle();
        Assert.assertEquals(mainPage.getPageTitle(),pageTitle); 
    }

    
    @Ignore
    @Test 
    public void darkModeSwitchTest() {
        PageBase mainPage = new MainPage(this.driver);

        String bodyText = mainPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Dark"));
        PageBase newPage = mainPage.switchDarkMode();
        bodyText = newPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Light"));
    }

    @Test
    public void loginTestFailWithRandomInput() {
        MainPage mainPage = new MainPage(this.driver);

        Random rand = new Random();

        int randomUsername = rand.nextInt(10);
        int randomPassword = rand.nextInt(10);

        PageBase loggedInPage = mainPage.login(Integer.toString(randomUsername), Integer.toString(randomPassword));

        String bodyText = loggedInPage.getBodyText();
        System.out.println(bodyText);

        Assert.assertTrue(bodyText.contains("Sorry"));
    }

    @Test
    public void logoutTest() {
        MainPage mainPage = new MainPage(this.driver);

        PageBase loggedInPage = mainPage.login("test_user_sqat", "8rTU1bHS2i");

        String bodyText = loggedInPage.getBodyText();
        

        Assert.assertTrue(bodyText.contains("LOG OUT"));

        loggedInPage.logout();
        bodyText = loggedInPage.getBodyText();
        System.out.println(bodyText);
        Assert.assertTrue(bodyText.contains("LOG IN"));
    }



    @Test 
    public void multipleStaticPageTestsUsingArray()  {
        pages = new String[] {"MainPage", "BuyPage", "BrowsePage", "ForumPage"};

        for (String page : pages) {
            String className = page;

            Class<?> cls = Class.forName(className);

            Constructor<?> constructor = cls.getConstructor(String.class);

            Object instance = constructor.newInstance(this.driver);

            Assert.assertEquals(((PageBase) instance).getPageTitle(),driver.getTitle());
        }
    }
*/
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
