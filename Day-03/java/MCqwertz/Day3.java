//created by MCqwertz

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {
	public static void main(String[] args) throws FileNotFoundException {
		char[][] grid = getInput();
		System.out.println("Part 1: " + getTrees(grid, 3, 1));
		System.out.println("Part 2: " + getSecondResult(grid));
	}


	private static long getSecondResult(char[][] grid) {
		return getTrees(grid, 1, 1) * getTrees(grid, 3, 1) * getTrees(grid, 5, 1) *
				getTrees(grid, 7, 1) * getTrees(grid, 1, 2);
	}

	private static long getTrees(char[][] grid, int right, int down) {
		long trees = 0;
		int coulomb = 0;
		for (int i = down; i < grid.length; i += down) {
			coulomb = coulomb + right;
			if (grid[i][coulomb % grid[i].length] == '#') {
				trees++;
			}
		}
		return trees;
	}

	public static char[][] getInput() throws FileNotFoundException {
		Scanner scanner = TextFileUtils.getScanner(3);
		ArrayList<String> arrayList = new ArrayList<>();
		while(scanner.hasNextLine()) {
			arrayList.add(scanner.nextLine());
		}
		int coulombs = arrayList.get(0).toCharArray().length;
		char[][] grid = new char[arrayList.size()][coulombs];
		int j = 0;
		for (int i = 0; i < arrayList.size(); i++){
			for (char c : arrayList.get(i).toCharArray()) {
				grid[i][j++] = c;
			}
			j = 0;
		}
		scanner.close();
		return grid;
	}
}
