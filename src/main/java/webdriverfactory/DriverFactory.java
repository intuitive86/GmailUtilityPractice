package webdriverfactory;

/*public class DriverFactory {

    static WebDriver driver;
    private static String url;

    public static WebDriver getWebDriver() {

        setUrl();

        if (Config.CHROME.contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (Config.FIREFOX.contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new
                    FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private static void setUrl() {
        url = Environment.DEV1.getUrl();
    }

    @BeforeTest
    public static WebDriver getDriver() {
        driver = getWebDriver();
        driver.get(url);
        return driver;
    }

    @AfterTest
    public static void tearDown() {
        driver = getDriver();
        driver.close();
    }
}*/

