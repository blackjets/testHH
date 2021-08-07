package ru.hh.spb;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;


public class HHPageTest extends SetupClass {

    private static String defaultTestPage;

    @BeforeClass
    public static void beforeClass() throws Exception {
        defaultTestPage = "https://spb.hh.ru/";
    }

    @Before
    @After
    public void defaultConditions() {
        driver.get(defaultTestPage);
    }

    @Test
    public void testTitle() {
        Assert.assertTrue(driver.getTitle().equals("Работа в Санкт-Петербурге, поиск персонала и публикация вакансий - spb.hh.ru"));
    }

    @Test
    public void checkSearch() {
        testSearch("Java Developer");
        testSearch("C++ Developer");
        testSearch("Go Developer");
    }

    private void testSearch(String searchText) {
        WebElement searchTextEdit = driver.findElement(new By.ByCssSelector("input[data-qa=\"search-input\"]"));
        WebElement button = driver.findElement(new By.ByCssSelector("button[data-qa=\"search-button\"]"));
        WebElement header;

        searchTextEdit.click();
        searchTextEdit.sendKeys(searchText);
        button.click();
    }

    @Ignore
    @Test
    public void checkAllBtns() {
        //Во-первых, может в список попасть одинаковые кнопки,
        //Во-вторых это лучше сделать единым XPATH
        //В-третьих, некоторые элементы не успевают появиться на экране, поэтому тест пока не рабочий.
        // Да и идея прокликать сразу все кнопки - дичь

        List<WebElement> buttons = driver.findElements(new By.ByTagName("button"));
        buttons.addAll(driver.findElements(new By.ByXPath("//@button[contains(@class,'button')]")));

        for (WebElement btn : buttons) {
            try {
                driver.get(defaultTestPage);
                Assert.assertTrue(isClickable(btn));
            } catch (AssertionError err) {
                System.out.println("Button not clickable: " + btn.getTagName());
            }
            catch (StaleElementReferenceException err){
                System.out.println("element is not attached to the page document: " + btn.getTagName());
            }
        }
    }

    boolean isClickable(WebElement element) {
        try {
            element.click();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }


}
