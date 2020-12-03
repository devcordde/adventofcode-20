import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.lines(Path.of(args[0]))
                .collect(Collectors.toList());
            long n1 = findSlopeTreeAmount(lines, 1, 1);
            long n2 = findSlopeTreeAmount(lines, 3, 1);
            long n3 = findSlopeTreeAmount(lines, 5, 1);
            long n4 = findSlopeTreeAmount(lines, 7, 1);
            long n5 = findSlopeTreeAmount(lines, 1, 2);
            System.out.println("Solution for part 1: " + n2);
            System.out.println("Solution for part 2: " + (n1 * n2 * n3 * n4 * n5));
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    private static int findSlopeTreeAmount(List<String> input, int step, int down) {
        int currentIndex = 0;
        int treeAmount = 0;
        for (int i = 0; i < input.size(); i += down) {
            char[] objects = input.get(i).toCharArray();
            if (objects[currentIndex] == '#') {
                treeAmount++;
            }
            currentIndex = getNextIndex(currentIndex, objects.length - 1, step);
        }
        return treeAmount;
    }

    private static int getNextIndex(int currentIndex, int maxIndex, int step) {
        if (currentIndex + step > maxIndex) {
            return ((currentIndex - maxIndex) + --step);
        }
        return currentIndex + step;
    }

}

