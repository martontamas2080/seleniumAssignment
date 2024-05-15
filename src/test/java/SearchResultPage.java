import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


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
