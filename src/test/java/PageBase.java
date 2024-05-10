import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;


class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private By loginButton = By.xpath("//a[@class=\"loginLink\"]");
    private By usernameField = By.xpath("//input[@id=\"ctl00_mainContent_username\"]");
    private By passwordField = By.xpath("//input[@id=\"ctl00_mainContent_password\"]");
    private By loginWithCredentialsButton = By.xpath("//input[@value=\"Login\"]");

    private By logoutButton = By.xpath("//li//a[@href=\"/logout\"]");

    private By darkModeSwitch = By.xpath("//li//a[@href=\"#\"]");

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

        //return new PageBase(this.driver);
    }

    protected void logout() {

        this.waitAndReturnElement(logoutButton).click();

        //return new PageBase(this.driver);
    }

    protected PageBase switchDarkMode() {
        this.waitAndReturnElement(darkModeSwitch).click();

        return new PageBase(this.driver);
    }

    protected String getPageTitle() {
        return "PageBase";
    }

    protected void autoConsentCookies() {
        this.waitAndReturnElement(cookieButton).click();

        /*
        Map<String, String> consentValues = new HashMap<>();
        consentValues.put("euconsent-v2", "CP-WLQAP-WLQAAKA1AENDgCsAP_AAEPAAAwIg1NX_H__bW9r8X7_aft0eY1P9_j77sQxBhfJE-4F3LvW_JwXx2E5NF36tqoKmRoEu3ZBIUNlHJHUTVmwaogVryHsakWcoTNKJ6BkkFMRM2dYCF5vm4tjeQKY5_p_d3fx2D-t_dv839zzz8VHn3e5fue0-PCdU5-9Dfn9fRfb-9IP9_78v8v8_l_rk2_eT13_pcvr_D--f_87_XW-9_cAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        consentValues.put("addtl_consent", "1~43.3.9.6.9.13.6.4.15.9.5.2.11.1.7.1.3.2.10.33.4.6.9.17.2.9.20.7.20.5.20.9.2.1.4.40.4.14.9.3.10.6.2.9.6.6.9.8.33.5.3.1.27.1.17.10.9.1.8.6.2.8.3.4.146.65.1.17.1.18.25.35.5.18.9.7.41.2.4.18.24.4.9.6.5.2.14.18.7.3.2.2.8.28.8.6.3.10.4.20.2.17.10.11.1.3.22.16.2.6.8.6.11.6.5.33.11.8.11.28.12.1.5.2.17.9.6.40.17.4.9.15.8.7.3.12.7.2.4.1.7.12.13.22.13.2.6.8.10.1.4.15.2.4.9.4.5.4.7.13.5.15.17.4.14.10.15.2.5.6.2.2.1.2.14.7.4.8.2.9.10.18.12.13.2.18.1.1.3.1.1.9.7.2.16.5.19.8.4.8.5.4.8.4.4.2.14.2.13.4.2.6.9.6.3.2.2.3.5.2.3.6.10.11.6.3.19.8.3.3.1.2.3.9.19.26.3.10.13.4.3.4.6.3.3.3.3.1.1.1.6.11.3.1.1.11.6.1.10.5.8.3.2.2.4.3.2.2.7.15.7.14.1.3.3.4.5.4.3.2.2.5.5.1.2.9.7.9.1.5.3.7.10.11.1.3.1.1.2.1.3.2.6.1.12.8.1.3.1.1.2.2.7.7.1.4.3.6.1.2.1.4.1.1.4.1.1.2.1.8.1.7.4.3.3.3.5.3.15.1.15.10.28.1.2.2.12.3.4.1.6.3.4.7.1.3.1.4.1.5.3.1.3.4.1.5.2.3.1.2.2.6.2.1.2.2.2.4.1.1.1.2.2.1.1.1.1.2.1.1.1.2.2.1.1.2.1.2.1.7.1.7.1.1.1.1.2.1.4.2.1.1.9.1.6.2.1.6.2.3.2.1.1.1.2.5.2.4.1.1.2.2.1.1.7.1.2.2.1.2.1.2.3.1.1.2.4.1.1");
        
        for (Map.Entry<String, String> entry : consentValues.entrySet()) {
            this.driver.manage().addCookie(new Cookie(entry.getKey(), entry.getValue(), ".brickset.com", "/", null, true, true));
        }

        this.driver.navigate().refresh();
        */
    }
   
}
