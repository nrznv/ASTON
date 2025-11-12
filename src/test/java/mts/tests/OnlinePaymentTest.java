package mts.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import mts.pages.HomePage;
import mts.utils.TestData;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OnlinePaymentTest {
    private WebDriver driver;
    private HomePage homePage;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        homePage = new HomePage(driver);
        homePage.open();
    }

    @Test
    void testOnlinePaymentBlockTitle() {
        String actualTitle = homePage.getOnlinePaymentTitle();
        assertTrue(actualTitle.contains("Онлайн пополнение"),
                "Название блока должно содержать 'Онлайн пополнение'. Актуальное название: " + actualTitle);
    }

    @Test
    void testPaymentSystemsLogosPresence() {
        int actualLogosCount = homePage.getPaymentSystemsCount();
        assertTrue(actualLogosCount >= 5,
                "В блоке онлайн пополнения должно быть минимум 5 логотипов. Найдено: " + actualLogosCount);

        assertTrue(homePage.areAllPaymentSystemsDisplayed(),
                "Не все логотипы платежных систем отображаются");
    }

    @Test
    void testDetailsLinkFunctionality() {
        try {
            String originalWindow = driver.getWindowHandle();
            String originalUrl = driver.getCurrentUrl();

            homePage.forceHideCookieBanner();

            WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(
                    homePage.getDetailsLinkLocator()
            ));

            wait.until(driver -> {
                WebElement element = driver.findElement(homePage.getDetailsLinkLocator());
                return element.isDisplayed() && element.isEnabled();
            });

            detailsLink.click();

            wait.until(driver -> driver.getWindowHandles().size() > 1 ||
                    !driver.getCurrentUrl().equals(originalUrl));

            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            if (tabs.size() > 1) {
                driver.switchTo().window(tabs.get(1));

                String currentUrl = driver.getCurrentUrl();
                assertNotEquals(originalUrl, currentUrl,
                        "Ссылка 'Подробнее о сервисе' должна открывать новую страницу");

                driver.close();
                driver.switchTo().window(originalWindow);
            } else {
                wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(originalUrl)));
                String currentUrl = driver.getCurrentUrl();
                assertNotEquals(originalUrl, currentUrl,
                        "Ссылка 'Подробнее о сервисе' должна вести на другую страницу");

                driver.navigate().back();
                wait.until(ExpectedConditions.urlToBe(originalUrl));
            }
        } catch (Exception e) {
            fail("Не удалось проверить работу ссылки 'Подробнее о сервисе': " + e.getMessage());
        }
    }

    @Test
    void testContinueButtonFunctionality() {
        try {
            System.out.println("=== НАЧАЛО ТЕСТА КНОПКИ 'ПРОДОЛЖИТЬ' ===");

            // Выбираем услугу связи
            homePage.selectCommunicationServices();

            // Вводим номер телефона
            homePage.enterPhoneNumber(TestData.EXPECTED_PHONE_NUMBER);

            // Убеждаемся, что баннер не мешает
            homePage.forceHideCookieBanner();

            // Нажимаем кнопку "Продолжить"
            homePage.clickContinueButton();

            // Даем время для обработки и перехода
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Проверяем, произошел ли переход на страницу оплаты
            boolean isPaymentPageOpened = homePage.isPaymentPageOpened();
            boolean urlChanged = !driver.getCurrentUrl().equals(TestData.HOME_PAGE_URL);

            System.out.println("Открыта страница оплаты: " + isPaymentPageOpened);
            System.out.println("URL изменился: " + urlChanged);
            System.out.println("Текущий URL: " + driver.getCurrentUrl());

            // Тест считается успешным, если произошел переход на страницу оплаты
            assertTrue(isPaymentPageOpened || urlChanged,
                    "После нажатия кнопки 'Продолжить' должен произойти переход на страницу оплаты. " +
                            "Текущий URL: " + driver.getCurrentUrl());

            System.out.println("=== ТЕСТ КНОПКИ 'ПРОДОЛЖИТЬ' ЗАВЕРШЕН УСПЕШНО ===");
        } catch (Exception e) {
            fail("Не удалось проверить работу кнопки 'Продолжить': " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}