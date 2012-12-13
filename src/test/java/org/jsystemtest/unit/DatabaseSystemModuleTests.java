package org.jsystemtest.unit;

import junit.framework.Assert;

import org.jsystemtest.AbstractIntegrationTestCase;
import org.jsystemtest.systemModule.database.DatabaseSystemModule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseSystemModuleTests extends AbstractIntegrationTestCase {

	@Autowired
	private DatabaseSystemModule db;

	@Test
	public void testNumberOfRows() {
		reporter.report("About to test database");
		Assert.assertEquals(3, db.countRowsInTable("BLACKBOX.ATTACK_TYPE"));
	}

}
