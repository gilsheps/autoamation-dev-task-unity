package com.home.task.unity.pageObject;

import com.home.task.unity.base.BaseWebTestClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.home.task.unity.utils.PageObjectUtils.verifyWebElementText;

public class LoginPageObject extends BaseWebTestClass {

    @FindBy(css = "[name=email]")
    WebElement emailInputField;
    @FindBy(css = "[name=password]")
    WebElement passwordInputField;
    @FindBy(css = ".adminjs_Button")
    WebElement loginButton;


    public void loginAdmin() {
//        File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

        verifyWebElementText(emailInputField, "admin@example.com");
        verifyWebElementText(passwordInputField, "password");
        loginButton.click();
//        mainPageObject().verifyLogin();
    }
}
