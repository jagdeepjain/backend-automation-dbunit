/**
 * @author jagdeepjain
 *
 */
package org.jagdeep.example.tests.asserts.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;

public class AssertionLogger {
    private static final Logger logger = Logger.getLogger(AssertionLogger.class.getName());
    public static void writeTestAssertionsFailures(String fileName,
            String testDescription, String testExpected, String assertionFailure) throws IOException {
        File file = new File(fileName + ".log");
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        try {
            output.write("Test Description:");
            output.append(System.lineSeparator());
            output.write(testDescription);
            output.append(System.lineSeparator());
            output.append(System.lineSeparator());
            output.write("Test Expected:");
            output.append(System.lineSeparator());
            output.write(testExpected);
            output.append(System.lineSeparator());
            output.append(System.lineSeparator());
            output.write("Assertions Failure:");
            output.append(System.lineSeparator());
            output.write(assertionFailure);
        } catch (IOException e) {
            logger.severe("IOException occurred: " + e.getMessage());
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                logger.severe("Failed to close BufferedWriter: " + e.getMessage());
            }
        }
        try {
            FileUtils.rename(file, new File("target/surefire-reports/" + fileName + ".log"));
        } catch (IOException e) {
            logger.severe("Failed to rename file: " + e.getMessage());
        }
    }
}
