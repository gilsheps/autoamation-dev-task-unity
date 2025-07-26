package com.home.task.unity.pageObject;

import com.home.task.unity.base.BaseWebTestClass;
import com.home.task.unity.utils.Enums;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CreateNewPost extends BaseWebTestClass {

    @FindBy(css = "#title")
    WebElement titleInput;
    @FindBy(css = "#content")
    WebElement contentInput;
    @FindBy(xpath = "//button[@data-testid='someJson-add']")
    WebElement addNewItemJson;
    @FindBy(xpath = "//section[@data-css='Post-edit-status']//input")
    WebElement statusSelect;
    @FindBy(xpath = "//div[contains(text(), 'ACTIVE')]")
    WebElement reactSelectActive;
    @FindBy(xpath = "//div[contains(text(), 'REMOVE')]")
    WebElement reactSelectRemove;
    @FindBy(css = "#published")
    WebElement publishedCheckBox;
    @FindBy(xpath = "//section[@data-css='Post-edit-publisher']//input")
    WebElement publisherSelect;
    @FindBy(css = "button[type='submit']")
    WebElement saveButton;


    public CreateNewPost clickStatusSelect() {
        clickOnWebElement(statusSelect);
        return this;
    }

    public CreateNewPost clickReactSelectActive() {
        clickOnWebElement(reactSelectActive);
        return this;
    }

    public CreateNewPost clickSaveButton() {
        clickOnWebElement(saveButton);
        return this;
    }

    public CreateNewPost changePostStatus(Enums.PostStatus postStatus) {
        clickStatusSelect();
        switch (postStatus) {
            case ACTIVE:
                clickReactSelectActive();
            case REMOVED:
                clickOnWebElement(reactSelectRemove);
        }
        return this;
    }

    public CreateNewPost clickAddNewItemJson() {
        clickOnWebElement(addNewItemJson);
        return this;
    }

    public CreateNewPost clickPublishedCheckBox() {
        new Actions(webDriver).moveToElement(publishedCheckBox).perform();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click()", publishedCheckBox);
        return this;
    }

    public void createPost(String titleStr, String contentStr, String email) {
        sendKeys(titleInput, titleStr);
        clickAddNewItemJson();
        sendKeys(contentInput, contentStr);
        clickStatusSelect().clickReactSelectActive().clickPublishedCheckBox();
        clickOnWebElement(publisherSelect);
        clickOnWebElement(webDriver.findElement(By.xpath("//div[contains(text(), '" + email + "')]")));
        clickSaveButton();
    }
}
