package demo;

import javax.jms.JMSException;

public class Main {
    
    public static void main(String[] args) throws JMSException {
        MessageSender.sendingTOQueue();
        // ObjectSendToQueue.sendingTOQueue();
    }
}