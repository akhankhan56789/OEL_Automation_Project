import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.*;
import java.time.Duration;

public class UserAuthDataTests {
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com/login");
    }


    @ParameterizedTest
    @CsvSource({
            "arbaz1@test.com, password123",
            "testuser@gmail.com, admin456",
            "guest_user@yahoo.com, guest789"
    })
    void test1_DataDrivenLogin(String email, String password) {
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();


        WebElement error = driver.findElement(By.xpath("//p[contains(text(),'incorrect')]"));
        Assertions.assertTrue(error.isDisplayed());
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }
}