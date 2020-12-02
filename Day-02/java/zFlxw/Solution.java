import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        List<String> input = readInput();
        int totalPasswords = 0;
        int totalPasswordsNewPolicy = 0;

        // Problem 1
        for (String str : input) {
            String amount = str.split(" ")[0];
            String character = str.split(" ")[1].split(":")[0];
            String password = str.split(" ")[2];
            int minAmount = Integer.parseInt(amount.split("-")[0]);
            int maxAmount = Integer.parseInt(amount.split("-")[1]);
            int count = 0;

            for (char c : password.toCharArray())
                if (c == character.charAt(0))
                    count++;

            if (count >= minAmount && count <= maxAmount)
                totalPasswords++;
        }

        System.out.println("Problem 1: " + totalPasswords);

        // Problem 2
        for (String str : input) {
            String amount = str.split(" ")[0];
            String character = str.split(" ")[1].split(":")[0];
            String password = str.split(" ")[2];
            int firstIndex = Integer.parseInt(amount.split("-")[0]);
            int secondIndex = Integer.parseInt(amount.split("-")[1]);

            if (password.length() > firstIndex && password.length() >= secondIndex)
                if ((password.charAt(firstIndex - 1) == character.charAt(0) && password.charAt(secondIndex - 1) != character.charAt(0)) ||
                        (password.charAt(firstIndex - 1) != character.charAt(0) && password.charAt(secondIndex - 1) == character.charAt(0)))
                    totalPasswordsNewPolicy++;

        }

        System.out.println("Problem 2: " + totalPasswordsNewPolicy);
    }

    private static List<String> readInput() {
        List<String> values = new ArrayList<>();
        String path = new File("").getAbsolutePath();
        File file = new File(path.concat("/src/day2/input.txt"));
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String val = scanner.nextLine();
                values.add(val);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return values;
    }
}
