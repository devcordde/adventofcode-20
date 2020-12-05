//created by MCqwertz
package MCqwertz;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day5 {
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Integer> input = getIDs();
		System.out.println("Part 1: " + getHighestSeatID(input));
		System.out.println("Part 1: " + getSantasSeat(input));
	}

	public static int getHighestSeatID(ArrayList<Integer> input) {
		int highest = 0;
		for (int i : input) {
			if(i > highest) {
				highest = i;
			}
		}
		return highest;
	}

	public static int getSantasSeat(ArrayList<Integer> input) {
		Collections.sort(input);
		for (int i = 0 ; i < input.size() - 1; i++) {
			if(input.get(i + 1) - input.get(i) != 1) {
				return input.get(i) + 1;
			}
		}
		return -1;
	}

	public static ArrayList<Integer> getIDs() throws FileNotFoundException {
		ArrayList<Integer> seats = new ArrayList<>();
		Scanner scanner = TextFileUtils.getScanner(5);
		while(scanner.hasNextLine()) {
			char[] line = scanner.nextLine().toCharArray();
			int row = 127;
			int rangeRow = 127;
			int coulomb = 7;
			int rangeCoulomb = 7;
			for(char c : line) {
				switch (c) {
					case 'F':
						row -= rangeRow / 2 + 1;
					case 'B':
						rangeRow /= 2;
						break;
					case 'L':
						coulomb -= rangeCoulomb / 2 + 1;
					case 'R':
						rangeCoulomb /= 2;
						break;
					default:
						System.out.println("Error in reading the input!");
				}
			}
			seats.add(row * 8 + coulomb);
		}
		return seats;
	}
}

