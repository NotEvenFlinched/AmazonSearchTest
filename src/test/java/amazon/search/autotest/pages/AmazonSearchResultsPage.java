package amazon.search.autotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void getBooksInfo() {
        List<WebElement> links = searchResults.findElements(By.tagName("li"));

        if (links.size() > 0) {
            String title, author, price, rating, isBestSeller;

            for (int i = 0; i < links.size(); i++) {
                title = links.get(i).findElement(By.cssSelector("h2.a-size-medium.s-inline.s-access-title.a-text-normal")).getText();
                bookTitles.add(title);
//                System.out.println(title);
//
//                author = links.get(i).findElement(By.xpath("//span[@class='a-size-small a-color-secondary' and contains(.,'by')]/../span[2]")).getText();
//                System.out.println(author);
//
//                price = links.get(i).findElement(By.cssSelector("span.sx-price-whole")).getText()+","+links.get(i).findElement(By.cssSelector("sup.sx-price-fractional")).getText()+"$";
//                System.out.println(price);
//
//                try {rating = links.get(i).findElement(By.className("a-icon-alt")).getText();
//                    System.out.println(rating);}
//                    catch (NoSuchElementException e) {
//                        System.out.println("No such element");
//                    }
//
//
//                if (links.get(i).findElements(By.cssSelector("span.a-badge-text")).isEmpty()) {
//                    isBestSeller = "Is best seller - no";
//                    System.out.println(isBestSeller);
//                }
//                else isBestSeller = "Is best seller - yes";
//                System.out.println(isBestSeller);
//
            }
        } else System.out.println("Your search has no results");
    }
}
