/*
 * Organization: RSA
 * Product:      Blackbox
 * Version:      1.0.1
 * Project:		 integration-test
 */
package org.jsystemtest;

import junit.framework.Assert;

import org.jsystemtest.jms.JmsSystemModule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageType;


/**
 * <b>Package:</b> org.jsystemtest<br/>
 * <b>Type:</b> ITJmsSystemModule<br/>
 * <b>Description:</b>
 * <br/>
 * @author abraho
 * <br/>
 */
public class ITJmsSystemModule {
	@Autowired
	private JmsSystemModule jms;
	
	@Test
	public void testSendRecieveMessage() throws InterruptedException{
		String queueName = "Broker";
		jms.sendMessage(queueName, MessageType.TEXT,"My message");
		jms.startListenToQueue(queueName);
		Thread.sleep(1000);
		Assert.assertEquals(1, jms.getMessageList().size());
		System.out.println("Success");
	}
}
