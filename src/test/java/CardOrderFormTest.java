import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.assertTrue;

public class CardOrderFormTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Указываем путь к драйверу браузера (Chrome)
        System.setProperty("webdriver.chrome.driver", "D:\\Download\\chromedriver_win32(1)\\chromedriver.exe");

        // Настройка для включения headless-режима
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu"); // Эти параметры могут варьироваться в зависимости от версии Chrome

        // Инициализация WebDriver с настройками
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCardOrderForm() {
        // Открываем страницу
        driver.get("http://localhost:9999");

        // Заполняем поля формы
        WebElement nameInput = driver.findElement(By.cssSelector("[data-test-id='name'] input"));
        WebElement phoneInput = driver.findElement(By.cssSelector("[data-test-id='phone'] input"));
        WebElement agreementCheckbox = driver.findElement(By.cssSelector("[data-test-id='agreement']"));
        WebElement submitButton = driver.findElement(By.cssSelector("[data-test-id='submit']"));

        nameInput.sendKeys("Иван Иванов");
        phoneInput.sendKeys("+79123456789");
        agreementCheckbox.click();
        submitButton.click();

        // Проверяем сообщение об успешной отправке заявки
        WebElement successMessage = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        assertTrue(successMessage.isDisplayed());
    }
}
