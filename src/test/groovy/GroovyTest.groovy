package org.jsystemtest.integration

import org.testng.annotations.Test;
import org.jsystemtest.infra.report.Reporter;
class GroovyTest {

	@Test
	void testGroovy(){
		System.out.println("STDOUT OF GROOVY TEST");
		Reporter.log("IN GROOVY TEST!!!");
	}
}
