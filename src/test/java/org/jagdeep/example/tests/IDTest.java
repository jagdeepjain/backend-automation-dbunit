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

    @Before
    public void setUp() throws Exception {
        super.setUp();
        DBUtil.setDatabaseConnection(iDatabaseConnection);
    }

    @Test
    public void testEmployeeRecordForID1001() throws Exception {
        hsqldb.create("create table employee " +
                "(id INTEGER, first_name VARCHAR(50), last_name VARCHAR(50))");
        hsqldb.insert("insert into employee " +
                        "(id, first_name, last_name) values (?, ?, ?)",
                1001, "John", "Doe");
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

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}