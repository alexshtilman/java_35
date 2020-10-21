package telran.util;

import java.net.*;
import java.io.*;

public class TcpConsoleClientAppl {

	static final String HOST = "localhost";
	static final int PORT = 5000;
	static GameTcpProxy game = new GameTcpProxy(HOST,PORT);
	
	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket(HOST, PORT);
				BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream socketWriter = new PrintStream(socket.getOutputStream())) {
			while (true) {
				String line = null;
				if(!game.isStarted()) {
					System.out.println("Eneter request type start or quit");
					line = game.prompt();
					if (line.equalsIgnoreCase("quit")) {
						break;
					}
					if (line.equalsIgnoreCase("start")) {
						socketWriter.println(game.startGame());
						System.err.println(getResponce(socketReader.readLine()));
						while(!game.isFinished()) {
							line = game.prompt();
							socketWriter.println(game.move(line));
							
							System.err.println(getResponce(socketReader.readLine()));
						}
						socketWriter.println(game.getResult());
						break;
					}
				}
				
				
			}
		}
	}
	private static String getResponce(String line) {
		// <request type>#<string>
		String tokens[] = line.split("#");
		if (tokens.length != 2) {
			return "Wrong responce from server: "+line;
		}
		switch (tokens[0]) {
		case "start":{
			game.startGame();
			return tokens[1];
		}	
		case "move":{
			return tokens[1];
		}
		case "finish":{
			return game.setFinished(tokens[1]);
		}
		default:
			return "Wrong request type"+line;
		}
	}

}
