//created by MCqwertz

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFileUtils {
	public static Scanner getScanner(int day) throws FileNotFoundException {
		File file = new File("inputs/Day" + day + ".txt");
		return new Scanner(file);
	}
}
