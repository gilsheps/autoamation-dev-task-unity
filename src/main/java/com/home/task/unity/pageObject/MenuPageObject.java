package com.home.task.unity.pageObject;

import com.home.task.unity.base.BaseWebTestClass;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPageObject extends BaseWebTestClass {

    @FindBy(css = ".adminjs_NavBar .adminjs_Icon")
    WebElement menuIcon;
    @FindBy(xpath = "//div[contains(text(), 'Happy Folder')]")
    WebElement happyFolderButton;
    @FindBy(css = "a[href='/admin/resources/Publisher']")
    WebElement publisherButton;
    @FindBy(css = "a[href='/admin/resources/Profile']")
    WebElement profileButton;
    @FindBy(css = "a[href='/admin/resources/Post']")
    WebElement postButton;
    @FindBy(css = "a[href='/admin/pages/dashboard']")
    WebElement dashboardButton;
    @FindBy(css = "a[href='/admin/pages/designSystemExamples']")
    WebElement designSystemExamplesButton;

    private void openMenuTab() {
        try {
            if (menuIcon.isDisplayed()) {
                clickOnWebElement(menuIcon);
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        clickOnWebElement(happyFolderButton);
    }

    public void clickOnPublisher() {
        openMenuTab();
        clickOnWebElement(publisherButton);
    }

    public void clickOnPost() {
        openMenuTab();
        clickOnWebElement(postButton);
    }
}
