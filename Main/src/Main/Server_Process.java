package Main;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Server_Process extends Thread{
	// xu ly ching thao tac tren sv
	Socket sk1; 
	BufferedReader netIn;
	PrintWriter netOut;
	
	public Server_Process(Socket sk) {
		this.sk1 = sk;
		try {
			netIn = new BufferedReader(new InputStreamReader(sk1.getInputStream()));
			netOut = new PrintWriter(new OutputStreamWriter(sk1.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		netOut.println("Hello Client");
		netOut.flush();
		while(true) {
			String command;
			try {
				command = netIn.readLine();
				if (command.equalsIgnoreCase("quit")) {
					netOut.println("Bye Client");
					netOut.flush();
					netIn.close();
					netOut.close();
					break;
				}
				else {
					netOut.println("Server Response: " + command);
					netOut.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
