package driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilits.properties.FilePath;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class DriverManager {
    private static WebDriver driver;

    private static FilePath filePath = new FilePath();


    private DriverManager() {
    }

    public static WebDriver getDriver() throws Exception {
        String driverName = filePath.propertyFile("driver");

        System.setProperty(String.format("webdriver.%s.driver", driverName), filePath.propertyFile("filePathDriver" + driverName));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        if(driverName.equals("chrome")){
            capabilities = DesiredCapabilities.chrome();
        } else if(driverName.equals("firefox")) {
            capabilities = DesiredCapabilities.firefox();
        }else throw new Exception("Incorrect browser name!");

        capabilities.setPlatform(Platform.ANY);
        capabilities.setBrowserName(driverName);

        try {
            driver = new RemoteWebDriver(new URL("http://10.8.39.53:5557/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.get(filePath.propertyFile("filePathUkr"));
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void tearDown() {
        if (!Objects.isNull(driver)) {
            driver.quit();
        }
    }

}
