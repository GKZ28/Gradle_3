
import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;

        import static org.junit.Assert.assertFalse;

public class CardOrderTestNotAllFields {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Download\\chromedriver_win32(1)\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testEmptyField() {
        driver.get("http://localhost:9999");

        // Заполняем поле имени
        WebElement nameInput = driver.findElement(By.cssSelector("[data-test-id='name'] input"));
        nameInput.sendKeys("Иван Иванов");

        // Заполняем поле телефона
        WebElement phoneInput = driver.findElement(By.cssSelector("[data-test-id='phone'] input"));
        phoneInput.sendKeys("");

        // Соглашаемся с условиями
        WebElement agreementCheckbox = driver.findElement(By.cssSelector("[data-test-id='agreement']"));
        agreementCheckbox.click();

        // Пытаемся отправить форму
        WebElement submitButton = driver.findElement(By.cssSelector("[data-test-id='submit']"));
        submitButton.click();

        // Проверяем, что сообщение об успешной отправке не отображается
        assertSuccessMessageNotDisplayed();
    }

    private void assertSuccessMessageNotDisplayed() {
        try {
            WebElement successMessage = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
            assertFalse(successMessage.isDisplayed());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Success message is not displayed, which is expected
        }
    }
}
