public class Solution {
    public static void main(String[] args) throws IOException {
        new Solution();
    }

    public Solution() throws IOException {
        List<String> input = Files.readAllLines(Paths.get("Your Path here"));
        List<Integer> seatNumbers = new ArrayList<>();

        // Problem 1
        for (String pass : input) {
            int row = 0;
            int rowHalf = 64;

            int column = 0;
            int columnHalf = 4;
            for (int i = 0; i < pass.length(); i++) {
                if (pass.charAt(i) == 'B')
                    row += rowHalf;

                rowHalf /= 2;

                if (pass.charAt(i) == 'R') {
                    column += columnHalf;
                    columnHalf /= 2;
                }

                if (pass.charAt(i) == 'L') {
                    columnHalf /= 2;
                }

                seatNumbers.add((row * 8 + column));
            }
        }

        // Problem 2
        int santasId = 0;
        for (int i = 0; i < seatNumbers.size(); i++) {
            int seatIdOne = seatNumbers.get(i);
            for (int j = i + 1; j < seatNumbers.size(); j++) {
                int seatIdTwo = seatNumbers.get(j);

                if (seatIdOne - seatIdTwo == 2 && !seatNumbers.contains((seatIdOne + 1))) {
                    santasId = seatIdOne + 1;
                }

                if (seatIdTwo - seatIdOne == 2 && !seatNumbers.contains((seatIdTwo + 1))) {
                    santasId = seatIdTwo + 1;
                }
            }
        }

        System.out.println("Problem 1: " + Collections.max(seatNumbers));
        System.out.println("Problem 2: " + santasId);
    }
}
