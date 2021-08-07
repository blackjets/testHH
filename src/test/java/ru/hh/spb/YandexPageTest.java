package ru.hh.spb;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class YandexPageTest extends SetupClass {

    private static String defaultTestPage;

    boolean isClickable (WebElement element){
        try{
            element.click();
        }catch (Exception ex){
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

    @Test
    public void getSearchByText() {
        WebElement searchBtn = driver.findElement(
                new By.ByXPath("//*[text() = 'Найти']/.."));
        Assert.assertTrue(isClickable(searchBtn));
    }
    @Test
    public void getSearchByXPath(){
        WebElement searchBtn = driver.findElement(
                new By.ByXPath("//*[@class ='button mini-suggest__button button_theme_search button_size_search i-bem button_js_inited']")
        );
        Assert.assertTrue(isClickable(searchBtn));
    }
    @Test
    public void checkThatBtnIsSearch1(){
        WebElement searchBtn = driver.findElement(
                new By.ByXPath("//*[@class ='button mini-suggest__button button_theme_search button_size_search i-bem button_js_inited'][1]")
        );
        Assert.assertEquals("Найти", searchBtn.getText());
    }
    @Test
    public void checkThatBtnIsSearch2(){
        WebElement searchBtn = driver.findElement(
                new By.ByXPath(".//*[@class ='button mini-suggest__button button_theme_search button_size_search i-bem button_js_inited']")
        );
        Assert.assertEquals("Найти", searchBtn.getText());
    }


}
