/**
 * @author jagdeepjain
 */
package org.jagdeep.example.tests;

import junit.framework.ComparisonFailure;
import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import org.jagdeep.example.hsqldb.HSQLDB;
import org.jagdeep.example.tests.asserts.log.AssertionLogger;
import org.jagdeep.example.tests.config.Config;
import org.jagdeep.example.tests.util.DBUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * Test class for verifying employee records in the HSQLDB database.
 */
public class IDTest extends BaseTest {
    private final HSQLDB hsqldb = HSQLDB.getInstance();
    private final DBUtil DBUtil = new DBUtil();
    private final String actualTableName;
    private final String actualSql;
    private final String testExpectedDescription;
    private final String testDescription;
    private final String expectedResults;
    protected ITable actualSQLData;
    protected ITable expectedSQLResult;

    /**
     * Initializes configuration and sets up test parameters.
     *
     * @throws IOException if there is an error reading the configuration file.
     */
    public IDTest() throws IOException {
        Config config = new Config();
        String path = "src/test/resources/test.properties";
        config.setConfig(path);
        this.actualTableName = config.getEmployeeIDTestActualTableName();
        this.actualSql = config.getEmployeeIDTestActualSql();
        this.testExpectedDescription = config.getEmployeeIDExpectedResults();
        this.testDescription = config.getEmployeeIDTestDescription();
        this.expectedResults = config.getEmployeeIDExpectedResults();
    }

    /**
     * Sets up the test environment before each test.
     *
     * @throws Exception if there is an error setting up the database.
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        DBUtil.setDatabaseConnection(iDatabaseConnection);
        hsqldb.create("create table employee " +
                "(id INTEGER, first_name VARCHAR(50), last_name VARCHAR(50))");
        hsqldb.insert("insert into employee " +
                        "(id, first_name, last_name) values (?, ?, ?)",
                1001, "John", "Doe");
    }

    /**
     * Test method for verifying the employee record with ID 1001.
     *
     * @throws Exception if there is an error executing the test.
     * And if the test fails, the method exports the actual results to an XML file.
     */
    @Test
    public void testEmployeeRecordForID1001() throws Exception {
        actualSQLData = DBUtil.getActualResults(actualTableName, actualSql);
        expectedSQLResult = DBUtil.getExpectedResults(actualTableName, expectedResults);

        try {
            Assertion.assertEquals(expectedSQLResult, actualSQLData);
        } catch (ComparisonFailure cf) {
            String assertionFailure = cf.getMessage();
            AssertionLogger.writeTestAssertionsFailures(getQualifiedTestName(),
                    testDescription, testExpectedDescription, assertionFailure);
            DBUtil.exportToXML(actualTableName, actualSql, getQualifiedTestName());
            fail();
        }
    }

    /**
     * Cleans up the test environment after each test.
     *
     * @throws Exception if there is an error during cleanup.
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}