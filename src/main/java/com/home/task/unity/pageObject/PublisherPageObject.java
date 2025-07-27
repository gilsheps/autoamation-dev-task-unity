package com.home.task.unity.pageObject;

import com.home.task.unity.base.BaseWebTestClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PublisherPageObject extends BaseWebTestClass {
    @FindBy(css = "button[href='/admin/resources/Publisher/actions/new']")
    WebElement createRecordButton;
    @FindBy(css = "#name")
    WebElement nameInput;
    @FindBy(css = "#email")
    WebElement emailInput;
    @FindBy(css = "button[type='submit']")
    WebElement saveButton;
    @FindBy(xpath = "//td[@data-property-name='name']")
    WebElement nameTd;
    @FindBy(xpath = "//td[@data-property-name='email']")
    WebElement emailTd;


    public PublisherPageObject createPublisherRecord(String name, String email) {
        clickOnWebElement(createRecordButton);
        sendKeys(nameInput, name);
        sendKeys(emailInput, email);
        clickOnWebElement(saveButton);
        return this;
    }

    public void verifyPublisherCreated(String name, String email) {
        verifyWebElementText(nameTd, name);
        verifyWebElementText(emailTd, email);
    }
}
