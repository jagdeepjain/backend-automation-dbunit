package org.jagdeep.example.tests.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Properties {
	String testDescription;
	String testExpected;
	String sql;
	String tableName;
	String expectedResults;
	final java.util.Properties properties = new java.util.Properties();
	InputStream inputStream;

	public void setTestProperties(String path) throws IOException {
		inputStream = new FileInputStream(path);
		properties.load(inputStream);
		this.testDescription = properties.getProperty("test.description");
		this.testExpected = properties.getProperty("test.expected");
		this.sql = properties.getProperty("sql");
		this.tableName = properties.getProperty("tableName");
		this.expectedResults = properties.getProperty("expectedResults");
	}
	public String getTestDescription() {
		return this.testDescription;
	}
	public String getTestExpected()  {
		return this.testExpected;
	}
	public String getSql() {
		return this.sql;
	}
	public String getTableName()  {
		return this.tableName;
	}
	public String getExpectedResults()  {
		return this.expectedResults;
	}
}
