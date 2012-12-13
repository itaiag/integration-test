/*
 * Organization: RSA
 * Product:      Blackbox
 * Version:      1.0.1
 * Project:		 blackbox-integration-test
 */
package org.jsystemtest;

import org.jsystemtest.infra.report.ReportersManager;
import org.jsystemtest.infra.runner.IntegrationJUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

/**
 * <b>Package:</b> com.rsa.fa.blackbox.integration<br/>
 * <b>Type:</b> AbstractBlackBoxTestCase<br/>
 * <b>Description:</b> <br/>
 * 
 * @author abraho <br/>
 */
@RunWith(IntegrationJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/blackbox-integration-context.xml" })
public abstract class AbstractIntegrationTestCase {
	
	protected ReportersManager reporter = ReportersManager.getInstance();
	
	protected void sleep(long milliToSleep){
		try {
			Thread.sleep(milliToSleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
