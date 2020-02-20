package ru.hh.spb;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class MainPageTest extends SetupClass {

    String defaultTestPage = "https://spb.hh.ru/";

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

        header = driver.findElement(new By.ByCssSelector("h1[data-qa=\"page-title\"]"));

        Assert.assertTrue(header.getText().contains(searchText));

    }

    @Test
    public void checkAllBtns() {
        List<WebElement> buttons = driver.findElements(new By.ByTagName("button"));
        for (WebElement btn : buttons) {
            if (!btn.getText().isEmpty()) checkBtn(btn);
        }
    }

    private void checkBtn(WebElement button) {
        button.click();
        Assert.assertTrue(button.getText() + " check...", !driver.getCurrentUrl().equals(defaultTestPage));
    }


}
