package com.home.task.unity.base;

import com.home.task.unity.listeners.Listener;
import com.home.task.unity.utils.PageObjectUtils;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Base class for all tests classes
 */
@Listeners({Listener.class})
public abstract class BaseTestClass {
    protected Properties prop = new Properties();
    private FileInputStream fileInputStream = null;

    @BeforeClass(alwaysRun = true)
    protected void setupClass() {
        printToLog("setupClasssetupClass");
        try {
            fileInputStream = new FileInputStream("config.properties");
            prop.load(fileInputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void printToLog(String str) {
        String className = new Exception().getStackTrace()[1].getClassName();
        LogFactory.getLog(PageObjectUtils.class.getName()).info(className.substring(className.lastIndexOf(".") + 1) + " " + str);
    }

}
