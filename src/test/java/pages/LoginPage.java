package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.sql.DriverManager.getDriver;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy (id = "email")
    private WebElement emailElement;
    @FindBy (id = "email")
            private WebElement passwordElement;
    @FindBy (className = "btnSubmit")
    private WebElement loginButton;

    public void loginProcess(String email, String password ){

        emailElement.sendKeys(email);


        passwordElement.sendKeys(password);


        loginButton.click();

    }

}
