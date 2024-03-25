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

		// activeMQConnectionFactory.setTrustedPackages(Arrays.asList("demo"));

		try{

		Connection connection = activeMQConnectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Destination destination = session.createQueue(queueName);

		MessageProducer producer = session.createProducer(destination);

		// ObjectMessage objectMessage = session.createObjectMessage(employee);
        
		// jmsTemplate.convertAndSend(jmsDestinjava.lang.Throwable: Delivery[7] exceeds redelivery policy limit:RedeliveryPolicy {destination = null, collisionAvoidanceFactor = 0.15, maximumRedeliveries = 6, maximumRedeliveryDelay = -1, initialRedeliveryDelay = 1000, useCollisionAvoidance = false, useExponentialBackOff = false, backOffMultiplier = 5.0, redeliveryDelay = 1000, preDispatchCheck = true}, cause:null"ation,customer.toString());
		TextMessage message=session.createTextMessage(employee.toString());

		producer.send(message);
		producer.send(destination,message,DeliveryMode.NON_PERSISTENT,1,300l);
		// producer.send(objectMessage);
		System.out.println("printing after sending message'");
		producer.close();
		session.close();
		connection.close();
		}
		catch(JMSException e)
		{
           e.printStackTrace();
		}
	}
}

/*
 * 
 * // The name of the queue.
 * this.queueName = queueName;
 * // URL of the JMS server is required to create connection factory.
 * // DEFAULT_BROKER_URL is : tcp://localhost:61616 and is indicates that JMS
 * // server is running on localhost
 * connectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);
 * // Getting JMS connection from the server and starting it
 * connection = connectionFactory.createConnection();
 * connection.setClientID(CLIENTID);
 * connection.start();
 * // Creating a non-transactional session to send/receive JMS message.
 * session = connection.createSession(false, AUTO_ACKNOWLEDGE);
 * // Destination represents here our queue ’MyFirstActiveMQ’ on the JMS
 * // server.
 * // The queue will be created automatically on the JSM server if its not
 * already
 * // created.
 * destination = session.createQueue(this.queueName);
 * // MessageProducer is used for sending (producing) messages to the queue.
 * producer = session.createProducer(destination);
 * // MessageConsumer is used for receiving (consuming) messages from the queue.
 * consumer = session.createConsumer(destination);
 */