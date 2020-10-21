package telran.util;


import static telran.util.BullsAndCowsModel.*;
import static telran.util.BullsAndCowsView.*;

import java.util.ArrayList;

public class BullsCowsGameImpl implements GuessGame{
	String secret;
	boolean isGameStarted = false;
	boolean isGameFinished = false;
	private static ArrayList<String> buffer = new ArrayList<String>();
	private final static int MAX_DIGITS = 4;
	public BullsCowsGameImpl() {
	}
	@Override
	public String startGame() {
		secret =  genUniqueRandomNumber();
		System.out.println(secret);
		isGameStarted = true;
		buffer.clear();
		buffer.add("Game of Cows and Bulls! Please gues the number (4 digits)");
		return "start#Game of Cows and Bulls! Please gues the number (4 digits)";
	}

	@Override
	public String prompt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String move(String userInput) {
		int[] result = compareToSecret(secret, userInput);
		buffer.add(String.format("%s (%d cows; %d bulls)", userInput, result[0], result[1]));
		if (result[0] == 0 && result[1] == MAX_DIGITS) {
			isGameFinished = true;
			buffer.add("Finish!");
			sendToFile(buffer);
			return "finish#Finish! Total moves - "+buffer.size()/2;
		}
		return String.format("move#%s (%d cows; %d bulls)", userInput, result[0], result[1]);
	}

	@Override
	public boolean isFinished() {
		return isGameFinished;
	}

}
