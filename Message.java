package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

public class Message {
	
	String operation;
	String name;
	String message;
	private int port;
	private int port1;
	private String komunikat="a";
	private String komunikat1="a";
	private boolean komunikacja=false;
	static ArrayList<String> names= new ArrayList<String>();
	static ArrayList<Integer> ports= new ArrayList<Integer>();	
	
	
	public Message(String operation, String name, String message,int port) {
		this.operation=operation;
		this.name=name;
		this.message=message;
		this.port=port;
	}
	
	public void operacja(String operacja1) {
		komunikat=" ";
		komunikat1=" ";
		operacja1=operacja1.trim();
		if(operacja1.equals("+")) register(name,port);
		else if(operacja1.equals("-")) unregister(name);
		else if(operacja1.equals("?")) list(name);
		else if(operacja1.equals("!")) communicate(name,message,port);
		else komunikat="|"+name+"|"+"niepoprawna operacja";
			//System.out.println("niepoprawna operacja");		
		}
	
	private void communicate(String nick, String msg,int port) {
		String nick1;
		
		komunikacja=false;
		for(String i: names) {
			if(i.equals(nick)) {
				int pos =names.indexOf(i);
				port1 = ports.get(pos);
				komunikat1="|"+nick+"|"+msg;
				komunikacja=true;
			}
		}
		for(Integer i: ports) {
			if(i.equals(port)) {
				int pos =ports.indexOf(i);
				nick1 = names.get(pos);
				if(komunikacja==true) {
					komunikat="|"+nick1+"|"+"OK|";
				}
				else komunikat="|"+nick1+"|brak uzytkownika";
			}
		}
	}

	private void list(String nick) {
		komunikat="|"+nick+"|"+names;
	}

	private void unregister(String nick) {
		boolean isDeleted=false;
		int i=0; 
		 for (Iterator<String> it = names.iterator(); it.hasNext();) {
	           
			 String next = it.next();
	            if(next.equals(nick)) {
	                it.remove(); 
	            	i++;
					komunikat="|"+nick+"|"+"OK|";
					isDeleted=true;
					//ports.remove(i);
	            }
	        }
		if(isDeleted==false) {
			//System.out.println("nie ma u¿ytkowanika o podanej nazwie");
			komunikat="|"+nick+"|brak uzytkownika";
			i=-1;
		}
		else {
			int j=0;   
			 for (Iterator<Integer> it = ports.iterator(); it.hasNext();) {
		         
				 Integer next = it.next();
		            if(j==i) {
		                it.remove(); 
		            	
		            }   
		            j++;
		        }
		}
	}

	private void register(String nick,int clientPort) {
		boolean isAvailable=true;
		for(String i: names) {
			if(i.equals(nick)) {
				//System.out.println("podana nazwa jest zajeta");
				komunikat="|"+nick+"|nazwa zajeta";
				isAvailable=false;
				break;
			}
		}
		if(isAvailable==true) {
			names.add(nick);
			ports.add(clientPort);
			komunikat="|"+nick+"|"+"OK|";
		
		}
	}
	
	public String getKomunikat() {
		return komunikat;
	}
	public int getPort1() {
		return port;
	}
	public String getKomunikat_1() {
		return komunikat1;
	}
	public int getPort1_1() {
		return port1;
	}
	public boolean getKomunikacja() {
		return komunikacja;
	}
}
