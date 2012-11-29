/*
 * Organization: RSA
 * Product:      Blackbox
 * Version:      1.0.1
 * Project:		 blackbox-integration-test
 */
package org.jsystemtest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <b>Package:</b> com.rsa.fa.blackbox.integration<br/>
 * <b>Type:</b> ITDemoTest<br/>
 * <b>Description:</b> <br/>
 * 
 * @author abraho <br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class ITDemoTest {

	@Autowired
	private RootSUTModule root;

	@Test
	public void testOne() {
		System.out.println("Test One");
		Assert.assertNotNull(root);
	}

	@Test
	public void testTwo() {
		System.out.println("Test Two");
		Assert.assertNotNull(root.getSecondLevel());
	}
}
