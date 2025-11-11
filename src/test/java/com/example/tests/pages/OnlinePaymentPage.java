package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions; // Добавляем импорт
import java.util.List;
import java.util.stream.Collectors;

public class OnlinePaymentPage extends BasePage {

    public OnlinePaymentPage(WebDriver driver) {
        super(driver);
    }

    public void selectPaymentOption(String optionName) {
        System.out.println("Searching for payment option: " + optionName);

        // Ищем элемент по тексту
        WebElement option = driver.findElement(By.xpath("//*[contains(text(), '" + optionName + "')]"));

        if (option != null && option.isDisplayed()) {
            System.out.println("Found option: " + option.getText() + ", tag: " + option.getTagName());
            jsExecute("arguments[0].click();", option);
        } else {
            throw new RuntimeException("Payment option not found: " + optionName);
        }
    }

    public void fillCommunicationServices(String phone, String amount) {
        // Ищем поле номера телефона
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[placeholder='Номер телефона']")));
        phoneField.clear();
        phoneField.sendKeys(phone);

        // Ищем поле суммы
        WebElement amountField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[placeholder='Сумма']")));
        amountField.clear();
        amountField.sendKeys(amount);
    }

    public void clickContinue() {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Продолжить')]")));
        continueButton.click();
    }

    public String getPhonePlaceholder() {
        try {
            WebElement phoneField = driver.findElement(By.cssSelector("input[placeholder='Номер телефона']"));
            return phoneField.getAttribute("placeholder");
        } catch (Exception e) {
            return "";
        }
    }

    public String getAmountPlaceholder() {
        try {
            WebElement amountField = driver.findElement(By.cssSelector("input[placeholder='Сумма']"));
            return amountField.getAttribute("placeholder");
        } catch (Exception e) {
            return "";
        }
    }

    public List<String> getAvailablePaymentOptions() {
        // Простой поиск по тексту
        List<WebElement> options = driver.findElements(By.xpath(
                "//*[contains(text(), 'Услуги связи') or contains(text(), 'Домашний интернет') or contains(text(), 'Рассрочка') or contains(text(), 'Задолженность')]"));

        return options.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}