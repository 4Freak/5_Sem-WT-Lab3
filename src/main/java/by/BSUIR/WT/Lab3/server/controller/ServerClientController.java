package by.BSUIR.WT.Lab3.server.controller;

import java.io.*;
import java.net.Socket;

import by.BSUIR.WT.Lab3.server.command.CommandProvider;
import by.BSUIR.WT.Lab3.server.command.exception.CommandExcpetion;

public class ServerClientController extends Thread{

	private final Socket socket;
	private final ServerController serverController;
	
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	
	public ServerClientController(Socket socket, ServerController serverController) {
		this.socket = socket;
		this.serverController = serverController;
	}
	
	@Override
	public void run() {
		try {
			var inputStreamReader = new InputStreamReader(socket.getInputStream());
			bufferedReader = new BufferedReader(inputStreamReader);
			var outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
			printWriter = new PrintWriter(outputStreamWriter);
		} catch (IOException e) {
			e.printStackTrace();
			sendMessage(e.getMessage());
		}
		sendMessage("Available commands: \nAUTH USER/MANAGER DISCONNECT, \nVIEW, \nCREATE (firstName) (lastName), \nEDIT (id) (firstName) (lastName)");
		var running = true;
		while(running) {
			try {
				var request = readMessage();
				if (request == null) {
					running = false;
				}
				
				var command = CommandProvider.getInstance().getCommand(request);
				var response = command.execute(this, request);
				sendMessage(response);
			}catch (CommandExcpetion e) {
				e.printStackTrace();
			}
		}
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void sendMessage(String message) {
		printWriter.println(message);
		printWriter.flush();
	}
	
	private String readMessage() {
		try {
			return bufferedReader.readLine();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
