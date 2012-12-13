package org.jsystemtest.unit;

import org.jsystemtest.AbstractIntegrationTestCase;
import org.junit.Test;

public class ReportersManagerTests extends AbstractIntegrationTestCase{
	
	@Test
	public void testSimplePrint(){
		reporter.report("Simple message");
	}
	
	@Test()
	
	public void testFailureReport(){
//		reporter.report("Failure report", Status.FAILURE);
	}
}
