/*
 * Organization: RSA
 * Product:      Blackbox
 * Version:      1.0.1
 * Project:		 blackbox-integration-test
 */
package org.jsystemtest.unit;

import java.util.List;

import javax.jms.Message;

import junit.framework.Assert;

import org.jsystemtest.AbstractIntegrationTestCase;
import org.jsystemtest.systemModule.jms.JmsSystemModule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageType;

/**
 * <b>Package:</b> com.rsa.fa.blackbox.integration<br/>
 * <b>Type:</b> JmsSystemModuleTests<br/>
 * <b>Description:</b> <br/>
 * 
 * @author abraho <br/>
 */
public class JmsSystemModuleTests extends AbstractIntegrationTestCase {

	private static final String QUEUE_NAME = "Blocking";
	@Autowired
	private JmsSystemModule jms;

	@Test
	public void testSendRecieveMessage() throws Exception {
		jms.sendMessage(QUEUE_NAME, MessageType.TEXT, "My message");
		jms.startListenToQueue(QUEUE_NAME);
		sleep(1000);
		Assert.assertEquals(1, jms.getMessageList().size());
		System.out.println("Success");
		jms.stopListenToQueue(QUEUE_NAME);
	}

	@Test
	public void testBrowseQueue() throws Exception {
		jms.sendMessage(QUEUE_NAME, MessageType.TEXT, "My message");
		sleep(1000);
		List<Message> message = jms.browseQueue(QUEUE_NAME);
		Assert.assertEquals(1, message.size());
		Assert.assertEquals(1, message.size());
		System.out.println("Success");
	}

}
