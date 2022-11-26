package by.BSUIR.WT.Lab3.client.controller;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientController extends Thread{
	
	private final int port;
	private PrintWriter printWriter;
	private boolean running;
	
	public ClientController(int port) {
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
			running = true;
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
			var readerController = new ClientReaderController(this);
			readerController.start();
			var inputStreamReader = new InputStreamReader(socket.getInputStream());
			var bufferedReader = new BufferedReader(inputStreamReader);
			var outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
			printWriter = new PrintWriter(outputStreamWriter);
			String responce = "";
			while(responce != null) {
				System.out.println(responce);
				responce = bufferedReader.readLine();
			}
			
		}catch (IOException  e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		printWriter.println(message);
		printWriter.flush();
	}
		
	public boolean isRunning() {
		return running;
	}
}
