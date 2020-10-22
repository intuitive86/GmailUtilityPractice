package config;

public enum BrowserType {

    CHROME,
    FIREFOX;

    public static BrowserType init(String propertyName, BrowserType defaultValue) {
        String browser = System.getProperty(propertyName, defaultValue.name());
        return BrowserType.valueOf(browser);
    }
}
