import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


public class ProfilePage extends PageBase {

    protected By editProfileButton = By.xpath("//ul//li//a[@href=\"/profile/edit\"]");
    protected By contactCheckbox = By.xpath("//div//input[@id=\"ctl00_mainContent_Repeater2_ctl00_allowContact\"]");
    protected By saveButton = By.xpath("//div//input[@value=\"Save\"]");

    public ProfilePage(WebDriver driver) {
        super(driver);
        this.driver.get("https://brickset.com/profile");
    }    

    public void editProfile() {
        this.waitAndReturnElement(editProfileButton).click();
    }

    public void changeAllowContactFromMembers() {
        this.waitAndReturnElement(contactCheckbox).click();
        this.waitAndReturnElement(saveButton).click();
    }

    @Override
    protected String getPageTitle() {
        return "My Profile | Brickset";
    }
}
