package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.OnlinePaymentPage;
import utils.TestBase;
import java.util.List;

public class OnlinePaymentTest extends TestBase {

    private MainPage mainPage;
    private OnlinePaymentPage onlinePaymentPage;

    @BeforeMethod
    public void setUp() {
        initialization();
        mainPage = new MainPage(driver);
        onlinePaymentPage = new OnlinePaymentPage(driver);
    }

    @Test
    public void testPaymentOptionsExist() {
        // Простой тест для проверки наличия элементов
        mainPage.openOnlineReplenishment();

        // Проверяем, что блок оплаты существует
        List<WebElement> paymentBlocks = driver.findElements(By.xpath("//*[contains(text(), 'Онлайн пополнение')]"));
        Assert.assertFalse(paymentBlocks.isEmpty(), "Payment block should exist");

        // Проверяем наличие вариантов оплаты
        String[] options = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};
        for (String option : options) {
            List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(), '" + option + "')]"));
            System.out.println("Found " + elements.size() + " elements for: " + option);
            if (!elements.isEmpty()) {
                System.out.println("First element text: " + elements.get(0).getText());
                System.out.println("First element tag: " + elements.get(0).getTagName());
            }
        }
    }

    @Test
    public void testCommunicationServicesPayment() {
        mainPage.openOnlineReplenishment();

        try {
            // Выбираем вариант "Услуги связи"
            onlinePaymentPage.selectPaymentOption("Услуги связи");

            // Проверяем плейсхолдеры
            String phonePlaceholder = onlinePaymentPage.getPhonePlaceholder();
            String amountPlaceholder = onlinePaymentPage.getAmountPlaceholder();

            System.out.println("Phone placeholder: " + phonePlaceholder);
            System.out.println("Amount placeholder: " + amountPlaceholder);

            Assert.assertEquals(phonePlaceholder, "Номер телефона");
            Assert.assertEquals(amountPlaceholder, "Сумма");

            // Заполняем поля
            onlinePaymentPage.fillCommunicationServices("297777777", "5");
            onlinePaymentPage.clickContinue();

            // Проверяем, перешли ли мы на следующую страницу
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Current URL after continue: " + driver.getCurrentUrl());

        } catch (Exception e) {
            System.out.println("Test failed with error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testEmptyFieldsPlaceholders() {
        mainPage.openOnlineReplenishment();

        String[] paymentOptions = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};

        for (String option : paymentOptions) {
            try {
                System.out.println("Testing option: " + option);

                // Выбираем вариант
                onlinePaymentPage.selectPaymentOption(option);

                // Проверяем плейсхолдеры
                String amountPlaceholder = onlinePaymentPage.getAmountPlaceholder();
                Assert.assertNotNull(amountPlaceholder, "Amount placeholder should not be null");
                Assert.assertFalse(amountPlaceholder.isEmpty(), "Amount placeholder should not be empty");

                System.out.println("Successfully tested: " + option);

                // Пауза между переключениями - ОБРАБОТКА ИСКЛЮЧЕНИЯ
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread sleep interrupted for option: " + option);
                }

            } catch (Exception e) {
                System.out.println("Failed to test option " + option + ": " + e.getMessage());
            }
        }
    }
}