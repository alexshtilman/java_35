package telran.app;

import java.io.*;
import java.net.*;

import telran.util.BullsCowsGameImpl;

public class TcpBullsCowsAppl {

	private static final int PORT = 5000;
	public static BullsCowsGameImpl game = new BullsCowsGameImpl();
	
	public static void main(String[] args) throws Exception {

		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server is listening on the port " + PORT);
			while (true) {
				Socket socket = serverSocket.accept();
				try {
					runClient(socket);
				} catch (Exception e) {
					
					//e.printStackTrace();
					continue;
				}
			}
		}

	}

	private static void runClient(Socket socket) throws Exception {

		BufferedReader readerFromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream writerToSocket = new PrintStream(socket.getOutputStream());
		while (true) {
			String line = null;
			try {
				line = readerFromSocket.readLine();
				System.out.println(line);
				if (line == "finish") {
					System.out.println("client closed connection");
					break;
				}
			} catch (IOException e) {
				System.out.println("client abnormally closed connection");
				break;
			}
			line = getRequest(line);
			writerToSocket.println(line);
		}

	}

	private static String getRequest(String line) {
		// <request type>#<string>
		String tokens[] = line.split("#");
		if (tokens.length != 2) {
			return "error#Wrong Request from client: "+line;
		}
		switch (tokens[0]) {
		case "start":
			return game.startGame();
		case "move":
			return game.move(tokens[1]);
		default:
			return "Wrong request type"+line;
		}
	}
}