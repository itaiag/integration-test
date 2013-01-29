package org.jsystemtest.integration;

import junit.framework.Assert;

import org.jsystemtest.AbstractIntegrationTestCase;
import org.jsystemtest.systemModule.database.DatabaseSystemModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class DatabaseSystemModuleTests extends AbstractIntegrationTestCase {

	@Autowired
	private DatabaseSystemModule db;

	@Test
	public void testNumberOfRows() {
		reporter.report("About to test database");
		Assert.assertEquals(3, db.countRowsInTable("QUEUE.NAME"));
	}

}
