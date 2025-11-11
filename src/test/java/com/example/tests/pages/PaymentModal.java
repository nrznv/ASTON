package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentModal extends BasePage {

    // Модальное окно - предполагаем, что оно появляется после нажатия "Продолжить"
    @FindBy(css = ".modal, .popup, [role='dialog']")
    private WebElement modal;

    // Сумма платежа - ищем элемент с текстом содержащим "BYN" или "руб"
    @FindBy(xpath = "//*[contains(text(), 'BYN') or contains(text(), 'руб')]")
    private WebElement paymentAmount;

    // Номер телефона - ищем элемент с номером
    @FindBy(xpath = "//*[contains(text(), '375') or contains(text(), '+375')]")
    private WebElement phoneNumber;

    // Поля для карты - предполагаемые локаторы
    @FindBy(css = "input[placeholder*='карт']")
    private WebElement cardNumberInput;

    @FindBy(css = "input[placeholder*='срок']")
    private WebElement expiryDateInput;

    @FindBy(css = "input[placeholder*='CVC']")
    private WebElement cvcInput;

    // Иконки платежных систем
    @FindBy(css = "img[alt*='Visa'], img[alt*='MasterCard'], img[alt*='Belkart']")
    private List<WebElement> paymentSystemIcons;

    // Кнопка оплаты
    @FindBy(xpath = "//button[contains(text(), 'Оплатить') or contains(text(), 'Pay')]")
    private WebElement payButton;

    public PaymentModal(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        try {
            waitForElementToBeVisible(modal);
            return modal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPaymentAmount() {
        return paymentAmount.getText();
    }

    public String getPhoneNumber() {
        return phoneNumber.getText();
    }

    public List<String> getCardInputPlaceholders() {
        List<String> placeholders = new java.util.ArrayList<>();
        try {
            if (cardNumberInput.isDisplayed()) {
                placeholders.add(cardNumberInput.getAttribute("placeholder"));
            }
            if (expiryDateInput.isDisplayed()) {
                placeholders.add(expiryDateInput.getAttribute("placeholder"));
            }
            if (cvcInput.isDisplayed()) {
                placeholders.add(cvcInput.getAttribute("placeholder"));
            }
        } catch (Exception e) {
            System.out.println("Не удалось найти поля для карты: " + e.getMessage());
        }
        return placeholders;
    }

    public List<String> getPaymentSystemIcons() {
        return paymentSystemIcons.stream()
                .map(element -> element.getAttribute("alt"))
                .collect(Collectors.toList());
    }

    public String getPayButtonText() {
        return payButton.getText();
    }
}