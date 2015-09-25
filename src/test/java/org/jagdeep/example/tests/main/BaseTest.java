package org.jagdeep.example.tests.main;

import static org.junit.Assert.fail;

import java.io.IOException;

import junit.framework.ComparisonFailure;

import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.jagdeep.example.automation.utils.TestAssertionLogger;
import org.jagdeep.example.automation.utils.TestProcessor;
import org.jagdeep.example.automation.utils.TestPropertiesProcessor;
import org.jagdeep.example.hsqldb.HSQLDB;

public class BaseTest {

	protected String tableName;
	protected String sql;
	protected String testExpected;
	protected String testDescription;
	protected String expectedResults;
	protected ITable actualSQLData;
	protected ITable expectedSQLResult;
	protected String assertionFailure;
	
	protected String testName;

	IDatabaseConnection iDatabaseConnection = null;
	
	TestPropertiesProcessor testPropertiesProcessor = new TestPropertiesProcessor();
	TestProcessor testProcessor = new TestProcessor();
	 
	protected BaseTest() throws IOException {
		String path = "src/test/resources/" + getTestName() + "/test.properties";
		testPropertiesProcessor.setTestProperties(path);
		this.tableName = testPropertiesProcessor.getTableName();
		this.sql =  testPropertiesProcessor.getSql();
		this.testExpected = testPropertiesProcessor.getTestExpected();
		this.testDescription = testPropertiesProcessor.getTestDesription();
		this.expectedResults = testPropertiesProcessor.getExpectedResults();	
	}

	/* 
	 * To be on safer side just initializing DatabaseOperation with NONE
	 * getSetUpOperation()
	 * getTearDownOperation()
	 */
	protected DatabaseOperation getSetUpOperation() throws Exception {
		System.out.println("In getSetUpOperation");
		return DatabaseOperation.NONE;
	}
	protected DatabaseOperation getTearDownOperation() throws Exception {
		System.out.println("In getTearDownOperation");
		return DatabaseOperation.NONE;
	}

	protected String getTestName() {
		return this.getClass().getSimpleName();
	}
	
	protected String getQualifiedTestName() {
		return this.getClass().getCanonicalName();
	}

	protected String getTestProperties() {
		return "src/main/resources/" + getTestName() + "test.properties";
	}
	
	protected void setUp() throws Exception {
		// getting the connection
		iDatabaseConnection = new DatabaseConnection(HSQLDB.getConnection());
		testProcessor.setDatabaseConnection(iDatabaseConnection);
	}

	public void tearDown() throws Exception {
		iDatabaseConnection.close();
	}

	/*
	 * This will be actual test doing database assertions and if there is any
	 * failure then it will be creating a .log file and will place this inside
	 * target folder.
	 */
	protected void test() throws Exception {
		this.actualSQLData = testProcessor.getActualResults(tableName, sql);
		this.expectedSQLResult = testProcessor.getExpectedResults(tableName, expectedResults);

		try {
			Assertion.assertEquals(expectedSQLResult, actualSQLData);
		} catch (ComparisonFailure cf) {
			assertionFailure = cf.getMessage();
			TestAssertionLogger.writeTestAssertionsFailures(getQualifiedTestName(), testDescription, testExpected, assertionFailure);
			testProcessor.exportToXML(tableName, sql, getQualifiedTestName());
			fail();
		} 
	}
}
