package mts.tests;

import mts.pages.HomePage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentFormTest extends TestBase {

    @Test
    public void testPaymentFormFunctionality() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        // Выбираем "Услуги связи"
        homePage.selectServiceType();
        System.out.println("Выбраны 'Услуги связи'");

        // Вводим номер
        homePage.enterPhoneNumber("297777777");
        System.out.println("Введен номер: 297777777");

        // Вводим сумму
        homePage.enterAmount("5");
        System.out.println("Введена сумма: 5");

        // Проверяем кнопку
        boolean isButtonEnabled = homePage.isContinueButtonEnabled();
        assertTrue(isButtonEnabled, "Кнопка 'Продолжить' должна быть активна после заполнения формы");
        System.out.println("Кнопка 'Продолжить' активна");
    }
}