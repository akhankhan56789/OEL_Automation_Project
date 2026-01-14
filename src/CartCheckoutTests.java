import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class CartCheckoutTests {
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
    }

    @Test
    void test1_AddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        // Scroll down taake product nazar aaye
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");

        WebElement add = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Add to cart'])[1]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", add);


        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content"))).isDisplayed();
        Assertions.assertTrue(isDisplayed);
    }

    @Test
    void test2_VerifyCartPage() {
        driver.get("https://automationexercise.com/view_cart");
        Assertions.assertTrue(driver.getCurrentUrl().contains("view_cart"));
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }
}