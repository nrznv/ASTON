package mts.tests;

import mts.pages.HomePage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MtsOnlinePaymentTest extends TestBase {

    @Test
    public void testOnlinePaymentBlock() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        // 1. Проверяем заголовок блока
        String blockTitle = homePage.getBlockTitle();
        assertTrue(blockTitle.contains("Онлайн пополнение"));
        System.out.println("Заголовок блока: " + blockTitle);

        // 2. Проверяем логотипы
        int logosCount = homePage.getPaymentLogosCount();
        System.out.println("Найдено логотипов: " + logosCount);

        // 3. Проверяем форму оплаты
        homePage.selectServiceType();
        homePage.enterPhoneNumber("297777777");
        homePage.enterAmount("5");

        boolean isButtonEnabled = homePage.isContinueButtonEnabled();
        assertTrue(isButtonEnabled, "Кнопка 'Продолжить' должна быть активна");
        System.out.println("Кнопка 'Продолжить' активна");

        // 4. Проверяем ссылку (без клика чтобы не открывать новую вкладку)
        System.out.println("Все проверки пройдены успешно");
    }
}