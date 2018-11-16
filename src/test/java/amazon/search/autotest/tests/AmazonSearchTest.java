package amazon.search.autotest.tests;

import amazon.search.autotest.pages.AmazonMainPage;
import amazon.search.autotest.pages.AmazonSearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AmazonSearchTest {
    public static WebDriver driver;
    public static AmazonMainPage amazonMainPage;
    public static AmazonSearchResultsPage amazonSearchResultsPage;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        URL server = new URL("http://127.0.0.1:4444/wd/hub");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");

        System.out.println("Connecting to " + server);

        //Check if parameter passed from TestNG is 'Firefox'
        if (browser.equalsIgnoreCase("firefox")) {
            //create firefox instance
            System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\geckodriver-v0.23.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        //Check if parameter passed as 'Chrome'
        else if (browser.equalsIgnoreCase("chrome")) {
            //create chrome instance
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
            driver = new RemoteWebDriver(server, capabilities);
        }
        //Check if parameter passed as 'Edge'
        else if (browser.equalsIgnoreCase("edge")) {
            //create edge instance
            System.setProperty("webdriver.edge.driver", "C:\\Program Files\\MicrosoftWebDriver\\MicrosoftWebDriver.exe");
            driver = new EdgeDriver();
        } else {
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
        amazonMainPage = new AmazonMainPage(driver);
        amazonSearchResultsPage = new AmazonSearchResultsPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com");
    }

    @Test
    @Parameters({"searchQuerry", "bookToSearch"})
    public void searchTest(String searchQuerry, String bookToSearch) {
        amazonMainPage.inputQuerry(searchQuerry);
        amazonMainPage.clickSearchButton();

        amazonSearchResultsPage.getBooksInfo();
        amazonSearchResultsPage.checkBookAvailable(bookToSearch);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
