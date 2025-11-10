package mts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(text(), 'Онлайн пополнение')]")
    private WebElement blockTitle;

    @FindBy(tagName = "img")
    private List<WebElement> allImages;

    @FindBy(partialLinkText = "Подробнее")
    private WebElement detailsLink;

    @FindBy(xpath = "//*[contains(text(), 'Услуги связи')]")
    private WebElement serviceTypeLabel;

    @FindBy(xpath = "//input[contains(@type, 'tel') or contains(@placeholder, 'номер')]")
    private WebElement phoneInput;

    @FindBy(xpath = "//input[contains(@type, 'number') or contains(@placeholder, 'сумм')]")
    private WebElement amountInput;

    @FindBy(xpath = "//button[contains(., 'Продолжить') or contains(., 'Continue')]")
    private WebElement continueButton;

    public void open() {
        driver.get("https://www.mts.by");
    }

    public String getBlockTitle() {
        wait.until(ExpectedConditions.visibilityOf(blockTitle));
        return blockTitle.getText();
    }

    public int getPaymentLogosCount() {
        wait.until(ExpectedConditions.visibilityOf(blockTitle));

        return (int) allImages.stream()
                .filter(WebElement::isDisplayed)
                .filter(img -> {
                    String src = img.getAttribute("src");
                    String alt = img.getAttribute("alt");
                    return src != null && (src.contains("payment") || src.contains("card") ||
                            src.contains("visa") || src.contains("mastercard") ||
                            (alt != null && alt.toLowerCase().contains("плате")));
                })
                .count();
    }

    public boolean arePaymentLogosDisplayed() {
        int count = getPaymentLogosCount();
        return count > 0;
    }

    public void clickDetailsLink() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(detailsLink));
            detailsLink.click();
        } catch (Exception e) {
            WebElement alternativeLink = driver.findElement(By.xpath("//a[contains(@href, 'подробнее') or contains(@href, 'details')]"));
            alternativeLink.click();
        }
    }

    public void selectServiceType() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(serviceTypeLabel));
            serviceTypeLabel.click();
        } catch (Exception e) {
            System.out.println("Не удалось найти 'Услуги связи', продолжаем без выбора");
        }
    }

    public void enterPhoneNumber(String phone) {
        try {
            wait.until(ExpectedConditions.visibilityOf(phoneInput));
            phoneInput.clear();
            phoneInput.sendKeys(phone);
        } catch (Exception e) {
            System.out.println("Не удалось найти поле для номера телефона");
        }
    }

    public void enterAmount(String amount) {
        try {
            wait.until(ExpectedConditions.visibilityOf(amountInput));
            amountInput.clear();
            amountInput.sendKeys(amount);
        } catch (Exception e) {
            System.out.println("Не удалось найти поле для суммы");
        }
    }

    public void clickContinueButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueButton));
            continueButton.click();
        } catch (Exception e) {
            System.out.println("Не удалось найти кнопку 'Продолжить'");
        }
    }

    public boolean isContinueButtonEnabled() {
        try {
            return continueButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void debugPageInfo() {
        System.out.println("ДЕБАГ ИНФОРМАЦИЯ");
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Заголовок: " + driver.getTitle());
        System.out.println("Всего изображений: " + allImages.size());
        System.out.println("Видимых изображений: " + allImages.stream().filter(WebElement::isDisplayed).count());

        allImages.stream()
                .filter(WebElement::isDisplayed)
                .limit(10)
                .forEach(img -> {
                    String src = img.getAttribute("src");
                    String alt = img.getAttribute("alt");
                    System.out.println("Изображение: src=" + src + ", alt=" + alt);
                });
    }

    // ДОБАВЛЕННЫЕ МЕТОДЫ
    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}