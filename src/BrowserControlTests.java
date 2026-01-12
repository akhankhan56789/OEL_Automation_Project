import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class BrowserControlTests {
    WebDriver driver;
    @BeforeEach void setup() { driver = new ChromeDriver(); driver.get("https://automationexercise.com/"); }
    @Test void test1_VerifyTitle() { Assertions.assertTrue(driver.getTitle().contains("Automation")); }
    @Test void test2_VerifyURL() { Assertions.assertEquals("https://automationexercise.com/", driver.getCurrentUrl()); }
    @Test void test3_RefreshPage() { driver.navigate().refresh(); Assertions.assertNotNull(driver.getTitle()); }
    @AfterEach void tearDown() { if (driver != null) driver.quit(); }
}