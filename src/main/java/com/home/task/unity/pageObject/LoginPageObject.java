package com.home.task.unity.pageObject;

import com.home.task.unity.base.BaseWebTestClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPageObject extends BaseWebTestClass {

    @FindBy(css = "[name=email]")
    WebElement emailInputField;
    @FindBy(css = "[name=password]")
    WebElement passwordInputField;
    @FindBy(css = ".adminjs_Button")
    WebElement loginButton;


    public void loginAdmin() {
        verifyWebElementText(emailInputField, "admin@example.com");
        verifyWebElementText(passwordInputField, "password");
        loginButton.click();
    }
}
