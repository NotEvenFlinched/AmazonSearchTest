package amazon.search.autotest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonMainPage {
    public AmazonMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchField;

    @FindBy(className = "nav-input")
    private WebElement searchButton;

    public void inputSearchTerm(String bookSearchTerm) {
        searchField.sendKeys(bookSearchTerm);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

}
