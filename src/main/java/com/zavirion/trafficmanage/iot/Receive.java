package com.zavirion.trafficmanage.iot;

import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Receive {
    public static void main(String[] args) {
        try {
            InitialContext context = new InitialContext();
            QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("iotConnectionFactory");
            QueueConnection connection = factory.createQueueConnection();
            connection.start();

            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("myQueue");
            QueueReceiver receiver = session.createReceiver(queue);
            receiver.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        IotDevice body = message.getBody(IotDevice.class);
                        System.out.println(body.getVehicleSpeed());
                    } catch (JMSException e) {
                        throw new RuntimeException(e);
                    }


                }
            });
            while (true) {
            }


        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

    }
}
