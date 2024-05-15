import org.openqa.selenium.WebDriver;


public class MorePage extends PageBase {

    public MorePage(WebDriver driver) {
        super(driver);
        this.driver.get("https://brickset.com/more");
    }

    @Override
    protected String getPageTitle() {
        return "More | Brickset";
    }
}