package org.jagdeep.example.tests;

import java.io.IOException;
import junit.framework.ComparisonFailure;
import org.dbunit.Assertion;
import org.jagdeep.example.tests.asserts.log.AssertionLogger;
import org.jagdeep.example.tests.processor.TestProcessor;
import org.jagdeep.example.tests.db.hsqldb.HSQLDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

public class IDTest extends BaseTest {
	private final HSQLDB hsqldb = HSQLDB.getInstance();
	private final TestProcessor testProcessor = new TestProcessor();

	public IDTest() throws IOException {
		super();
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		testProcessor.setDatabaseConnection(iDatabaseConnection);
	}
	@Test
	public void test() throws Exception {
		hsqldb.query("create table employee " +
				"(id INTEGER, first_name VARCHAR(50), last_name VARCHAR(50))");
		hsqldb.insert("insert into employee " +
				"(id, first_name, last_name) values (?, ?, ?)",
				1001, "John", "Doe");
		actualSQLData = testProcessor.getActualResults(tableName, sql);
		expectedSQLResult = testProcessor.getExpectedResults(tableName, expectedResults);
		try {
			Assertion.assertEquals(expectedSQLResult, actualSQLData);
		} catch (ComparisonFailure cf) {
			assertionFailure = cf.getMessage();
			AssertionLogger.writeTestAssertionsFailures(getQualifiedTestName(),
					testDescription, testExpected, assertionFailure);
			testProcessor.exportToXML(tableName, sql, getQualifiedTestName());
			fail();
		}
	}
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
}