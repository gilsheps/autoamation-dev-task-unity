/*
 * *
 *  * Created by Gil on 9/26/18 3:49 PM
 *
 */

package com.home.task.unity.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.Platform;

import java.io.File;


public class ExtentManager {

    private static ExtentReports extent = new ExtentReports();
    private static Platform platform;
    private static final String reportFileName = "ExtentReports.html";
    private static final String macPath = System.getProperty("user.dir") + "/TestReport";
    private static final String windowsPath = System.getProperty("user.dir") + "\\TestReport";
    private static final String macReportFileLoc = macPath + "/" + reportFileName;
    private static final String winReportFileLoc = windowsPath + "\\" + reportFileName;


    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {
        platform = getCurrentPlatform();
        String fileName = getReportFileLocation(platform);
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);
        extentSparkReporter.config().setOfflineMode(false);
        extentSparkReporter.config().setProtocol(Protocol.HTTPS);
        extentSparkReporter.config().setTimelineEnabled(true);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setDocumentTitle(fileName);
        extentSparkReporter.config().setEncoding("utf-8");
        extentSparkReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(extentSparkReporter);

        return extent;
    }

    private static String getReportFileLocation(Platform platform) {
        String reportFileLocation = null;
        switch (platform) {
            case MAC:
                reportFileLocation = macReportFileLoc;
                createReportPath(macPath);
                break;
            case WINDOWS:
                reportFileLocation = winReportFileLoc;
                createReportPath(windowsPath);
                break;
            default:
                break;
        }
        return reportFileLocation;
    }

    private static void createReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!");
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }

    private static Platform getCurrentPlatform() {
        if (platform == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                platform = Platform.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                platform = Platform.LINUX;
            } else if (operSys.contains("mac")) {
                platform = Platform.MAC;
            }
        }
        return platform;
    }
}
