package org.jsystemtest.unit;

import javax.jms.Message;

import junit.framework.Assert;

import org.jsystemtest.AbstractIntegrationTestCase;
import org.jsystemtest.systemModule.jms.JmsSystemModule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageType;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

/**
 * <b>Package:</b> com.rsa.fa.blackbox.integration<br/>
 * <b>Type:</b> JmsSystemModuleTests<br/>
 * <b>Description:</b> <br/>
 * 
 * @author abraho <br/>
 */
public class JmsSystemModuleTests extends AbstractIntegrationTestCase {

	private static final String QUEUE_NAME = "Blocking";

	private static final long MESSAGE_TIMEOUT = 10;

	@Autowired
	private JmsSystemModule jms;

	@BeforeMethod
	public void clearResources() throws Exception {
		Reporter.log("Clearing queue before test", true);
		jms.flushQueue(QUEUE_NAME);
		Reporter.log("Finished clearing queue", true);
	}

	@Test
	public void testFlushQueue() throws Exception {
		for (int i = 0; i < 10; i++) {
			jms.sendMessage(QUEUE_NAME, MessageType.TEXT, "testFlushQueue");
		}
		jms.flushQueue(QUEUE_NAME);
		Assert.assertNull(jms.receiveMessage(QUEUE_NAME, 1));
		Reporter.log("Success", true);
	}

	@Test
	public void testSendRecieveMessage() throws Exception {
		jms.sendMessage(QUEUE_NAME, MessageType.TEXT, "testSendRecieveMessage0");
		Message m = jms.receiveMessage(QUEUE_NAME, MESSAGE_TIMEOUT);
		Assert.assertNotNull(m);
		jms.sendMessage(QUEUE_NAME, MessageType.TEXT, "testSendRecieveMessage0");
		m = jms.receiveMessage(QUEUE_NAME, MESSAGE_TIMEOUT);
		Assert.assertNotNull(m);
		Reporter.log("Success", true);
	}

	@Test
	public void testBrowseQueue() throws Exception {
		int numOfMessages = 2;
		for (int i = 0; i < numOfMessages; i++) {
			jms.sendMessage(QUEUE_NAME, MessageType.TEXT, "testFlushQueue" + i);
		}
		Assert.assertEquals(3, jms.browseQueue(QUEUE_NAME).size());
		Assert.assertEquals(3, jms.browseQueue(QUEUE_NAME).size());
		
		Reporter.log("Success", true);
		
		//Cleaning queue
		for (int i = 0; i < numOfMessages; i++) {
			jms.receiveMessage(QUEUE_NAME, 500);
		}
		
	}
	
	@Test
	public void testReceiveAllMessages() throws Exception{
		for (int i = 0; i < 3; i++) {
			jms.sendMessage(QUEUE_NAME, MessageType.TEXT, "testFlushQueue" + i);
		}
		sleep(1000);
		Assert.assertEquals(3, jms.recieveAllMessages(QUEUE_NAME).size());
		
	}


}
