package by.BSUIR.WT.Lab3.client.controller;

import java.net.InetAddress;

public class ClientController extends Thread{
	
	private final int port;
		
	public ClientController(int port) {
		this.port = port;
	}
	
	public void start() {
		
		@Override
		public void run() {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
			var readerController = new ClientReadContoller(this);
		}
	}
}
