package com.home.task.unity.base;

import com.home.task.unity.pageObject.*;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static com.home.task.unity.utils.SoftAssertLocal.softAssertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public abstract class BaseWebTestClass extends BaseTestClass {
    protected static WebDriver webDriver;
    protected static Wait<WebDriver> uiWait;


    @BeforeMethod(alwaysRun = true)
    public void createDriver() {
        printToLog("BeforeMethod - createDriver with browser");
        printToLog("createDriver(), osSystem is => " + System.getProperty("os.name").toLowerCase());
        printToLog("start chrome configuration");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL)
                .addArguments("--window-size=1920,1080");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("http://localhost:3000/admin/login");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        uiWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        loginPageObject().loginAdmin();
    }

    @AfterMethod(alwaysRun = true)
    public void after(ITestResult result) {
        try {
            printToLog("webDriver.quit()");
            webDriver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        printToLog("afterClass()");
    }

    @AfterSuite(alwaysRun = true)
    public void AfterSuite() {
        printToLog("AfterSuite()");
    }

    protected LoginPageObject loginPageObject() {
        return getInstance(LoginPageObject.class);
    }

    protected MainPage mainPageObject() {
        return getInstance(MainPage.class);
    }

    protected MenuPageObject menuPageObject() {
        return getInstance(MenuPageObject.class);
    }

    protected PublisherPageObject publisherPageObject() {
        return getInstance(PublisherPageObject.class);
    }

    protected PostPageObject postPageObject() {
        return getInstance(PostPageObject.class);
    }

    protected CreateNewPost createNewPost() {
        return getInstance(CreateNewPost.class);
    }

    protected <T> T getInstance(Class<T> pageClass) {
        printToLog("getInstance of class " + pageClass.getSimpleName());
        return PageFactory.initElements(webDriver, pageClass);
    }

    protected void clickOnWebElement(WebElement webElement) {
        printToLog("clickOnWebElement() " + webElement.toString());
        try {
//            visibilityOf(webElement);
//            elementToBeClickable(webElement);
            webElement.click();
        } catch (ElementClickInterceptedException e) {
        }
    }

    protected void sendKeys(WebElement webElement, String sendKeysStr) {
        printToLog("sendkeys to WebElement " + webElement.toString() + " with text " + sendKeysStr);
        webElement.sendKeys(sendKeysStr);
        ExpectedCondition<Boolean> isTextExists = textToBePresentInElement(webElement, sendKeysStr);
        printToLog(isTextExists.toString());
    }

    protected <T> T sendKeys(Class<T> pageClass, WebElement webElement, String sendKeysStr) {
        sendKeys(webElement, sendKeysStr);
        return (T) pageClass;
    }


    protected String getTextFromWebElementWithOutLowerCase(WebElement webElement) {
        printToLog("getTextFromWebElement() for: " + webElement);
        return webElement.getText();
    }

    protected void verifyWebElementText(WebElement webElement, String strToCompare) {
        printToLog("verifyWebElementText");
        softAssertTrue(strToCompare + " not equals", getTextFromWebElementWithOutLowerCase(webElement).equals(strToCompare));
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }
}
