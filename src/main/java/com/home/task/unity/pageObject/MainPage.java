package com.home.task.unity.pageObject;

import com.home.task.unity.base.BaseWebTestClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.home.task.unity.utils.PageObjectUtils.getTextFromWebElementWithOutLowerCase;
import static com.home.task.unity.utils.PageObjectUtils.verifyWebElementText;
import static com.home.task.unity.utils.SoftAssertLocal.softAssertTrue;

public class MainPage extends BaseWebTestClass {

    @FindAll(@FindBy(css = ".adminjs_Header"))
    List<WebElement> headerElements;
    @FindBy(css = ".adminjs_H2")
    WebElement adminjs_H2;
    @FindBy(css = ".adminjs_H4")
    WebElement adminjs_H4;


    public void verifyLogin() {
        verifyWebElementText(adminjs_H2, "Welcome, Candidate!");
        softAssertTrue("", getTextFromWebElementWithOutLowerCase(adminjs_H2).equals("Welcome, Candidate!1"));
        softAssertTrue("", getTextFromWebElementWithOutLowerCase(adminjs_H4).equals("Happy coding and good luck, may the Force be with you!"));
    }

}
