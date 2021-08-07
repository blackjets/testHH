package ru.hh.spb;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.opera.OperaDriver;

import java.io.File;

public abstract class SetupClass {

    static ChromeDriver driver;

    @BeforeClass
    public static void setupBrowser() {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("D:\\StudyingTest\\operadriver_win64\\operadriver.exe")).build();
        driver = new ChromeDriver(service);
    }

    @AfterClass
    public static void clearMem() {
        driver.quit();
    }
}
