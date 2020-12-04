//created by MCqwertz

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;


public class Day1 {
	public static void main(String[] args) throws FileNotFoundException {
		Supplier<IntStream> supplier = getInput();
		System.out.println("Task 1: " + getFirstPart(supplier));
		System.out.println("Task 2: " + getSecondPart(supplier));
	}

	/**
	 * @param supplier supplier of the stream with all given numbers
	 * @return the solution of the first problem
	 */
	private static int getFirstPart(Supplier<IntStream> supplier) {
		IntPredicate isSearched = arg -> isContaining(supplier.get(), 2020 - arg);
		int result = 1;
		//check for each int whether there is suitable second int
		for (int i : supplier.get().filter(isSearched).toArray()) {
			result = result * i;
		}
		return result;
	}
	/**
	 * @param supplier supplier of the stream with all given numbers
	 * @return the solution of the second problem
	 */
	private static int getSecondPart(Supplier<IntStream> supplier) {
		int[] array = supplier.get().toArray();
		//combine each number with every other number
		for (int i : array) {
			for (int j : array) {
				//check for a third suitable number
				if (isContaining(supplier.get(), 2020 - i - j)) {
					return i * j * (2020 - i - j);
				}
			}
		}
		return -1;
	}

	private static boolean isContaining(IntStream stream, int x) {
		for (int i : stream.toArray()) {
			if(i == x) {
				return true;
			}
		}
		return false;
	}

	public static Supplier<IntStream> getInput() throws FileNotFoundException {
		Scanner scanner = TextFileUtils.getScanner(1);
		ArrayList<String> arrayList = new ArrayList<>();
		while(scanner.hasNextLine()) {
			arrayList.add(scanner.nextLine());
		}
		scanner.close();
		return () -> arrayList.stream().mapToInt(Integer::parseInt);
	}
}
