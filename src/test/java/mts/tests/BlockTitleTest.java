package mts.tests;

import mts.pages.HomePage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlockTitleTest extends TestBase {

    @Test
    public void testBlockTitleExists() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        String title = homePage.getBlockTitle();
        assertTrue(title.contains("Онлайн пополнение"),
                "Блок должен содержать 'Онлайн пополнение'. Фактический текст: " + title);
        System.out.println("Заголовок блока: " + title);
    }
}