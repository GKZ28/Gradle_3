import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.*;

public class CardOrderFormTest {

    public static void main(String[] args) {
        // Указываем путь к драйверу браузера (Chrome)
        System.setProperty("webdriver.chrome.driver", "D:\\Download\\chromedriver_win32(1)\\chromedriver.exe");

        // Настройка для включения headless-режима
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu"); // Эти параметры могут варьироваться в зависимости от версии Chrome

        // Инициализация WebDriver с настройками
        WebDriver driver = new ChromeDriver(options);

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

        // Закрываем браузер
        driver.quit();
    }
}
