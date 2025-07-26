package com.home.task.unity.pageObject;

import com.home.task.unity.base.BaseWebTestClass;
import com.home.task.unity.utils.Enums;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PostPageObject extends BaseWebTestClass {
    CreateNewPost createNewPost;
    @FindBy(css = "button[href='/admin/resources/Post/actions/new']")
    WebElement createFirstPostButton;
    @FindBy(xpath = "//td[@data-property-name='title']")
    WebElement titleTd;
    @FindBy(xpath = "//td[@data-property-name='status']")
    WebElement statusTd;
    @FindBy(xpath = "//td[@data-property-name='published']")
    WebElement publishedTd;
    @FindBy(xpath = "//a[@data-testid='actions-dropdown']")
    WebElement menuButton;
    @FindBy(xpath = "//a[@data-css='Post-edit-button']")
    WebElement editPostButton;


    public PostPageObject sendKey(WebElement webElement, String str) {
        super.sendKeys(PostPageObject.class, webElement, str);
        return this;
    }

    public PostPageObject clickCreateFirstPostButton() {
        clickOnWebElement(createFirstPostButton);
        return this;
    }


    public void verifyPostCreated(String title, Enums.PostStatus status) {
        printToLog("verifyPostCreated, title: " + title + " status: " + status.toString());
        verifyWebElementText(titleTd, title);
        verifyWebElementText(statusTd, status.toString());
        verifyWebElementText(publishedTd, "Yes");
    }

    public PostPageObject clickOnEditPost() {
        new Actions(webDriver).moveToElement(menuButton).perform();
        clickOnWebElement(editPostButton);
        return this;
    }
}
