package org.jagdeep.example.tests.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Config {
    final java.util.Properties properties = new java.util.Properties();
    String employeeIDTestDescription;
    String employeeIDTestActualSql;
    String employeeIDTestActualTableName;
    String employeeIDExpectedResults;
    InputStream inputStream;

    public void setConfig(String path) throws IOException {
        inputStream = new FileInputStream(path);
        properties.load(inputStream);
        this.employeeIDTestDescription =
                properties.getProperty("employee.id.test.description");
        this.employeeIDTestActualSql =
                properties.getProperty("employee.id.test.actual.sql");
        this.employeeIDTestActualTableName =
                properties.getProperty("employee.id.test.actual.table.name");
        this.employeeIDExpectedResults =
                properties.getProperty("employee.id.expected.results");
    }

    public String getEmployeeIDTestDescription() {
        return employeeIDTestDescription;
    }
    public String getEmployeeIDTestActualSql() {
        return employeeIDTestActualSql;
    }
    public String getEmployeeIDTestActualTableName() {
        return employeeIDTestActualTableName;
    }
    public String getEmployeeIDExpectedResults() {
        return employeeIDExpectedResults;
    }
}
