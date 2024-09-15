/**
 * @author jagdeepjain
 */
package org.jagdeep.example.tests;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.jagdeep.example.hsqldb.HSQLDB;

public class BaseTest {
    IDatabaseConnection iDatabaseConnection = null;

    protected void setUp() throws Exception {
        iDatabaseConnection = new DatabaseConnection(HSQLDB.getConnection());
        iDatabaseConnection.getConfig().
                setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                        new org.dbunit.ext.hsqldb.HsqldbDataTypeFactory());
    }

    public void tearDown() throws Exception {
        iDatabaseConnection.close();
    }

    protected String getQualifiedTestName() {
		return this.getClass().getCanonicalName();
    }
}
