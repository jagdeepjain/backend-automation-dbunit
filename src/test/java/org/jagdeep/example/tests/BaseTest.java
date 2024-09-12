package org.jagdeep.example.tests;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.ITable;
import org.jagdeep.example.hsqldb.HSQLDB;

public class BaseTest {
	protected String actualTableName;
	protected String actualSql;
	protected String testExpectedDescription;
	protected String testDescription;
	protected String expectedResults;
	protected ITable actualSQLData;
	protected ITable expectedSQLResult;
	protected String assertionFailure;
	 IDatabaseConnection iDatabaseConnection = null;

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
