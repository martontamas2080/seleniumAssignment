import org.openqa.selenium.WebDriver;


public class MySetsPage extends PageBase {

    public MySetsPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://brickset.com/mycollection");
    }

    @Override
    protected String getPageTitle() {
        return "Your Collection | Brickset";
    }
}