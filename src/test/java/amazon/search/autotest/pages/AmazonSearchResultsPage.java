package amazon.search.autotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    private WebDriver driver;

    public void checkBookAvailable(String bookTitle) {
        boolean isAvailable = false;
        for (String book : getBooksTitles()) {
            if (book.contains(bookTitle)) {
                isAvailable = true;
                break;
            }
        }
        System.out.println("Is book available - " + isAvailable);
    }

    public List<String> getBooksTitles() {
        List<String> bookTitles = new ArrayList<>();

        List<WebElement> bookTitleElements = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("ul#s-results-list-atf h2.a-size-medium.s-inline.s-access-title.a-text-normal")));
        for (WebElement bookTitle : bookTitleElements) {
            bookTitles.add(bookTitle.getText());
        }
        return bookTitles;
    }

    public List<String> getBooksAuthors() {
        List<String> authorNames = new ArrayList<>();
        List<WebElement> authorNameElements = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("ul#s-results-list-atf span.a-size-small.a-color-secondary>a.a-link-normal.a-text-normal")));
        for (WebElement authorName : authorNameElements) {
            authorNames.add(authorName.getText());
        }
        return authorNames;
    }
}
