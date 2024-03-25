package demo;

import javax.jms.JMSException;

public class Main {

    public static void main(String[] args) throws JMSException {
        // sending messages to queue (MESSAGE_QUEUE)
        MessageSender.sendingTOQueue();
        // sending messages as message to queue (OBJECT_QUEUE)
        ObjectSendToQueue.sendingTOQueue();
    }
}