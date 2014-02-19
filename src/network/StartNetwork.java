package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartNetwork extends Thread {
	private ServerSocket serversocket;
	private Socket clientsocket;

	public void run(){
		try {
			serversocket = new ServerSocket(8080);
			
			while(true){
				System.out.println("Waiting for client on port "
						+ serversocket.getLocalPort() + "...");
				clientsocket = serversocket.accept();
				//clientsocket.setSoTimeout(5000);
				System.out.println("Just connected to "
						+ clientsocket.getRemoteSocketAddress());
				
				Comm comm = new Comm(clientsocket);
				comm.start();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
