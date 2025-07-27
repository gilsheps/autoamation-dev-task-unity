/*
 * *
 *  * Created by Gil on 4/9/18 4:36 PM
 *
 */

package com.home.task.unity.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.home.task.unity.base.BaseWebTestClass;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Listener implements ITestListener {

    private static String suiteName;
    private static ExtentReports extent = ExtentManager.createInstance();
    public static ExtentTest extentTest;


    @Override
    public void onTestStart(ITestResult result) {
        printToLog("onTestStart() " + result.getName());
        extentTest = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        printToLog("onTestSuccess()");
        long timeTaken = (result.getEndMillis() - result.getStartMillis()) / 1000;
        printToLog("onTestSuccess(): " + result.getName() + " Time taken:" + timeTaken + " sec");
        try {
            if (BaseWebTestClass.getWebDriver() != null) {
                takeScreenshot(result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        printToLog("onTestFailure() " + result.getName());
        try {
            if (BaseWebTestClass.getWebDriver() != null) {
                takeScreenshot(result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        result.getThrowable().printStackTrace();
    }

    private void takeScreenshot(ITestResult result) throws IOException {
        printToLog("takeScreenshot()");
        TakesScreenshot screen = (TakesScreenshot) BaseWebTestClass.getWebDriver();
        File screenshotFile = screen.getScreenshotAs(OutputType.FILE);
        Path resourceDirectory = Paths.get("src", "main", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        File localFile = new File(absolutePath + "/" + screenshotFile.getName());
        FileUtils.copyFile(screenshotFile, localFile);
        Status testStatus;
        if (result.getStatus() == ITestResult.SUCCESS) {
            testStatus = Status.PASS;
        } else {
            testStatus = Status.FAIL;
        }
        extentTest.log(testStatus, result.getThrowable()).addScreenCaptureFromPath(localFile.getAbsolutePath());
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        printToLog("onTestSkipped() " + result.getName());
        extentTest.skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        printToLog("onTestFailedButWithinSuccessPercentage() " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        suiteName = context.getSuite().getName();
        printToLog("onStart() " + context.getName() + "suiteName ==> " + suiteName);
    }

    @Override
    public void onFinish(ITestContext context) {
        printToLog("onFinish() " + context.getName());
        printToLog("PASSED TEST CASES");
        context.getPassedTests()
                .getAllResults()
                .forEach(result -> {
                    printToLog(result.getName().toUpperCase());
                });

        printToLog("FAILED TEST CASES");
        context.getFailedTests()
                .getAllResults()
                .forEach(result -> {
                    printToLog(result.getName().toUpperCase());
                });
        extent.flush();
    }

    protected void printToLog(String str) {
        String className = new Exception().getStackTrace()[1].getClassName();
        LogFactory.getLog(Listener.class.getName()).info(className.substring(className.lastIndexOf(".") + 1) + " " + str);
    }
}