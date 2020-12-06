//created by MCqwertz

package MCqwertz;

import java.io.FileNotFoundException;
import java.util.*;

public class Day06 {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Part 1: " + getFirstResult());
		System.out.println("Part 1: " + getSecondResult());
	}

	private static int getFirstResult() throws FileNotFoundException {
		TreeSet<Character> questions = new TreeSet<>();
		Scanner scanner = TextFileUtils.getScanner(6);
		int sum = 0;
		while(scanner.hasNextLine()) {
			char[] chars = scanner.nextLine().toCharArray();
			if(chars.length == 0) {
				sum += questions.size();
				questions.clear();
			} else {
				for (char c : chars) {
					questions.add(c);
				}
			}
		}
		sum += questions.size();
		return sum;
	}

	private static int getSecondResult() throws FileNotFoundException {
		ArrayList<Character> questions = new ArrayList<>();
		Scanner scanner = TextFileUtils.getScanner(6);
		boolean firstLine = true;
		int sum = 0;
		while (scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			if(nextLine.equals("")) {
				sum += questions.size();
				questions.clear();
				firstLine = true;
			} else {
				if(firstLine) {
					for (char c : nextLine.toCharArray()) {
						questions.add(c);
					}
					firstLine = false;
				} else {
					questions.removeIf(c -> !nextLine.contains(String.valueOf(c)));
				}
			}

		}
		sum += questions.size();
		return sum;
	}
}
