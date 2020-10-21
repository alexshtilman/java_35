package telran.util;

import java.util.Random;
import java.util.stream.Stream;

public class BullsAndCowsModel {
	private final static int MAX_DIGITS = 4;

	public static int[] compareToSecret(String secret, String gues) {
		int[] bullsAndCows = { 0, 0 };//bullsAndCows[0] = cow, bullsAndCows[1] = bull
		int[] helper = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
		char[] charSecret = secret.toCharArray();
		for (int c = 0; c < secret.length(); c++) {
			helper[Integer.parseInt("" + charSecret[c])] = c;
		}
		for (int i = 0; i < gues.length(); i++) {
			try {
				int index = helper[gues.charAt(i) - '0'];
				if (index != -1) {
					if (index == i) {
						bullsAndCows[1]++;
					} else {
						bullsAndCows[0]++;
					}
				}
			} catch (Exception e) {
				continue;
			}
		}
		return bullsAndCows;
	}

	public static String genUniqueRandomNumber() {
		return Stream.generate(() -> (int) (new Random().nextInt(9) + 1)).distinct().limit(MAX_DIGITS)
				.collect(StringBuilder::new, (builder, cur) -> builder.append(Integer.toString(cur)),
						StringBuilder::append)
				.toString();
	}
}
