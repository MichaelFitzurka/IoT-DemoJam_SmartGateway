package com.redhat.demo.iot.gateway.rules_cep;

import javax.jms.Connection;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AMQTester {

	private ActiveMQConnectionFactory 	connectionFactory;
	private	Connection				  	connection;
	
	public AMQTester( ) {
	}
	
	public void waitForBroker( String userName, String password, String brokerURL ){
		while( testAvailability( userName, password, brokerURL ) == false ) {
    		System.out.println(" AMQ-Broker " + brokerURL + " not yet available ");
    		
    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    	System.out.println(" AMQ-Broker " + brokerURL + " ready to work! ");
    	
	}

	public boolean testAvailability( String userName, String password, String brokerURL) {
		boolean res = false;
		
        try {
        	// Create a ConnectionFactory
            connectionFactory = new ActiveMQConnectionFactory(userName, password, brokerURL);

            // Create a Connection
            connection = connectionFactory.createConnection();
        	
			connection.start();
			
			connection.close();
			
			res = true;
		} catch (JMSException e) {
			
			res = false;
			
		}
		
		return res;
	}
}
