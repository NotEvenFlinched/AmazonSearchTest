package amazon.search.autotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonSearchResultsPage {
    public AmazonSearchResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private WebDriver driver;
    private final String booksTitlesLocator = "//h2";
    private final String booksAuthorsLocator = "//span[contains(@class,'a-size-small')]//a[(contains(@class,'a-text-normal')) and not (contains(@class,'a-size-small'))]";
    private final String booksPriceLocator = "//li[@id='result_%s']//a/span[@class='a-offscreen']";
    private final String booksRatingLocator = "//li[@id='result_%s']//span[@class='a-icon-alt']";
    private final String isBestSellerLocator = "//li[@id='result_%s']//div[(contains(@class,'a-row')) and (contains(@class,'a-spacing-micro'))]";
    private final String booksCountLocator = "//li[contains(@id,'result_')]";

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

        List<WebElement> bookTitleElements = driver.findElements(By.xpath(booksTitlesLocator));
        for (WebElement bookTitle : bookTitleElements) {
            bookTitles.add(bookTitle.getText());
        }
        return bookTitles;
    }

    public List<String> getBooksAuthors() {
        List<String> authorNames = new ArrayList<>();

        List<WebElement> authorNameElements = driver.findElements(By.xpath(booksAuthorsLocator));
        for (WebElement authorName : authorNameElements) {
            authorNames.add(authorName.getText());
        }
        return authorNames;
    }

    public List<String> getBooksPrices() {
        List<String> bookPrices = new ArrayList<>();

        int booksCount = driver.findElements(By.xpath(booksCountLocator)).size();
        for (int i = 0; i < booksCount; i++) {
            WebElement bookPriceElement = driver.findElement(By.xpath(String.format(booksPriceLocator, i)));
            bookPrices.add(bookPriceElement.getAttribute("innerHTML"));
            System.out.println(bookPriceElement.getAttribute("innerHTML"));
        }
        return bookPrices;
    }

    public List<String> getBooksRatings() {
        List<String> bookRatings = new ArrayList<>();

        int booksCount = driver.findElements(By.xpath(booksCountLocator)).size();
        for (int i = 0; i < booksCount; i++) {
            List<WebElement> isRatingPresented = driver.findElements(By.xpath(String.format(booksRatingLocator, i)));
            if (isRatingPresented.size() != 0) {
                WebElement bookRatingElement = driver.findElement(By.xpath(String.format(booksRatingLocator, i)));
                bookRatings.add(bookRatingElement.getAttribute("innerHTML"));
            } else bookRatings.add("Rating is not presented for this book");
        }
        return bookRatings;
    }

    public List<String> isBestSeller() {
        List<String> isBestSeller = new ArrayList<>();

        int booksCount = driver.findElements(By.xpath(booksCountLocator)).size();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        for (int i = 0; i < booksCount; i++) {
            if (driver.findElements(By.xpath(String.format(isBestSellerLocator, i))).size() != 0) {
                isBestSeller.add("Is best seller - true");
            } else isBestSeller.add("Is best seller - false");
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return isBestSeller;
    }
}
