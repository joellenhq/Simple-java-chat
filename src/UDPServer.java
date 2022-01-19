package main;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPServer {

	public static void main ( String[] args ) {
		
		DatagramSocket aSocket;
		String reply1 = new String();
		int port;
		String reply2 = new String();
		int port2;
		try{
			if(args.length < 1) {
				System.out.println("Usage: UDPServer <port>");
				System.exit(-1);
			}

			aSocket = new DatagramSocket(Integer.parseInt(args[0]));

			while(true){
				byte[] buffer = new byte[1024];
				byte[] bytes = new byte[1024];
				DatagramPacket request = new DatagramPacket(buffer,buffer.length);
				aSocket.receive(request);

				bytes=request.getData();

				String clientMessage = new String(bytes);
				String[] splitMessage = new String[3];
				clientMessage=clientMessage+" ";
				splitMessage = clientMessage.split("\\|",3);
				//System.out.println(splitMessage);
				//System.out.println(clientMessage);
				
				String operation = splitMessage[0];
				String name = splitMessage[1];
				String message= splitMessage[2];

				//System.out.println(operation);
				//System.out.println(name);
				//System.out.println(message);
				Message wiadomosc = new Message(operation,name,message,request.getPort());
				wiadomosc.operacja(operation);
				reply1=wiadomosc.getKomunikat();
				port=wiadomosc.getPort1();
				if((operation.equals("!")) && (wiadomosc.getKomunikacja())) {
					reply2=wiadomosc.getKomunikat_1();
					port2=wiadomosc.getPort1_1();
					//System.out.println(reply2);
					//System.out.println(port2);
					DatagramPacket reply3=new DatagramPacket(reply2.getBytes(),reply2.length(),request.getAddress(),port2);
					aSocket.send(reply3);
				}
				//System.out.println(reply1);
				//System.out.println(port);
				//System.out.println(request.getPort());
				DatagramPacket reply=new DatagramPacket(reply1.getBytes(),reply1.length(),request.getAddress(),port);

				aSocket.send(reply);
				
				splitMessage[2]=" ";
			} 
		} catch (SocketException ex) {
			Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE,null,ex);
		} catch (IOException ex) {
			Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE,null,ex);
		} catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Incomplete message");
		}
	}
	}


