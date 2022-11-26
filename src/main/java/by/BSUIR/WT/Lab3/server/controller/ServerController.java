package by.BSUIR.WT.Lab3.server.controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController extends Thread{

	private static final int PORT = 4444;
	private static final int BACKLOG = 14;
	
	private ServerSocket serverSocket;
	
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(PORT, BACKLOG, null);
		}catch (IOException  e) {
			e.printStackTrace();
		}
		
		System.out.println("Start server");
		
		while(true) {
			Socket clientSocket;
			try {
				clientSocket = serverSocket.accept();
				System.out.println("Client connected " + clientSocket.toString());
				var serverClientController = new ServerClientController(clientSocket, this);
				serverClientController.start();
			}catch (IOException  e) {
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect(ServerClientController serverClientController) {
		Socket clientSocket;
		try {
			clientSocket = serverClientController.getSocket();
			clientSocket.close();
			System.out.println("Client disconnected " + clientSocket.toString());
		}catch (IOException  e) {
			e.printStackTrace();
		}
	}
}
