/**
 * @author jagdeepjain
 */
package org.jagdeep.example.tests.util;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

public class DBUtil {
    IDatabaseConnection iDatabaseConnection = null;

    public void setDatabaseConnection(IDatabaseConnection iDatabaseConnection) {
        this.iDatabaseConnection = iDatabaseConnection;
    }

    public ITable getActualResults(String tableName, String sql)
            throws DataSetException, SQLException {
        return iDatabaseConnection.createQueryTable(tableName,
                sql);
    }

    public ITable getExpectedResults(String tableName, String expectedResults)
            throws MalformedURLException, DataSetException {
        FlatXmlDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(new File(expectedResults));
        return expectedData.getTable(tableName);
    }

    public void exportToXML(String tableName, String sql, String expectedXML)
            throws DataSetException, IOException {
        QueryDataSet partialDataSet = new QueryDataSet(iDatabaseConnection);
        partialDataSet.addTable(tableName, sql);
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream(
                "target/surefire-reports/" + expectedXML + ".xml"));
    }

}
