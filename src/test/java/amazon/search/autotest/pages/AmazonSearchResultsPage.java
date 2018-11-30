package amazon.search.autotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AmazonSearchResultsPage {
    public AmazonSearchResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

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

        List<WebElement> bookTitleElements = driver.findElements(By.xpath("//h2"));
        for (WebElement bookTitle : bookTitleElements) {
            bookTitles.add(bookTitle.getText());
        }
        return bookTitles;
    }

    public List<String> getBooksAuthors() {
        List<String> authorNames = new ArrayList<>();
        List<WebElement> authorNameElements = driver.findElements(By.xpath("//span[contains(@class,'a-size-small')]//a[(contains(@class,'a-text-normal')) and not (contains(@class,'a-size-small'))]"));
        for (WebElement authorName : authorNameElements) {
            authorNames.add(authorName.getText());
        }
        return authorNames;
    }
}
