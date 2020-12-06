//created by MCqwertz

package MCqwertz;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day4 {
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<HashMap<String, String>> input = getInput();
		System.out.println("Part 1: " + getFirstResult(input));
		System.out.println("Part 2: " + getSecondResult(input));
	}

	public static int getFirstResult(ArrayList<HashMap<String, String>> input) {
		int result = 0;
		for (HashMap<String, String> map : input) {
			if(map.containsKey("byr") && map.containsKey("iyr") && map.containsKey("eyr") && map.containsKey("hgt")
					&& map.containsKey("hcl") && map.containsKey("ecl") && map.containsKey("pid")) {
				result++;
			}
		}
		return result;
	}

	public static int getSecondResult(ArrayList<HashMap<String, String>> input) {
		int result = 0;
		for (HashMap<String, String> map : input) {
			if(map.containsKey("byr") && map.containsKey("iyr") && map.containsKey("eyr") && map.containsKey("hgt")
					&& map.containsKey("hcl") && map.containsKey("ecl") && map.containsKey("pid")
					&& Integer.parseInt(map.get("byr")) >= 1920 && Integer.parseInt(map.get("byr")) <= 2002
					&& Integer.parseInt(map.get("iyr")) >= 2010 && Integer.parseInt(map.get("iyr")) <= 2020
					&& Integer.parseInt(map.get("eyr")) >= 2020 && Integer.parseInt(map.get("eyr")) <= 2030
					&& (((String.valueOf(map.get("hgt").toCharArray()[map.get("hgt").toCharArray().length - 2]) +
					map.get("hgt").toCharArray()[map.get("hgt").toCharArray().length - 1]).equals("cm")
					&& Integer.parseInt(map.get("hgt").substring(0 , map.get("hgt").length() - 2)) >= 150
					&& Integer.parseInt(map.get("hgt").substring(0 , map.get("hgt").length() - 2)) <= 193)
					|| ((String.valueOf(map.get("hgt").toCharArray()[map.get("hgt").toCharArray().length - 2]) +
					map.get("hgt").toCharArray()[map.get("hgt").toCharArray().length - 1]).equals("in")
					&& Integer.parseInt(map.get("hgt").substring(0 , map.get("hgt").length() - 2)) >= 59
					&& Integer.parseInt(map.get("hgt").substring(0 , map.get("hgt").length() - 2)) <= 76))
					&& map.get("hcl").matches("#[a-f|0-9][a-f|0-9][a-f|0-9][a-f|0-9][a-f|0-9][a-f|0-9]")
					&& (map.get("ecl").equals("amb") ||
					map.get("ecl").equals("blu") ||
					map.get("ecl").equals("brn") ||
					map.get("ecl").equals("gry") ||
					map.get("ecl").equals("grn") ||
					map.get("ecl").equals("hzl") ||
					map.get("ecl").equals("oth"))
					&& map.get("pid").replaceAll("[^0-9]", "").length() == 9) {
				result++;
			}
		}
		return result;
	}

	private static ArrayList<HashMap<String, String>> getInput() throws FileNotFoundException {
		Scanner scanner = TextFileUtils.getScanner(4);
		ArrayList<HashMap<String, String>> input = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if(line.equals("")) {
				String[] strings = sb.toString().replace(':', ' ').split(" ");
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < strings.length; i += 2) {
					map.put(strings[i], strings[i + 1]);
				}
				input.add(map);
				sb = new StringBuilder();
			} else {
				if(sb.length() != 0) {
					sb.append(' ');
				}
				sb.append(line);
			}
		}
		String[] strings = sb.toString().replace(':', ' ').split(" ");
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < strings.length; i += 2) {
			map.put(strings[i], strings[i + 1]);
		}
		input.add(map);
		return input;
	}
}
