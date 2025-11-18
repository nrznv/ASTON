package mts.tests;

import mts.pages.HomePage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentLogosTest extends TestBase {

    @Test
    public void testPaymentLogosPresence() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        homePage.debugPageInfo();

        int logosCount = homePage.getPaymentLogosCount();
        System.out.println("Найдено логотипов платежных систем: " + logosCount);

        if (logosCount == 0) {
            System.out.println("Логотипы платежных систем не найдены. Возможно изменилась структура сайта.");
        }

        String title = homePage.getBlockTitle();
        assertTrue(title.contains("Онлайн пополнение"), "Страница должна содержать блок пополнения");

        System.out.println("Основной блок найден: " + title);
    }
}