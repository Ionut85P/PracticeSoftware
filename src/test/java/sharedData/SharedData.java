package sharedData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SharedData {
    private WebDriver  driver;

    @BeforeMethod
    public void prepareEnviroment(){
        driver = new ChromeDriver();
        driver.get("https://practicesoftwaretesting.com/auth/login");
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void clearEnviroment(){
        driver.quit();
    }

    public WebDriver getDriver(){
        return driver;
    }

}
