package mts.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Куки-баннер
    @FindBy(css = "div.cookie.show")
    private WebElement cookieBanner;

    @FindBy(css = ".cookie__accept")
    private WebElement acceptCookiesButton;

    // Блок онлайн пополнения
    @FindBy(xpath = "/html/body/div[6]/main/div/div[4]/div[2]/div/div/div[2]/section/div/h2")
    private WebElement onlinePaymentTitle;

    // Логотипы платежных систем
    @FindBy(xpath = "/html/body/div[6]/main/div/div[4]/div[2]/div/div/div[2]/section/div/div[2]/ul/li/img")
    private List<WebElement> paymentSystemLogos;

    // Ссылка "Подробнее о сервисе"
    @FindBy(xpath = "/html/body/div[6]/main/div/div[4]/div[2]/div/div/div[2]/section/div/a")
    private WebElement detailsLink;

    // Выбор услуги "Услуги связи"
    @FindBy(xpath = "/html/body/div[6]/main/div/div[4]/div[2]/div/div/div[2]/section/div/div/div/div[2]/button")
    private WebElement communicationServicesButton;

    // Поле ввода номера телефона
    @FindBy(css = "input[type='tel'], input[name='phone'], input[placeholder*='телефон'], input[placeholder*='номер']")
    private WebElement phoneNumberInput;

    // Кнопка "Продолжить"
    @FindBy(xpath = "//button[contains(text(), 'Продолжить')]")
    private WebElement continueButton;

    // Селектор для выпадающего списка операторов (если есть)
    @FindBy(css = ".select__list, .operators-list, [data-operators]")
    private WebElement operatorsList;

    public void acceptCookies() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement banner = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.cookie.show")
            ));

            System.out.println("Cookie-баннер найден, пытаемся закрыть...");

            boolean cookiesAccepted = false;

            // Способ 1: Ищем кнопку внутри баннера
            try {
                List<WebElement> buttons = banner.findElements(By.tagName("button"));
                if (!buttons.isEmpty()) {
                    WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(buttons.get(0)));
                    acceptButton.click();
                    System.out.println("Кнопка принятия куки найдена внутри баннера и нажата");
                    cookiesAccepted = true;
                }
            } catch (Exception e) {
                System.out.println("Не удалось найти кнопку внутри баннера: " + e.getMessage());
            }

            // Ждем исчезновения баннера
            if (cookiesAccepted) {
                try {
                    wait.until(ExpectedConditions.invisibilityOf(banner));
                    System.out.println("Cookie-баннер успешно закрыт");
                } catch (Exception e) {
                    System.out.println("Баннер не исчез, но продолжаем выполнение");
                }
            }

        } catch (TimeoutException e) {
            System.out.println("Cookie-баннер не найден в течение 10 секунд");
        } catch (Exception e) {
            System.out.println("Ошибка при закрытии cookie-баннера: " + e.getMessage());
        }
    }

    public void forceHideCookieBanner() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "var banner = document.querySelector('div.cookie.show');" +
                            "if (banner) banner.style.display = 'none';"
            );
            System.out.println("Cookie-баннер принудительно скрыт через JavaScript");
        } catch (Exception e) {
            System.out.println("Не удалось скрыть баннер через JavaScript: " + e.getMessage());
        }
    }

    public String getOnlinePaymentTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(onlinePaymentTitle));
        return onlinePaymentTitle.getText();
    }

    public int getPaymentSystemsCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(paymentSystemLogos));
        return paymentSystemLogos.size();
    }

    public boolean areAllPaymentSystemsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(paymentSystemLogos));
        return paymentSystemLogos.stream().allMatch(WebElement::isDisplayed);
    }

    public void clickDetailsLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        checkAndCloseCookieBanner();
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(detailsLink));
        link.click();
    }

    public By getDetailsLinkLocator() {
        return By.xpath("/html/body/div[6]/main/div/div[4]/div[2]/div/div/div[2]/section/div/a");
    }

    public By getContinueButtonLocator() {
        return By.xpath("//button[contains(text(), 'Продолжить')]");
    }

    public By getPhoneNumberInputLocator() {
        return By.cssSelector("input[type='tel'], input[name='phone']");
    }

    public void selectCommunicationServices() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        checkAndCloseCookieBanner();

        System.out.println("=== ВЫБОР УСЛУГИ СВЯЗИ ===");

        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(communicationServicesButton));
            System.out.println("Найдена кнопка: " + button.getText());

            String buttonClass = button.getAttribute("class");
            System.out.println("Класс кнопки: " + buttonClass);

            if (!buttonClass.contains("active")) {
                System.out.println("Кликаем на кнопку выбора услуги связи...");

                // Пробуем обычный клик
                try {
                    button.click();
                    System.out.println("Обычный клик выполнен");
                } catch (Exception e) {
                    System.out.println("Обычный клик не сработал, пробуем JavaScript...");
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", button);
                    System.out.println("JavaScript клик выполнен");
                }

                // Ждем появления формы или выпадающего списка
                safeSleep(2000);

                // Проверяем, появилось ли поле ввода телефона
                if (isPhoneInputAvailable()) {
                    System.out.println("Поле ввода телефона доступно");
                } else {
                    System.out.println("Поле ввода телефона не появилось");
                    // Возможно, нужно выбрать оператора из выпадающего списка
                    selectOperatorIfNeeded();
                }

            } else {
                System.out.println("Услуга связи уже выбрана");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при выборе услуги связи: " + e.getMessage());
            throw e;
        }
    }

    private boolean isPhoneInputAvailable() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.visibilityOf(phoneNumberInput));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void selectOperatorIfNeeded() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Пробуем найти выпадающий список операторов
            if (operatorsList.isDisplayed()) {
                System.out.println("Найден выпадающий список операторов");
                // Выбираем первого оператора из списка
                WebElement firstOperator = operatorsList.findElement(By.cssSelector("li, div, button"));
                firstOperator.click();
                System.out.println("Выбран оператор из списка");

                // Ждем появления поля ввода
                wait.until(ExpectedConditions.visibilityOf(phoneNumberInput));
            }
        } catch (Exception e) {
            System.out.println("Выпадающий список операторов не найден или не требует выбора: " + e.getMessage());
        }
    }

    public void enterPhoneNumber(String phoneNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("=== ВВОД НОМЕРА ТЕЛЕФОНА ===");

        try {
            // Пробуем основной локатор
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[type='tel'], input[name='phone']")
            ));
            input.clear();
            input.sendKeys(phoneNumber);
            System.out.println("Номер телефона введен: " + phoneNumber);

        } catch (TimeoutException e1) {
            // Пробуем альтернативные локаторы
            try {
                WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[placeholder*='телефон'], input[placeholder*='номер']")
                ));
                input.clear();
                input.sendKeys(phoneNumber);
                System.out.println("Номер телефона введен через альтернативный локатор: " + phoneNumber);
            } catch (TimeoutException e2) {
                System.out.println("Не удалось найти поле ввода номера телефона");
                throw e2;
            }
        }
    }

    public void clickContinueButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        checkAndCloseCookieBanner();

        System.out.println("=== НАЖАТИЕ КНОПКИ 'ПРОДОЛЖИТЬ' ===");

        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Продолжить')]")
            ));
            button.click();
            System.out.println("Кнопка 'Продолжить' нажата");
        } catch (TimeoutException e1) {
            try {
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[type='submit'], .btn-primary")
                ));
                button.click();
                System.out.println("Кнопка 'Продолжить' нажата через CSS селектор");
            } catch (TimeoutException e2) {
                System.out.println("Не удалось найти кнопку 'Продолжить'");
                throw e2;
            }
        }
    }

    private void checkAndCloseCookieBanner() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement banner = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.cookie.show")
            ));
            if (banner.isDisplayed()) {
                System.out.println("Cookie-баннер перекрывает элемент, закрываем...");
                acceptCookies();
                safeSleep(1000);
                if (banner.isDisplayed()) {
                    forceHideCookieBanner();
                }
            }
        } catch (TimeoutException e) {
            // Баннер не виден, продолжаем
        }
    }

    // Безопасный sleep без выбрасывания InterruptedException
    private void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Sleep прерван: " + e.getMessage());
        }
    }

    public void open() {
        driver.get("https://www.mts.by");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));

        acceptCookies();

        safeSleep(2000);
    }

    public boolean isElementVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Новый метод для проверки перехода на страницу оплаты
    public boolean isPaymentPageOpened() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            // Проверяем изменение URL (должен содержать pay или payment)
            wait.until(driver -> driver.getCurrentUrl().toLowerCase().contains("pay") ||
                    driver.getCurrentUrl().toLowerCase().contains("payment"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}