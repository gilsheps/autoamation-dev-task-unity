/*
 * *
 *  * Created by Gil on 6/23/19 4:04 PM
 *
 */

package com.home.task.unity.utils;

import org.apache.tika.utils.ExceptionUtils;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class SoftAssertLocal implements IHookable {

    private static final String SOFT_ASSERT = "softAssert";

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        SoftAssert softAssert = new SoftAssert();
        testResult.setAttribute(SOFT_ASSERT, softAssert);
        callBack.runTestMethod(testResult);
        softAssert = getSoftAssert(testResult);
        if (!testResult.isSuccess()) {
            Throwable throwable = testResult.getThrowable();
            if (null != throwable) {
                if (null != throwable.getCause()) {
                    throwable = throwable.getCause();
                }
                softAssert.assertNull(throwable, ExceptionUtils.getStackTrace(throwable));
            }
        }
        softAssert.assertAll();
    }

    public static SoftAssert getSoftAssert() {
        return getSoftAssert(Reporter.getCurrentTestResult());
    }

    private static SoftAssert getSoftAssert(ITestResult result) {
        Object object = result.getAttribute(SOFT_ASSERT);
        if (object instanceof SoftAssert) {
            return (SoftAssert) object;
        }
        throw new IllegalStateException("Could not find a soft assertion object");
    }

    public static void softAssertTrue(String message, boolean booleanToAssert) {
        String className = new Exception().getStackTrace()[1].getClassName();
        String methodName = new Exception().getStackTrace()[1].getMethodName();
        getSoftAssert().assertTrue(booleanToAssert, className.substring(className.lastIndexOf(".") + 1) + " - " + methodName + " " + message);
    }

}
