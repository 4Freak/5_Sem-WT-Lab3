package by.BSUIR.WT.Lab3.client.controller;

import java.io.*;

public class ClientReaderController extends Thread  {

	private final ClientController clientController;

	public ClientReaderController(ClientController clientController) {
		this.clientController = clientController;
	}
	
	@Override
	public void run() {
		var systemInStreamReader = new InputStreamReader(System.in);
		var bufferedReader = new BufferedReader(systemInStreamReader);
		try {
			while (clientController.isRunning()) {
				var request = bufferedReader.readLine();
				clientController.sendMessage(request);
			}
		}catch (IOException  e) {
			e.printStackTrace();
		}
	}
}
