package demo;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ObjectSendToQueue {

	private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	private static final String queueName = "OBJECT_QUEUE";

	public static void sendingTOQueue() throws JMSException {

		Employee employee = new Employee("tina", 3, "SE1");

		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url);

		try {
			Connection connection = activeMQConnectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(queueName);

			MessageProducer producer = session.createProducer(destination);

			TextMessage message = session.createTextMessage(employee.toString());

			producer.send(message);

			producer.send(destination, message, DeliveryMode.NON_PERSISTENT, 1, 300l);

			System.out.println("printing after sending message'");

			producer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
