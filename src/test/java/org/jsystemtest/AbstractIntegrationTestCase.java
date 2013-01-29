package org.jsystemtest;

import org.jsystemtest.infra.junit.report.ReportersManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * <b>Package:</b> com.rsa.fa.blackbox.integration<br/>
 * <b>Type:</b> AbstractBlackBoxTestCase<br/>
 * <b>Description:</b> <br/>
 */

@ContextConfiguration(locations = { "classpath:META-INF/integration-context.xml" })
public abstract class AbstractIntegrationTestCase extends AbstractTestNGSpringContextTests{
	
	protected ReportersManager reporter = ReportersManager.getInstance();
	
	protected void sleep(long milliToSleep){
		try {
			Thread.sleep(milliToSleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
