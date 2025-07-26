/*
 * *
 *  * Created by Gil on 5/14/18 3:17 PM
 *
 */

/*
 * *
 *  * Created by Gil on 3/14/18 10:37 PM
 *
 */

package com.home.task.unity.servers;

import java.io.IOException;

/**
 * This class is to run selenium server from intellij
 */
public class SeleniumServer {

    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("selenium-server");
        processBuilder.redirectErrorStream(true); // merge stderr with stdout

        try {
            Process process = processBuilder.start();

            // Print output to console
            try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            System.out.println("Selenium Server exited with code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt(); // restore interrupt status if interrupted
        }
    }
}
