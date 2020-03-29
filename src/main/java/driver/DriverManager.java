package driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilits.properties.FilePath;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class DriverManager {
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private static FilePath filePath = new FilePath();
    private static DesiredCapabilities capabilities;


    private DriverManager() {
    }

    public static WebDriver getDriver(String driverName) throws Exception {
        WebDriver driver;
        System.setProperty(String.format("webdriver.%s.driver", driverName), filePath.propertyFile("filePathDriver" + driverName));

        if(driverName.equals("chrome")){
           driver = setChromeDriver(null);
        } else if(driverName.equals("firefox")) {
           driver = setFirefoxDriver(null);
        }else throw new Exception("Incorrect browser name!");

        driver.get(filePath.propertyFile("filePathUkr"));
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driverPool.set(driver);
        return driverPool.get();
    }

    public static void tearDown() {
        if (!Objects.isNull(driverPool)) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }

    private static WebDriver setChromeDriver(WebDriver driver) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-extensions");
        options.addArguments("--no-sandbox");
        capabilities = DesiredCapabilities.chrome();
        capabilities.setPlatform(Platform.ANY);
        capabilities.setBrowserName("chrome");
        options.merge(capabilities);
        try {
           driver =  new RemoteWebDriver(new URL(filePath.propertyFile("hubURL")), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static WebDriver setFirefoxDriver(WebDriver driver) {
        capabilities = DesiredCapabilities.firefox();
        capabilities.setPlatform(Platform.ANY);
        capabilities.setBrowserName("firefox");
        try {
            driver = new RemoteWebDriver(new URL(filePath.propertyFile("hubURL")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

}
