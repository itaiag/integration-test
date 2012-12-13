/*
 */
package org.jsystemtest.systemModule.jms;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.jsystemtest.systemModule.AbstractSystemModule;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.support.converter.MessageType;

/**
 * <b>Package:</b> com.rsa.fa.blackbox.integration.jms<br/>
 * <b>Type:</b> Jms<br/>
 * <b>Description:</b>The module responsible for interacting with JMS. It exposes various services for sending and asserting
 * messages <br/>
 * 
 */
public class JmsSystemModule extends AbstractSystemModule {

	private final SingleConnectionFactory connectionFactory;
	private boolean transacted = false;

	private List<Message> messageList = new ArrayList<Message>();
	private final List<JMSException> exceptionList = new ArrayList<JMSException>();
	private Map<String, Connection> connectionMap = new TreeMap<String, Connection>();
	private CollectorConsumer consumer = new CollectorConsumer();

	/**
	 * @param connectionFactory
	 */
	public JmsSystemModule(SingleConnectionFactory connectionFactory) {
		super();
		this.connectionFactory = connectionFactory;
	}

	/**
	 * Send message to the specified queue.
	 * 
	 * @param queueName
	 *            The name of the destination queue
	 * @param type
	 *            TEXT,MAP,BYTES or OBJECT
	 * @param messageBody
	 * @throws Exception
	 *             if queue is not exist of failed to create connection to queue or to close the queue
	 * @throws IllegalArgumentException
	 *             If message type is not supported
	 */
	public void sendMessage(String queueName, MessageType type, String messageBody) throws Exception {
		reporter.report("About to send message from type " + type.name() + " to queue " + queueName);
		Connection connection = connectionFactory.createConnection();
		try {
			connection.start();
			Session session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(queueName);
			Message message = null;
			switch (type) {
				case TEXT:
					message = session.createTextMessage(messageBody);
					break;

				default:
					throw new IllegalArgumentException("Unsupported type " + type.name());
			}
			MessageProducer producer = session.createProducer(queue);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			producer.send(message);
		} finally {
			connection.close();
		}
	}

	/**
	 * Return all the messages from specified queue without removing from queue.
	 * 
	 * @param queueName
	 * @return List of messages.
	 * @throws Exception
	 *             If queue is not exist or failed to create connection
	 */
	public List<Message> browseQueue(String queueName) throws Exception {
		Connection connection = null;
		connection = connectionFactory.createConnection();
		List<Message> messageList = null;
		try {
			connection.start();
			Session session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(queueName);
			QueueBrowser browser = session.createBrowser(queue);
			messageList = new ArrayList<Message>();
			@SuppressWarnings("unchecked")
			Enumeration<Message> e = browser.getEnumeration();
			while (e.hasMoreElements()) {
				messageList.add(e.nextElement());

			}
		} finally {
			connection.stop();
		}

		return messageList;
	}

	public void flushQueue(String queueName) throws Exception {
		// TODO: Implement fulshQueue.
	}

	/**
	 * Opens connection to JMS and start listen to the specified queue. Use getMessageList to receive the captured messages
	 * 
	 * @param queueName
	 * @throws Exception
	 *             If failed to create connection or queue is not exist
	 */
	public void startListenToQueue(String queueName) throws Exception {
		Connection connection = null;
		connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(queueName);
		MessageConsumer messageConsumer = session.createConsumer(queue);
		connection.setExceptionListener(consumer);
		messageConsumer.setMessageListener(consumer);
		connectionMap.put(queueName, connection);
	}

	/**
	 * Stop listens to queue. If queue is not being listen to, nothing will happen.
	 * 
	 * @param queueName
	 *            queue to stop listen to.
	 * @throws Exception
	 *             If failed to close connection
	 */
	public void stopListenToQueue(String queueName) throws Exception {
		if (connectionMap.containsKey(queueName)) {
			Connection connection = connectionMap.remove(queueName);
			connection.close();
		}

	}

	/**
	 * Stops listening to all the queues.
	 * 
	 * @throws Exception
	 *             If failed to stop connection.
	 */
	public void stopListenToQueues() throws Exception {
		for (String queueName : connectionMap.keySet()) {
			stopListenToQueue(queueName);
		}
	}

	/**
	 * Cleans all the message that has been captured so far.
	 */
	public void flushMessages() {
		messageList = new ArrayList<Message>();
	}

	public List<Message> getMessageList() {
		// TODO: Should be cloned
		// TODO: Consider add the option to receive message of specific queue
		return messageList;
	}

	public boolean isTransacted() {
		return transacted;
	}

	public void setTransacted(boolean transacted) {
		this.transacted = transacted;
	}

	/**
	 * 
	 * This class uses for capturing all the messages received from the queues.
	 */
	private class CollectorConsumer implements MessageListener, ExceptionListener {

		@Override
		public void onMessage(Message message) {
			messageList.add(message);
		}


		@Override
		public void onException(JMSException e) {
			exceptionList.add(e);
			
		}
	}

}
