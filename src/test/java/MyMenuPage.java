import org.openqa.selenium.WebDriver;

public class MyMenuPage extends PageBase {

    public MyMenuPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://brickset.com/mymenu");
    }

    @Override
    protected String getPageTitle() {
        return "Your Menu | Brickset";
    }
}