package mts.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class SiteStructureAnalyzer {
    private WebDriver driver;

    public SiteStructureAnalyzer() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void analyzeMtsSite() {
        try {
            driver.get("https://www.mts.by");

            // Принимаем куки если есть
            acceptCookies();

            System.out.println("=== АНАЛИЗ СТРУКТУРЫ SITE MTS.BY ===");
            System.out.println("Текущий URL: " + driver.getCurrentUrl());
            System.out.println("Заголовок страницы: " + driver.getTitle());
            System.out.println();

            // 1. Ищем блок онлайн пополнения
            analyzeOnlinePaymentBlock();

            // 2. Ищем логотипы платежных систем
            analyzePaymentSystems();

            // 3. Ищем ссылку "Подробнее о сервисе"
            analyzeDetailsLink();

            // 4. Ищем форму пополнения
            analyzePaymentForm();

            // 5. Дополнительная информация о структуре
            analyzePageStructure();

        } catch (Exception e) {
            System.out.println("Ошибка при анализе: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    private void acceptCookies() {
        try {
            WebElement cookieButton = driver.findElement(By.cssSelector(".cookie__accept, .cookie-accept, [onclick*='cookie'], button[aria-label*='cookie']"));
            cookieButton.click();
            Thread.sleep(1000);
            System.out.println("✓ Куки приняты");
        } catch (Exception e) {
            System.out.println("✗ Куки-баннер не найден или уже принят");
        }
    }

    private void analyzeOnlinePaymentBlock() {
        System.out.println("=== БЛОК ОНЛАЙН ПОПОЛНЕНИЯ ===");

        // Ищем заголовки
        List<WebElement> headers = driver.findElements(By.tagName("h2"));
        for (WebElement header : headers) {
            String text = header.getText();
            if (text.contains("Онлайн") || text.contains("Пополнение") || text.contains("Пополнить")) {
                System.out.println("Найден заголовок: '" + text + "'");
                System.out.println("  Локатор: " + getElementXPath(header));
                System.out.println("  Классы: " + header.getAttribute("class"));
            }
        }

        // Ищем по классам
        String[] paymentClasses = {"online-payment", "payment", "popolnenie", "top-up"};
        for (String className : paymentClasses) {
            try {
                List<WebElement> elements = driver.findElements(By.cssSelector("." + className));
                if (!elements.isEmpty()) {
                    System.out.println("Найден элемент с классом: " + className);
                    for (WebElement element : elements) {
                        System.out.println("  Текст: '" + element.getText() + "'");
                        System.out.println("  Локатор: " + getElementXPath(element));
                    }
                }
            } catch (Exception e) {
                // Игнорируем если класс не найден
            }
        }
        System.out.println();
    }

    private void analyzePaymentSystems() {
        System.out.println("=== ЛОГОТИПЫ ПЛАТЕЖНЫХ СИСТЕМ ===");

        // Ищем все изображения
        List<WebElement> images = driver.findElements(By.tagName("img"));
        int paymentLogos = 0;

        for (WebElement img : images) {
            String alt = img.getAttribute("alt");
            String src = img.getAttribute("src");
            String className = img.getAttribute("class");

            // Проверяем, является ли изображение логотипом платежной системы
            if (isPaymentSystemLogo(alt, src, className)) {
                paymentLogos++;
                System.out.println("Логотип " + paymentLogos + ":");
                System.out.println("  ALT: " + alt);
                System.out.println("  SRC: " + src);
                System.out.println("  Класс: " + className);
                System.out.println("  Локатор: " + getElementXPath(img));
                System.out.println("  Видим: " + img.isDisplayed());
            }
        }

        System.out.println("Всего найдено логотипов: " + paymentLogos);

        // Ищем контейнер с логотипами
        String[] containerSelectors = {
                ".payment-systems", ".pay-systems", ".payment-icons",
                ".payment-methods", ".banks-logos", ".partners"
        };

        for (String selector : containerSelectors) {
            try {
                List<WebElement> containers = driver.findElements(By.cssSelector(selector));
                if (!containers.isEmpty()) {
                    System.out.println("Найден контейнер: " + selector);
                    for (WebElement container : containers) {
                        System.out.println("  Локатор: " + getElementXPath(container));
                        System.out.println("  Содержит изображений: " + container.findElements(By.tagName("img")).size());
                    }
                }
            } catch (Exception e) {
                // Игнорируем
            }
        }
        System.out.println();
    }

    private void analyzeDetailsLink() {
        System.out.println("=== ССЫЛКА 'ПОДРОБНЕЕ О СЕРВИСЕ' ===");

        // Ищем все ссылки с текстом "Подробнее"
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links) {
            String text = link.getText();
            String href = link.getAttribute("href");

            if (text.contains("Подробнее") || text.contains("подробнее") ||
                    (href != null && href.contains("help") || href.contains("service"))) {
                System.out.println("Найдена ссылка:");
                System.out.println("  Текст: '" + text + "'");
                System.out.println("  HREF: " + href);
                System.out.println("  Локатор: " + getElementXPath(link));
                System.out.println("  Видим: " + link.isDisplayed());
            }
        }
        System.out.println();
    }

    private void analyzePaymentForm() {
        System.out.println("=== ФОРМА ПОПОЛНЕНИЯ ===");

        // Ищем формы
        List<WebElement> forms = driver.findElements(By.tagName("form"));
        System.out.println("Найдено форм: " + forms.size());

        for (WebElement form : forms) {
            System.out.println("Форма:");
            System.out.println("  ID: " + form.getAttribute("id"));
            System.out.println("  Класс: " + form.getAttribute("class"));
            System.out.println("  Локатор: " + getElementXPath(form));

            // Ищем поля ввода в форме
            List<WebElement> inputs = form.findElements(By.tagName("input"));
            System.out.println("  Поля ввода: " + inputs.size());
            for (WebElement input : inputs) {
                String type = input.getAttribute("type");
                String placeholder = input.getAttribute("placeholder");
                String name = input.getAttribute("name");
                if ("tel".equals(type) || (placeholder != null && placeholder.contains("номер")) ||
                        "phone".equals(name)) {
                    System.out.println("    Телефонное поле: " + getElementXPath(input));
                }
            }

            // Ищем кнопки
            List<WebElement> buttons = form.findElements(By.tagName("button"));
            for (WebElement button : buttons) {
                String text = button.getText();
                if (text.contains("Продолжить") || text.contains("Пополнить") || text.contains("Далее")) {
                    System.out.println("    Кнопка: '" + text + "' - " + getElementXPath(button));
                }
            }
        }

        // Ищем радио-кнопки и селекторы услуг
        String[] serviceSelectors = {
                "input[type='radio']", "label", "span", "div[class*='service']", "div[class*='option']"
        };

        for (String selector : serviceSelectors) {
            try {
                List<WebElement> elements = driver.findElements(By.cssSelector(selector));
                for (WebElement element : elements) {
                    String text = element.getText();
                    if (text.contains("Услуги связи") || text.contains("услуги связи") ||
                            text.contains("Связь") || text.contains("связь")) {
                        System.out.println("Элемент услуги связи:");
                        System.out.println("  Текст: '" + text + "'");
                        System.out.println("  Локатор: " + getElementXPath(element));
                        System.out.println("  Тег: " + element.getTagName());
                    }
                }
            } catch (Exception e) {
                // Игнорируем
            }
        }
        System.out.println();
    }

    private void analyzePageStructure() {
        System.out.println("=== ОБЩАЯ СТРУКТУРА СТРАНИЦЫ ===");

        // Основные секции
        String[] sections = {"header", "main", "footer", "section", "div[class*='container']"};

        for (String section : sections) {
            try {
                List<WebElement> elements = driver.findElements(By.cssSelector(section));
                if (!elements.isEmpty()) {
                    System.out.println("Секция " + section + ": найдено " + elements.size() + " элементов");
                }
            } catch (Exception e) {
                // Игнорируем
            }
        }
    }

    private boolean isPaymentSystemLogo(String alt, String src, String className) {
        if (alt == null) alt = "";
        if (src == null) src = "";
        if (className == null) className = "";

        String[] paymentKeywords = {"visa", "mastercard", "belkart", "belcard", "мир", "maestro", "american express"};

        for (String keyword : paymentKeywords) {
            if (alt.toLowerCase().contains(keyword) ||
                    src.toLowerCase().contains(keyword) ||
                    className.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private String getElementXPath(WebElement element) {
        try {
            return (String) ((JavascriptExecutor) driver).executeScript(
                    "function getElementXPath(elt) {" +
                            "    var path = '';" +
                            "    for (; elt && elt.nodeType == 1; elt = elt.parentNode) {" +
                            "        var idx = getElementIdx(elt);" +
                            "        var xname = elt.tagName.toLowerCase();" +
                            "        if (idx > 1) xname += '[' + idx + ']';" +
                            "        path = '/' + xname + path;" +
                            "    }" +
                            "    return path;" +
                            "}" +
                            "function getElementIdx(elt) {" +
                            "    var count = 1;" +
                            "    for (var sib = elt.previousSibling; sib; sib = sib.previousSibling) {" +
                            "        if(sib.nodeType == 1 && sib.tagName == elt.tagName) count++;" +
                            "    }" +
                            "    return count;" +
                            "}" +
                            "return getElementXPath(arguments[0]);", element);
        } catch (Exception e) {
            return "Не удалось получить XPath";
        }
    }

    public static void main(String[] args) {
        SiteStructureAnalyzer analyzer = new SiteStructureAnalyzer();
        analyzer.analyzeMtsSite();
    }
}