package ru.hh.spb;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class YandexPageTest extends SetupClass {

    private static String defaultTestPage;
    private static String userXPath = "//*[@class ='button mini-suggest__button button_theme_search button_size_search i-bem button_js_inited']";

    boolean isClickable(WebElement element) {
        try {
            element.click();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        defaultTestPage = "https://yandex.ru/";
    }

    @Before
    @After
    public void defaultConditions() {
        driver.get(defaultTestPage);
    }

    /**
     * Поиск кнопки по тексту.
     * без /.. просто находит текст.
     * /.. берем родителя
     */
    @Test
    public void getSearchByText() {
        WebElement searchBtn = driver.findElement(
                new By.ByXPath("//*[text() = 'Найти']/.."));
        Assert.assertTrue(isClickable(searchBtn));
    }

    /**
     * Поиск кнопки по классу.
     */
    @Test
    public void getSearchByXPath() {
        WebElement searchBtn = driver.findElement(
                new By.ByXPath("//*[@class ='button mini-suggest__button button_theme_search button_size_search i-bem button_js_inited']")
        );
        Assert.assertTrue(isClickable(searchBtn));
    }

    /**
     * Из найденной кнопки берем текст. Указанием наследника [1]
     */
    @Test
    public void checkThatBtnIsSearch1() {
        WebElement searchBtn = driver.findElement(
                new By.ByXPath("//*[@class ='button mini-suggest__button button_theme_search button_size_search i-bem button_js_inited'][1]")
        );
        Assert.assertEquals("Найти", searchBtn.getText());
    }

    /**
     * Из найденной кнопки берем дочерний элемент, указав точку перед родителем.
     */
    @Test
    public void checkThatBtnIsSearch2() {
        WebElement searchBtn = driver.findElement(
                new By.ByXPath(".//*[@class ='button mini-suggest__button button_theme_search button_size_search i-bem button_js_inited']")
        );
        Assert.assertEquals("Найти", searchBtn.getText());
    }

    /**
     * Проверяем пользовательский XPath путем поиска фразы I Love Luxoft
     */
    @Test
    public void checkUserSearch() {
        WebElement searchBtn = driver.findElement(
                new By.ByXPath(userXPath)
        );
        WebElement searchInput = driver.findElement(new By.ByXPath("//input[@class = 'input__control input__input mini-suggest__input']"));
        searchInput.sendKeys("I love Luxoft");
        Assert.assertTrue(isClickable(searchBtn));
    }


}
