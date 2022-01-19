package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MyThread extends Thread{

	DatagramSocket aSocket;
	byte[] arg;
	//public String clientMessage;
	
	public MyThread(DatagramSocket aSocket){
		this.aSocket=aSocket;

	}
	
	@Override
	public void run(){
		try {
			System.out.println("Listener is running");
			while(true) {
				
				listen();
			}
		}catch(Exception e) {
			System.out.println("Message reading was unsuccessful.");
		}
		
	}
	
	public void listen(){
		try {
		byte[] buffer1 = new byte[1024];
		DatagramPacket reply11 = new DatagramPacket (buffer1, buffer1.length);
		aSocket.receive(reply11);
		System.out .println (" You got a message : " + new String(reply11.getData(),0,reply11.getLength()));

		}catch(IOException ex) {
			System.out.println("Failed or interrupted I/O operations.");
		}
	}
	
}
