package amazon.search.autotest.tests;

import amazon.search.autotest.pages.AmazonMainPage;
import amazon.search.autotest.pages.AmazonSearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AmazonSearchTest {
    private static WebDriver driver;
    private static AmazonMainPage amazonMainPage;
    private static AmazonSearchResultsPage amazonSearchResultsPage;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        URL server = new URL("http://127.0.0.1:4444/wd/hub");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");

        switch (browser.toLowerCase()) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "lib/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "chrome":
//                driver = new RemoteWebDriver(server, capabilities);
                System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "lib/MicrosoftWebDriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                throw new Exception("Browser is not correct");
        }

        amazonMainPage = new AmazonMainPage(driver);
        amazonSearchResultsPage = new AmazonSearchResultsPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com");
    }

    @Test
    @Parameters({"searchTerm", "bookToSearch"})
    public void searchTest(String searchTerm, String bookToSearch) {
        amazonMainPage.inputSearchTerm(searchTerm);
        amazonMainPage.clickSearchButton();
        amazonSearchResultsPage.checkBookAvailable(bookToSearch);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
