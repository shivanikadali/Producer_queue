package demo;

// http://127.0.0.1:8161/admin/queues.jsp admin page

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender {

    // URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is on localhost
    // default broker URL is : tcp://localhost:61616"
    private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // Queue Name : You can create any/many queue names as per your requirement.
    private static final String queueName = "MESSAGE_QUEUE";

    // throws a JMSException(checked exception), that can occur during JMS
    // operations
    public static void sendingTOQueue() throws JMSException {

        // Getting JMS connection from the JMS server and starting it
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Creating a non transactional session to send/receive JMS message.
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // The queue will be created automatically on the server.
        Destination destination = session.createQueue(queueName);

        // Destination represents here our queue 'MESSAGE_QUEUE' on the JMS server.
        // MessageProducer is used for sending messages to the queue.

        MessageProducer producer = session.createProducer(destination);

        TextMessage greetingMessage = session.createTextMessage("Hiiii developer");

        greetingMessage.setStringProperty("Type", "greetings");

        // Here we are sending our message
        //QOS
        producer.send(destination, greetingMessage, 1, 1, 20000l);

        // 2nd message to the same queue
        TextMessage message = session.createTextMessage("develop an application");

        message.setStringProperty("Type", "information");

        producer.send(destination, message, 2, 2, 20000l);

        System.out.println("sent the msgs successfully");
         
        producer.close();
        session.close();
        connection.close();
    }
}