package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openOnlineReplenishment() {
        // Просто прокручиваем к блоку оплаты по тексту
        WebElement paymentBlock = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(), 'Онлайн пополнение')]")));

        jsExecute("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", paymentBlock);

        // Пауза для стабилизации - ОБРАБОТКА ИСКЛЮЧЕНИЯ
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread sleep interrupted in openOnlineReplenishment");
        }
    }
}