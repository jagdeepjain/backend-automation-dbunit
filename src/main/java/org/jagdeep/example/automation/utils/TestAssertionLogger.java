/**
 * @author jagdeepjain
 *
 */
package org.jagdeep.example.automation.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;

public class TestAssertionLogger {
    public static void writeTestAssertionsFailures(String fileName,
            String testDescription, String testExpected, String assertionFailure)
            throws IOException {

        BufferedWriter output = null;

        File file = new File(fileName + ".log");

        try {
            output = new BufferedWriter(new FileWriter(file));
            output.write("Test Description:");
            output.append(System.getProperty("line.separator"));
            output.write(testDescription);
            output.append(System.getProperty("line.separator"));
            output.append(System.getProperty("line.separator"));
            output.write("Test Expected:");
            output.append(System.getProperty("line.separator"));
            output.write(testExpected);
            output.append(System.getProperty("line.separator"));
            output.append(System.getProperty("line.separator"));
            output.write("Assertions Failure:");
            output.append(System.getProperty("line.separator"));
            output.write(assertionFailure);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null)
                output.close();
        }
        
        FileUtils.rename(file, new File("target/surefire-reports/" + fileName + ".log"));
        
        
    }
}
