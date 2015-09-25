package org.jagdeep.example.automation.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

public class TestProcessor {
    
    IDatabaseConnection iDatabaseConnection = null;
    
    public void setDatabaseConnection(IDatabaseConnection iDatabaseConnection) {
        this.iDatabaseConnection = iDatabaseConnection;
    }
    
    public ITable getActualResults(String tableName, String sql)
            throws DataSetException, SQLException {
        // Retrieving actual data from database
        ITable actualSQLData = iDatabaseConnection.createQueryTable(tableName,
                sql);
        return actualSQLData;
    }
    
    public ITable getExpectedResults(String tableName, String expectedResults)
            throws MalformedURLException, DataSetException {
        // setting up expected results
        FlatXmlDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(new File(expectedResults));
        ITable expectedSQLResult = expectedData.getTable(tableName);
        return expectedSQLResult;
    }
    
    public void exportToXML(String tableName, String sql, String expectedXML)
            throws DataSetException, FileNotFoundException, IOException {
        // partial database export
        QueryDataSet partialDataSet = new QueryDataSet(iDatabaseConnection);
        partialDataSet.addTable(tableName, sql);
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream(
                "target/surefire-reports/" + expectedXML + ".xml"));
    }
    
}
