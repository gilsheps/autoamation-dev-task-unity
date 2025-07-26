package com.home.task.unity.utils;

import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;

import static com.home.task.unity.utils.SoftAssertLocal.softAssertTrue;

/**
 * Utils class for all function that not need instance of object, all page object class should use it
 */
public class PageObjectUtils {
    //    private static Log logger = LogFactory.getLog(PageObjectUtils.class);
    private static ITestContext context;


    public static void setContext(ITestContext context) {
        PageObjectUtils.context = context;
    }


    /**
     * one function to print to logs, all page objects should use it
     */
    public static void printToLog(String str) {
        String className = new Exception().getStackTrace()[1].getClassName();
        LogFactory.getLog(PageObjectUtils.class.getName()).info(className.substring(className.lastIndexOf(".") + 1) + " " + str);
    }

    /**
     * One function to click on WebElement
     */
    public static void clickOnWebElement(WebElement webElement) {
        printToLog("clickOnWebElement() " + webElement.toString());
        try {
//            visibilityOf(webElement);
//            elementToBeClickable(webElement);
            webElement.click();
        } catch (ElementClickInterceptedException e) {
//            JavascriptExecutor js = (JavascriptExecutor) DriversUtils.driversUtils.getWebDriver();
//            js.executeScript("arguments[0].click()", webElement);
        }
    }

    public static void sendKeys(WebElement webElement, String sendKeysStr) {
        printToLog("sendkeys to WebElement " + webElement.toString() + " with text " + sendKeysStr);
        webElement.sendKeys(sendKeysStr);
//        textToBePresentInElement(webElement, sendKeysStr);
    }

    public static <T> T sendKeys(Class<T> pageClass, WebElement webElement, String sendKeysStr) {
        sendKeys(webElement, sendKeysStr);
        return (T) pageClass;
    }


    public static String getTextFromWebElementWithOutLowerCase(WebElement webElement) {
        printToLog("getTextFromWebElement() for: " + webElement);
        return webElement.getText();
    }

    public static void verifyWebElementText(WebElement webElement, String strToCompare) {
        printToLog("verifyWebElementText");
        softAssertTrue(strToCompare + " not equals", getTextFromWebElementWithOutLowerCase(webElement).equals(strToCompare));
    }
}
