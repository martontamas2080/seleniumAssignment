import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

class MainPage extends PageBase {

    private By footerBy = By.className("//footer[@role=\"contentinfo\"]");
    private By searchBar = By.xpath("//input[@type=\"text\"]");
    private By searchButton = By.xpath("//input[@value=\"Go\"]");

    private By browseMenuButton = By.xpath("//ul//a[@href=\"/browse\"]");
    private By buyMenuButton = By.xpath("//ul//a[@href=\"/buy\"]");
    private By collectionMenuButton = By.xpath("//ul//a[@href=\"/mycollection\"]");
    private By forumMenuButton = By.xpath("//ul//a[@href=\"/forum\"]");
    private By moreMenuButton = By.xpath("//ul//a[@href=\"/more\"]");
    private By myMenuButton = By.xpath("//ul//a[@href=\"/mymenu\"]");

    private By profileButton = By.xpath("//ul//li//a[@href=\"/profile\"]");


    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://brickset.com/");

        this.autoConsentCookies();
    }    

    
    public String getFooterText() {
        return this.waitAndReturnElement(footerBy).getText();
    }
    
    public SearchResultPage search(String searchQuery) {

        this.waitAndReturnElement(searchBar).sendKeys(searchQuery);

        this.waitAndReturnElement(searchButton).click();

        return new SearchResultPage(this.driver);
    }

    public BrowsePage goBrowseMenuPage() {
        this.waitAndReturnElement(browseMenuButton).click();
        return new BrowsePage(this.driver);
    }

    public ProfilePage goProfilePage() {
        this.waitAndReturnElement(profileButton).click();
        return new ProfilePage(this.driver);
    }

    public BuyPage goBuyPage() {
        this.waitAndReturnElement(buyMenuButton).click();
        return new BuyPage(this.driver);
    }

    public MySetsPage goMySetsPage() {
        this.waitAndReturnElement(collectionMenuButton).click();
        return new MySetsPage(this.driver);
    }

    public ForumPage goForumPage() {
        this.waitAndReturnElement(forumMenuButton).click();
        return new ForumPage(this.driver);
    }

    public MorePage goMorePage() {
        this.waitAndReturnElement(moreMenuButton).click();
        return new MorePage(this.driver);
    }

    public MyMenuPage goMyMenuPage() {
        this.waitAndReturnElement(myMenuButton).click();
        return new MyMenuPage(this.driver);
    }

    @Override
    protected String getPageTitle() {
        return "Home | Brickset";
    }

}
