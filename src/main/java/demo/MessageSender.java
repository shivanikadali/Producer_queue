package demo;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender {

    // URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is
    // on localhost
    // default broker URL is : tcp://localhost:61616"

    private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;  

    // Queue Name.You can create any/many queue names as per your requirement.

    private static final String queueName = "MESSAGE_QUEUE";

    //throws a JMSException(checked exception), that can occur during JMS operations
    public static void sendingTOQueue() throws JMSException {

        // Getting JMS connection from the JMS server and starting it

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        // Creating a non transactional session to send/receive JMS message.

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // The queue will be created automatically on the server.

        Destination destination = session.createQueue(queueName);

        // Destination represents here our queue 'MESSAGE_QUEUE' on the JMS server.
        // MessageProducewr is used for sending messages to the queue.

        MessageProducer producer = session.createProducer(destination);
        
        TextMessage greetingMessage = session.createTextMessage("hiiii");

        greetingMessage.setStringProperty("Type","greetings");
        // Here we are sending our message!
        producer.send(destination,greetingMessage,1,1,20000l);

        //we will send a small text with small json
        TextMessage message = session.createTextMessage("develop an application");
        // message.setIntProperty("property",1);
        message.setStringProperty("Type","information");
        producer.send(destination,message,2,2,20000l);

        System.out.println("sent the msgs successfully");
        connection.close();
    }
}
