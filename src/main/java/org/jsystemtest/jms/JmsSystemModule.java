package org.jsystemtest.jms;

import java.util.ArrayList;
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
import javax.jms.Session;

import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.support.converter.MessageType;

/**
 * <b>Package:</b> com.rsa.fa.blackbox.integration.jms<br/>
 * <b>Type:</b> Jms<br/>
 * <b>Description:</b> <br/>
 * 
 * @author abraho <br/>
 */
public class JmsSystemModule {

	private final SingleConnectionFactory connectionFactory;
	private boolean transacted = false;

	private List<Message> messageList = new ArrayList<Message>();
	private final List<JMSException> exceptionList = new ArrayList<JMSException>();
	private Map<String, Connection> connectionMap = new TreeMap<String, Connection>();
	private MyConsumer consumer = new MyConsumer();

	/**
	 * @param connectionFactory
	 */
	public JmsSystemModule(SingleConnectionFactory connectionFactory) {
		super();
		this.connectionFactory = connectionFactory;
	}

	public void sendMessage(String queueName, MessageType type,String messageBody) {
		// TODO: Handle exceptions
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
		try {
			connection.start();
			Session session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
			javax.jms.Queue queue = session.createQueue(queueName);
			Message message = null;
			switch (type) {
				case TEXT:
					message = session.createTextMessage(messageBody);
					break;

				default:
					throw new IllegalArgumentException("Unsupported type "+type.name());
			}
			MessageProducer producer = session.createProducer(queue);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			producer.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (JMSException e) {
			}
		}
	}

	public void startListenToQueue(String queueName) {
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
			javax.jms.Queue queue = session.createQueue(queueName);
			MessageConsumer messageConsumer = session.createConsumer(queue);
			connection.setExceptionListener(consumer);
			messageConsumer.setMessageListener(consumer);

		} catch (Exception e) {
			// TODO: handle exception
		}
		if (connection != null) {
			connectionMap.put(queueName, connection);
		}
	}

	public void stopListenToQueue(String queueName) {
		if (connectionMap.containsKey(queueName)) {
			Connection connection = connectionMap.remove(queueName);
			try {
				connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void stopListenToQueues() {
		for (String queueName : connectionMap.keySet()){
			stopListenToQueue(queueName);
		}
	}

	public void flushMessages() {
		messageList = new ArrayList<Message>();
	}

	public List<Message> getMessageList() {
		// TODO: Should be cloned
		return messageList;
	}

	private class MyConsumer implements MessageListener, ExceptionListener {

		@Override
		synchronized public void onException(JMSException ex) {
			exceptionList.add(ex);
		}

		@Override
		synchronized public void onMessage(Message message) {
			messageList.add(message);
		}

	}

}
