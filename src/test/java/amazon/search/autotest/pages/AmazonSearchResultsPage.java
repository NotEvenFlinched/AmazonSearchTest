package amazon.search.autotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class AmazonSearchResultsPage {
    public AmazonSearchResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(css = "#s-results-list-atf")
    public WebElement searchResults;

    public void checkBookAvailable(String bookTitle) {
        boolean isAvailable = false;
        for (String book : bookTitles) {
            if (book.contains(bookTitle)) {
                isAvailable = true;
                break;
            }
        }
        System.out.println("Is book available - " + isAvailable);
    }

    private List<String> bookTitles = new ArrayList<>();
    private List<String> authorNames = new ArrayList<>();

    public void getBooksInfo() {
        List<WebElement> bookTitleElements = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("ul#s-results-list-atf h2.a-size-medium.s-inline.s-access-title.a-text-normal")));
        for (WebElement bookTitle : bookTitleElements) {
            bookTitles.add(bookTitle.getText());
        }

        List<WebElement> authorNameElements = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("ul#s-results-list-atf span.a-size-small.a-color-secondary>a.a-link-normal.a-text-normal")));
        for (WebElement authorName : authorNameElements) {
            authorNames.add(authorName.getText());
        }

//        List<WebElement> links = searchResults.findElements(By.tagName("li"));

//        if (links.size() > 0) {
//            String price;
//            for (int i = 0; i < links.size(); i++) {
//            price = links.get(i).findElement(By.cssSelector("span.sx-price-whole")).getText()+","+links.get(i).findElement(By.cssSelector("sup.sx-price-fractional")).getText()+"$";
//                System.out.println(price);
//
//                if (links.get(i).findElements(By.cssSelector("span.a-badge-text")).size() > 0) {
//                    System.out.println("Is best seller - yes");
//                } else System.out.println("Is best seller - no");
//            }
//        }
    }
}
