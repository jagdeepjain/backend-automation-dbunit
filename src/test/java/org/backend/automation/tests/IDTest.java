package org.backend.automation.tests;

import java.io.IOException;

import org.backend.automation.main.BaseTest;
import org.db.hsqldb.HSQLDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class IDTest extends BaseTest {
    
    HSQLDB hsqldb = HSQLDB.getInstance();
    
	public IDTest() throws IOException {
		super();
	}
	
	@Before
	public void setUp() throws Exception {
	       hsqldb.query("create table employee ( id INTEGER, first_name VARCHAR(50), last_name VARCHAR(50) )");  
	        hsqldb.insert("insert into employee (id, first_name, last_name) values (1001, 'John', 'Doe')");
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testFeedStatusProcessed() throws Exception {
		// actual test
		super.test();
	}
}
