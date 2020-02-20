package ru.hh.spb;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SetupClass {

    static WebDriver driver;
    @BeforeClass
    public static void setupBrowser() {
        System.setProperty("webdriver.gecko.driver", "D:\\StudyingTest\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void clearMem() {
        driver.quit();
    }
}
