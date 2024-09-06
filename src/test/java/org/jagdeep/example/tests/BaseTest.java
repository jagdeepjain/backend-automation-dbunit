package org.jagdeep.example.tests;

import java.io.IOException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.ITable;
import org.jagdeep.example.tests.properties.Properties;
import org.jagdeep.example.tests.db.hsqldb.HSQLDB;

public class BaseTest {
	protected String tableName;
	protected String sql;
	protected String testExpected;
	protected String testDescription;
	protected String expectedResults;
	protected ITable actualSQLData;
	protected ITable expectedSQLResult;
	protected String assertionFailure;
	 IDatabaseConnection iDatabaseConnection = null;
	final Properties properties = new Properties();
	protected BaseTest() throws IOException {
		String path = "src/test/resources/" + getTestName() + "/test.properties";
		properties.setTestProperties(path);
		this.tableName = properties.getTableName();
		this.sql =  properties.getSql();
		this.testExpected = properties.getTestExpected();
		this.testDescription = properties.getTestDescription();
		this.expectedResults = properties.getExpectedResults();
	}
	protected String getTestName() {
		return this.getClass().getSimpleName();
	}
	protected String getQualifiedTestName() {
		return this.getClass().getCanonicalName();
	}
	protected void setUp() throws Exception {
		iDatabaseConnection = new DatabaseConnection(HSQLDB.getConnection());
		iDatabaseConnection.getConfig().
				setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
						new org.dbunit.ext.hsqldb.HsqldbDataTypeFactory());
	}
	public void tearDown() throws Exception {
		iDatabaseConnection.close();
	}
}
