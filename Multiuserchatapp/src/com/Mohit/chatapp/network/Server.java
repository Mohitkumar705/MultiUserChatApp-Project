package com.Mohit.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.Mohit.chatapp.utils.ConfigReader;
import java.util.ArrayList;
public class Server {
	ServerSocket serverSocket ;
	ArrayList<ServerWorker> workers = new ArrayList<>();
	public Server() throws IOException {
		int PORT =Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Start and waiting for the client to join....");
		handleClientRequest();
		 
		
	}
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept();
			//Per Client Per Thread
			ServerWorker serverWorker = new ServerWorker(clientSocket, this);
			workers.add(serverWorker);
			serverWorker.start();
			}
		
	}
	
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		
	}

}
