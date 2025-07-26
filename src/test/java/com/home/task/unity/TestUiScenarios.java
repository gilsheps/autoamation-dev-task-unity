package com.home.task.unity;

import com.home.task.unity.base.BaseWebTestClass;
import com.home.task.unity.utils.Enums;
import org.testng.annotations.Test;

public class TestUiScenarios extends BaseWebTestClass {

    @Test
    public void testCreatePublisherEntity() {
        String name = prop.getProperty("uiName");
        String email = prop.getProperty("uiEmail");
        menuPageObject().clickOnPublisher();
        publisherPageObject().createPublisherRecord(name, email).verifyPublisherCreated(name, email);
    }

    @Test(dependsOnMethods = {"testCreatePublisherEntity"})
    public void testCreatePost() {
        menuPageObject().clickOnPost();
        postPageObject().clickCreateFirstPostButton();
        createNewPost().createPost(prop.getProperty("postTitle"), "", prop.getProperty("uiEmail"));
        postPageObject().verifyPostCreated(prop.getProperty("postTitle"), Enums.PostStatus.ACTIVE);
    }

    @Test(dependsOnMethods = {"testCreatePost"})
    public void testEditPost() {
        menuPageObject().clickOnPost();
        postPageObject().clickOnEditPost();
        createNewPost().changePostStatus(Enums.PostStatus.REMOVED).clickSaveButton();
        postPageObject().verifyPostCreated(prop.getProperty("postTitle"), Enums.PostStatus.REMOVED);
    }
}
