package com.zavirion.trafficmanage.iot;

import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;

public class Main {
    TopicPublisher publisher;

    public static void main(String[] args) {
        try {
            InitialContext context = new InitialContext();
            QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("iotConnectionFactory");
            QueueConnection connection = factory.createQueueConnection();

            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("myQueue");
            QueueSender sender = session.createSender(queue);


            for (int i = 0; i < 100; i++) {


                IotDevice iotDevice = IotDevice.simulateRandomData();
                MapMessage message = session.createMapMessage();
                message.setDouble("vehicleSpeed", iotDevice.getVehicleSpeed());
                message.setBoolean("trafficLightStatus", iotDevice.isTrafficLightStatus());
                message.setDouble("latitude", iotDevice.getLatitude());
                message.setDouble("longitude",iotDevice.getLongitude());


                sender.send(message);

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
            connection.close();


        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }


    }


}
