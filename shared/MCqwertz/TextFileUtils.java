//created by MCqwertz


package com.github.mcqwertz.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class TextFileUtils {

	public static Supplier<IntStream> getNumbers(int day) throws FileNotFoundException {
		File file = new File("inputs/Day" + day + ".txt");
		Scanner scanner = new Scanner(file);
		ArrayList<String> arrayList = new ArrayList<>();
		while(scanner.hasNextLine()) {
			arrayList.add(scanner.nextLine());
		}
		return () -> arrayList.stream().mapToInt(Integer::parseInt);
	}
}
