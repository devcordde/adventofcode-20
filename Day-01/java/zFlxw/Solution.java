import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> values = getValues();

        // Problem 1
        for (int i = 0; i < values.size(); i++) {
            for (int j = i + 1; j < values.size(); j++) {
                int x = values.get(i), y = values.get(j);
                if (i != j && x + y == 2020) {
                    System.out.println(x * y);
                }
            }
        }

        // Problem 2
        for (int i = 0; i < values.size(); i++) {
            for (int j = i + 1; j < values.size(); j++) {
                for (int k = j + 1; k < values.size(); k++) {
                    int x = values.get(i), y = values.get(j), z = values.get(k);
                    if ((i != j && i != k && j != k) && (x + y + z == 2020)) {
                        System.out.println(x * y * z);
                    }
                }
            }
        }
    }

    private static List<Integer> getValues() {
        List<Integer> values = new ArrayList<>();
        // Pfad könnte bei euch anders sein | Path might be different for you
        String path = new File("").getAbsolutePath();
        File file = new File(path.concat("/src/day1/input.txt"));
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                int val = Integer.valueOf(scanner.nextLine());
                values.add(val);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return values;
    }
}
