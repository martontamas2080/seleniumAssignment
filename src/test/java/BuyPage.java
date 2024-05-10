import org.openqa.selenium.WebDriver;

public class BuyPage extends PageBase {

    public BuyPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://brickset.com/buy");
    }

    @Override
    protected String getPageTitle() {
        return "Shopping for LEGO? | Brickset";
    }
}