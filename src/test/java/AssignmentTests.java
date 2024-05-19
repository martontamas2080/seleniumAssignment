import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.By;
import java.util.*;  

import java.net.URL;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;


public class AssignmentTests {
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


        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");

        configFile = new ConfigReader("config.properties");
        
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
    }

    @Test
    public void simpleStaticPageLoadTest() {
        MainPage mainPage = new MainPage(this.driver);
        String bodyText = mainPage.getBodyText();

        Assert.assertEquals(mainPage.getPageTitle(),driver.getTitle()); 
        Assert.assertTrue(bodyText.contains("Brickset"));
    }


    @Test 
    public void multipleStaticPageTestsUsingConfigArray() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
        pages = configFile.getPageArray();

        for (String page : pages) {

            Class<?> cls = Class.forName(page);
            Constructor<?> constructor = cls.getConstructor(WebDriver.class);
            Object instance = constructor.newInstance(this.driver);
            PageBase pageInstance = (PageBase) instance;
            Assert.assertEquals(pageInstance.getPageTitle(), driver.getTitle());
        }
    }


    @Test
    public void loginTest() {
        MainPage mainPage = new MainPage(this.driver);

        loginWithCredentials(mainPage);

        String bodyText = mainPage.getBodyText();
        Assert.assertTrue(bodyText.contains("LOG OUT"));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scroll(0, 250)");
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


    @Test
    public void searchTest() {
        MainPage mainPage = new MainPage(this.driver);

        SearchResultPage searchResultPage = mainPage.search("Star Wars");
        String bodyText = searchResultPage.getBodyText();

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
        Assert.assertEquals(browsePage.getPageTitle(),pageTitle);   

        driver.navigate().back();
        Assert.assertEquals(mainPage.getPageTitle(),driver.getTitle()); 
    }


    @Test
    public void pageTitleTest() {
        MainPage mainPage = new MainPage(this.driver);

        String pageTitle = driver.getTitle();
        Assert.assertEquals(mainPage.getPageTitle(),pageTitle); 
    }


    @Test
    public void loginTestFailWithRandomInput() {
        MainPage mainPage = new MainPage(this.driver);

        Random rand = new Random();

        int randomUsername = rand.nextInt(10);
        int randomPassword = rand.nextInt(10);

        mainPage.login(Integer.toString(randomUsername), Integer.toString(randomPassword));

        String bodyText = mainPage.getBodyText();


        Assert.assertTrue(bodyText.contains("Sorry"));
    }


    @Test
    public void logoutTest() {
        MainPage mainPage = new MainPage(this.driver);

        mainPage.login("test_user_sqat", "8rTU1bHS2i");

        String bodyText = mainPage.getBodyText();
        

        Assert.assertTrue(bodyText.contains("LOG OUT"));

        mainPage.logout();
        bodyText = mainPage.getBodyText();
        Assert.assertTrue(bodyText.contains("LOG IN"));
    }


    @Test 
    public void mainPageNavigateBuyTest() {
        MainPage mainPage = new MainPage(this.driver);

        BuyPage buyPage = mainPage.goBuyPage();
        Assert.assertEquals(buyPage.getPageTitle(),driver.getTitle()); 
    }


    @Test 
    public void mainPageNavigateMoreTest() {
        MainPage mainPage = new MainPage(this.driver);

        MorePage morePage = mainPage.goMorePage();
        Assert.assertEquals(morePage.getPageTitle(),driver.getTitle()); 
    }


    @Test 
    public void mainPageNavigateMySetsTest() {
        MainPage mainPage = new MainPage(this.driver);

        MySetsPage mySetsPage = mainPage.goMySetsPage();
        Assert.assertEquals(mySetsPage.getPageTitle(),driver.getTitle()); 
    }


    @Test 
    public void mainPageNavigateMyMenuTest() {
        MainPage mainPage = new MainPage(this.driver);

        MyMenuPage myMenuPage = mainPage.goMyMenuPage();
        Assert.assertEquals(myMenuPage.getPageTitle(),driver.getTitle()); 
    }


    @Test 
    public void mainPageNavigateForumTest() {
        MainPage mainPage = new MainPage(this.driver);

        ForumPage forumPage = mainPage.goForumPage();
        Assert.assertEquals(forumPage.getPageTitle(),driver.getTitle()); 
    }

    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
