package com.zavirion.trafficmanage.iot;

import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {
    TopicPublisher publisher;
    public static void main(String[] args) {
        try {
            InitialContext context =new InitialContext();
           TopicConnectionFactory factory  = (TopicConnectionFactory)context.lookup("iotConnectionFactory");
            TopicConnection connection = factory.createTopicConnection();
            connection.start();
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
             Topic topic= (Topic)context.lookup("iot");
            TopicPublisher publisher = session.createPublisher(topic);
            TextMessage message = session.createTextMessage();
            message.setText("Hello JMS");
            publisher.publish(message);



            connection.close();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 100; i++) {

            IotDevice iotDevice = IotDevice.simulateRandomData();
            System.out.println(iotDevice.getVehicleSpeed());
            System.out.println(iotDevice.getLatitude());
            System.out.println(iotDevice.getLongitude());
            System.out.println(iotDevice.isTrafficLightStatus());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
