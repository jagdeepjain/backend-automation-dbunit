package org.jagdeep.example.tests;

import java.io.IOException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.ITable;
import org.jagdeep.example.tests.config.Config;
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
	 IDatabaseConnection iDatabaseConnection = null;
	final Config config = new Config();
	protected BaseTest() throws IOException {
		String path = "src/test/resources/test.properties";
		config.setTestConfig(path);
		this.tableName = config.getTableName();
		this.sql =  config.getSql();
		this.testExpected = config.getTestExpected();
		this.testDescription = config.getTestDescription();
		this.expectedResults = config.getExpectedResults();
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
