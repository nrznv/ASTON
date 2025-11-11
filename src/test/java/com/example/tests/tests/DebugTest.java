package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestBase;
import java.util.List;

public class DebugTest extends TestBase {

    @BeforeMethod
    public void setUp() {
        initialization();
    }

    @Test
    public void exploreSiteStructure() {
        System.out.println("=== ИССЛЕДУЕМ СТРУКТУРУ САЙТА MTS.BY ===");

        // Ждем загрузки страницы
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ищем все ссылки с текстом "пополнение", "оплата" и т.д.
        String[] paymentKeywords = {"пополнен", "оплат", "платеж", "payment", "topup"};

        for (String keyword : paymentKeywords) {
            List<WebElement> links = driver.findElements(By.xpath(
                    "//a[contains(translate(., 'АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ', 'абвгдеёжзийклмнопрстуфхцчшщъыьэюя'), '" + keyword + "')]"));

            System.out.println("Найдено ссылок с '" + keyword + "': " + links.size());
            for (WebElement link : links) {
                System.out.println(" - " + link.getText() + " | href: " + link.getAttribute("href"));
            }
        }

        // Ищем кнопки
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        System.out.println("Всего кнопок на странице: " + buttons.size());
        for (WebElement button : buttons) {
            String text = button.getText();
            if (!text.isEmpty()) {
                System.out.println("Кнопка: " + text);
            }
        }

        // Ищем элементы с классами, содержащими payment, topup, etc.
        String[] classKeywords = {"payment", "topup", "top-up", "replenish", "pay", "online"};
        for (String keyword : classKeywords) {
            List<WebElement> elements = driver.findElements(By.cssSelector("[class*='" + keyword + "']"));
            System.out.println("Элементов с классом содержащим '" + keyword + "': " + elements.size());
            for (WebElement element : elements) {
                System.out.println(" - Класс: " + element.getAttribute("class") + " | Текст: " + element.getText());
            }
        }

        // Ищем формы ввода
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        System.out.println("Всего input элементов: " + inputs.size());
        for (WebElement input : inputs) {
            String type = input.getAttribute("type");
            String placeholder = input.getAttribute("placeholder");
            String name = input.getAttribute("name");
            if (placeholder != null && !placeholder.isEmpty()) {
                System.out.println("Input: type=" + type + ", placeholder=" + placeholder + ", name=" + name);
            }
        }
    }

    @Test
    public void findOnlinePaymentSection() {
        System.out.println("=== ПОИСК СЕКЦИИ ОНЛАЙН ПОПОЛНЕНИЯ ===");

        // Попробуем найти по тексту
        String[] sectionTexts = {
                "Онлайн пополнение",
                "Пополнить счет",
                "Оплатить услуги",
                "Онлайн оплата",
                "Online payment"
        };

        for (String text : sectionTexts) {
            try {
                List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(), '" + text + "')]"));
                if (!elements.isEmpty()) {
                    System.out.println("Найден элемент с текстом '" + text + "': " + elements.size() + " шт.");
                    for (WebElement element : elements) {
                        System.out.println(" - Тэг: " + element.getTagName() + " | Класс: " + element.getAttribute("class"));
                    }
                }
            } catch (Exception e) {
                System.out.println("Ошибка при поиске '" + text + "': " + e.getMessage());
            }
        }
    }
}