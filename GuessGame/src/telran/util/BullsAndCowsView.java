package telran.util;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BullsAndCowsView {
public static void sendToFile(ArrayList<String> buffer) {
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm");
	String formattedDate = LocalDateTime.now().format(myFormatObj);
	String fileName = formattedDate + "_" + buffer.size()/2;
	try (PrintStream ps = new PrintStream(fileName);) {
		for (String i : buffer) {
			ps.println(i);	
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
}
}
