import org.openqa.selenium.WebDriver;

public class ForumPage extends PageBase {

    public ForumPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://brickset.com/forum");
    }

    @Override
    protected String getPageTitle() {
        return "Join our communities on Discord and Facebook | Brickset";
    }
}