package Client;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.awt.*;
import java.awt.event.*;

public class Server_Process extends Thread{
	// xu ly ching thao tac tren sv
	Socket sk1; 
	BufferedReader netIn;
	PrintWriter netOut;
	
	static Frame f = new Frame("KAHOOT");
	static Font titleFont = new Font(Font.MONOSPACED, Font.BOLD, 45);
	static Font titleBut = new Font(Font.SANS_SERIF, Font.BOLD, 25);
	static Font n_answer = new Font(Font.SANS_SERIF, Font.ITALIC, 30);
	
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
		Button b1 = new Button("Game Moderator svpr");
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
