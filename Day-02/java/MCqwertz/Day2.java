//created by MCqwertz

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.IntPredicate;

public class Day2 {
	public static void main(String[] args) throws FileNotFoundException {
		String[][] input = getInput();
		System.out.println("Part 1: " + getFirstPart(input));
		System.out.println("Part 2: " + getSecondPart(input));
	}

	private static int getFirstPart(String[][] input) {
		int i = 0;
		for (String[] strings : input) {
			IntPredicate isSearched = arg -> arg == strings[2].charAt(0);
			int int0 = Integer.parseInt(strings[0]);
			int int1 = Integer.parseInt(strings[1]);
			int appearances = strings[3].chars().filter(isSearched).toArray().length;
			if(appearances <= int1 && appearances >= int0) {
				i++;
			}
		}
		return i;
	}

	private static int getSecondPart(String[][] input) {
		int i = 0;
		for (String[] strings : input) {
			int int0 = Integer.parseInt(strings[0]) - 1;
			int int1 = Integer.parseInt(strings[1]) - 1;
			char c = strings[2].charAt(0);
			if ((strings[3].charAt(int0) == c && strings[3].charAt(int1) != c) ||
					(strings[3].charAt(int0) != c && strings[3].charAt(int1) == c)) {
				i++;
			}
		}
		return i;
	}

	public static String[][] getInput() throws FileNotFoundException {
		Scanner scanner = TextFileUtils.getScanner(2);
		ArrayList<String> arrayList = new ArrayList<>();
		while(scanner.hasNextLine()) {
			arrayList.add(scanner.nextLine());
		}
		String[][] input = new String[arrayList.size()][4];
		for (int i = 0; i < arrayList.size(); i++) {
			String [] strings = arrayList.get(i).split("-")[1].replace(":", " ").split(" ");
			input[i] = new String[]{arrayList.get(i).split("-")[0], strings[0], strings[1], strings[3]};
		}
		scanner.close();
		return input;
	}

}

