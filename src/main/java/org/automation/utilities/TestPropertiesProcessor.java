package org.automation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestPropertiesProcessor {

	String testDescription;
	String testExpected;
	String sql;
	String tableName;
	String expectedResults;
	Properties properties = new Properties();
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

	
	public String getTestDesription() throws IOException {
		return this.testDescription;
	}
	
	public String getTestExpected() throws IOException {
		return this.testExpected;
	}

	public String getSql() throws IOException {
		return this.sql;
	}
	
	public String getTableName() throws IOException {
		return this.tableName;
	}
	
	public String getExpectedResults() throws IOException {
		return this.expectedResults;
	}
}
