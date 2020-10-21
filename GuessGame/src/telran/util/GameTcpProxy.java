package telran.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameTcpProxy implements GuessGame {
	private String hostname;
	private int port;
	boolean isGameStarted = false;
	boolean isGameFinished = false;
	String result = "";
	BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
	
	public GameTcpProxy(String hostname, int port) {
		super();
		this.hostname = hostname;
		this.port = port;
	}

	@Override
	public String startGame() {
		isGameStarted = true;
		return "start#game";
	}

	@Override
	public String prompt() {
		String userInput = "";
		try {
			userInput = consoleReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInput;
	}

	@Override
	public String move(String userInput) {
		return "move#" + userInput;
	}

	@Override
	public boolean isFinished() {
		return isGameFinished;
	}

	public boolean isStarted() {
		return isGameStarted;
	}

	public String setFinished(String result) {
		isGameFinished = true;
		isGameStarted = false;
		this.result = result;
		return result;
	}
	public String getResult() {
		return result;
	}
}
