//created by MCqwertz


package com.github.mcqwertz.year2020.days;

import com.github.mcqwertz.util.TextFileUtils;

import java.io.FileNotFoundException;
import java.util.function.IntPredicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;


public class Day1 {
	public static void main(String[] args) throws FileNotFoundException {
		Day1 day = new Day1();
		Supplier<IntStream> supplier = TextFileUtils.getNumbers(1);
		System.out.println("Task 1: " + day.getFirstPart(supplier));
		System.out.println("Task 2: " + day.getSecondPart(supplier));
	}

	/**
	 * @param supplier supplier of the stream with all given numbers
	 * @return the solution of the first problem
	 */
	private int getFirstPart(Supplier<IntStream> supplier) {
		IntPredicate isSearched = arg -> this.isContaining(supplier.get(), 2020 - arg);
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
	private int getSecondPart(Supplier<IntStream> supplier) {
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

	private boolean isContaining(IntStream stream, int x) {
		for (int i : stream.toArray()) {
			if(i == x) {
				return true;
			}
		}
		return false;
	}
}
