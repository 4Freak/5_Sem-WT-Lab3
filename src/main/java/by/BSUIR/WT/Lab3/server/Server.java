package by.BSUIR.WT.Lab3.server;

import by.BSUIR.WT.Lab3.server.controller.ServerController;

public class Server {
	public static void main(String[] args) {
		var server = new ServerController();
		server.start();
	}
}
