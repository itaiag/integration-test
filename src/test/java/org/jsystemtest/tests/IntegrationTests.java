package org.jsystemtest.tests;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Reporter;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:META-INF/blackbox-integration-context.xml" })
public class IntegrationTests extends AbstractTestNGSpringContextTests {

	@Test
	public void testOne() {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.setEscapeHtml(false);
		Reporter.log("<b>Bold </b>");
		Reporter.log("<i>Italic </i>");
		Reporter.setEscapeHtml(true);
	}

}
