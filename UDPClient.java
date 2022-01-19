package main;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;

public class UDPClient{
	
	public static void main(String[] args) {
		try {
			if(args.length < 2) {
				System.out.println("Usage: UDPClient <port> <server host name>");
				System.exit(-1);
			}
			
			int messageLength;
			DatagramSocket aSocket = new DatagramSocket();

			InetAddress aHost = InetAddress.getByName(args[1]);
			int serverPort = Integer.parseInt(args[0]);

			MyThread thread1 = new MyThread(aSocket);
			thread1.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			String clientMessage;

			while(true){

				
				//System.out.println("Enter message: ");
				clientMessage = reader.readLine();
				messageLength = clientMessage.trim().length();
				if(messageLength>3) {

					DatagramPacket request = new DatagramPacket(clientMessage.getBytes(), messageLength, aHost, serverPort);
					aSocket.send(request);

					/*byte[] buffer = new byte[1024];
					DatagramPacket reply = new DatagramPacket (buffer, buffer.length);
					aSocket.receive(reply);
					System.out.println("Reply:"+new String(reply.getData()));
					*/
				}
				else {
					System.out.println("Incomplete message");
				}
			}
			} catch (SocketException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE,null ,ex);
			} catch (UnknownHostException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE,null,ex);
			} catch (IOException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE,null,ex);
			}
	}
	
	
	
}
