import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;


class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private By loginButton = By.xpath("//a[@class=\"loginLink\"]");
    private By usernameField = By.xpath("//input[@id=\"ctl00_mainContent_username\"]");
    private By passwordField = By.xpath("//input[@id=\"ctl00_mainContent_password\"]");
    private By loginWithCredentialsButton = By.xpath("//input[@value=\"Login\"]");
    private By logoutButton = By.xpath("//li//a[@href=\"/logout\"]");
    private By cookieButton = By.xpath("//div//button[@mode=\"primary\"]");

    protected PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    
    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    } 

    
    protected String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(By.tagName("body"));
        return bodyElement.getText();
    }


    protected void login(String username, String password) {

        this.waitAndReturnElement(loginButton).click();

        this.waitAndReturnElement(usernameField).click();
        this.waitAndReturnElement(usernameField).sendKeys(username);
        this.waitAndReturnElement(passwordField).click();
        this.waitAndReturnElement(passwordField).sendKeys(password);
        this.waitAndReturnElement(loginWithCredentialsButton).click();
    }


    protected void logout() {

        this.waitAndReturnElement(logoutButton).click();
    }


    protected String getPageTitle() {
        return "PageBase";
    }


    protected void consentCookies() {
        this.waitAndReturnElement(cookieButton).click();
    }
   
}
