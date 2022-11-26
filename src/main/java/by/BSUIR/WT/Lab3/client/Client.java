package by.BSUIR.WT.Lab3.client;

import by.BSUIR.WT.Lab3.client.controller.ClientController;

public class Client {
	public static void main(String[] args) {
		var client = new ClientController(4444);
		client.start();
	}

}
