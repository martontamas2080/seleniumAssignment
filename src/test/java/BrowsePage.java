import org.openqa.selenium.WebDriver;

public class BrowsePage extends PageBase {

    public BrowsePage(WebDriver driver) {
        super(driver);
        this.driver.get("https://brickset.com/browse");
    }    

    @Override
    protected String getPageTitle() {
        return "Browse | Brickset";
    }

    
}
