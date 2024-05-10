import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class SearchResultPage extends PageBase {

    private By setsButton = By.xpath("//h1//a[contains(@href, '/sets?query=')]");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public SearchResultPage searchResultsGoToSetsPage() {
        this.waitAndReturnElement(setsButton).click();

        return new SearchResultPage(this.driver);
    }
           
}
