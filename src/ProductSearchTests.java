import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class ProductSearchTests {
    WebDriver driver;

    @BeforeEach void setup() {
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com/products");
    }

    @Test void test1_PerformSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.id("search_product")));
        search.clear();
        search.sendKeys("Tshirt");

        // Click button instead of Enter for better stability
        WebElement submitSearch = driver.findElement(By.id("submit_search"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitSearch);

        wait.until(ExpectedConditions.urlContains("search"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("search"));
    }

    @Test void test2_CategoryVisibility() {
        Assertions.assertTrue(driver.findElement(By.id("accordian")).isDisplayed());
    }

    @Test void test3_ViewProductDetails() {
        WebElement view = driver.findElement(By.xpath("(//a[text()='View Product'])[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", view);
        Assertions.assertTrue(driver.getCurrentUrl().contains("product_details"));
    }

    @AfterEach void tearDown() { if (driver != null) driver.quit(); }
}