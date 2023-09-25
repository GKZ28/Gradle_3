import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;

public class CardOrderTestText {

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
    public void testEmptyFieldValidation() {
        driver.get("http://localhost:9999");

        // Заполняем поле имени
        WebElement nameInput = driver.findElement(By.cssSelector("[data-test-id='name'] input"));
        nameInput.sendKeys("");

        // Заполняем поле телефона
        WebElement phoneInput = driver.findElement(By.cssSelector("[data-test-id='phone'] input"));
        phoneInput.sendKeys("1234567890");

        // Соглашаемся с условиями
        WebElement agreementCheckbox = driver.findElement(By.cssSelector("[data-test-id='agreement']"));
        agreementCheckbox.click();

        // Пытаемся отправить форму
        WebElement submitButton = driver.findElement(By.cssSelector("[data-test-id='submit']"));
        submitButton.click();

        // Проверяем, что появилось сообщение о необходимости заполнения поля
        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test-id='name'] .input__sub"));
        assertEquals("Поле обязательно для заполнения", errorMessage.getText());
    }
}
