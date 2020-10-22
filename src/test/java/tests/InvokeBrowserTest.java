package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import webdriverfactory.BrowserFactory;

public class InvokeBrowserTest extends BrowserFactory{


    static WebDriver driver = BrowserFactory.getWebDriverManager();

    @Test
    public void chromeLaunchTest() {
        driver.get("https://github.com/bonigarcia/webdrivermanager");
    }
}
