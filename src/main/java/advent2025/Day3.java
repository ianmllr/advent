package advent2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Day3 {
    public static void main() {
        Path path = Path.of(("src/main/resources/2025/day3input.txt"));
        String[] numbers = splitNumbersBy(path, " ");
        int totalJolts = 0;
        long totalJoltsLong = 0;
        int maxJolts;
        long maxJoltsPt2;

        // part 1
        for (int i = 0; i < Objects.requireNonNull(numbers).length; i++) {
            maxJolts = getPt1Jolts(numbers[i]);
            totalJolts += maxJolts;
        }
        System.out.println("Total jolts (pt 1): " + totalJolts);

        // part 2
        for (int i = 0; i < Objects.requireNonNull(numbers).length; i++) {
            maxJoltsPt2 = getPt2Jolts(numbers[i]);
            totalJoltsLong += maxJoltsPt2;
        }
        System.out.println("Total jolts (pt 2): " + totalJoltsLong);

    }

    private static int getPt1Jolts(String numbers) {
        int maxJolts = 0;
        List<String> numberLine = new ArrayList<>(List.of(numbers.split("")));
        for (int x = 0; x < numberLine.size(); x++) {
            for (int y = x + 1; y < numberLine.size(); y++) {
                int digit1 = Integer.parseInt(numberLine.get(x));
                int digit2 = Integer.parseInt(numberLine.get(y));
                int jolts = digit1 * 10 + digit2;
                if (jolts > maxJolts) {
                    maxJolts = jolts;
                }
            }
        }
        return maxJolts;
    }

    private static long getPt2Jolts(String numbers) {
        char[] digits = numbers.toCharArray();
        char[] result = new char[12];
        int startPointer = 0;

        for (int i = 0; i < 12; i++) {
            int endPointer = digits.length - (12 - i);
            char maxDigit = '0';
            int maxDigitPointer = -1;

            for (int j = startPointer; j <= endPointer; j++) {
                if (digits[j] > maxDigit) {
                    maxDigit = digits[j];
                    maxDigitPointer = j;
                }
            }
            result[i] = maxDigit;
            startPointer = maxDigitPointer + 1;
        }
        return Long.parseLong(new String(result));
    }

    public static String[] splitNumbersBy(Path path, String regex) {
        List<String> allParts = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(regex);
                allParts.addAll(Arrays.asList(parts));
            }
            return allParts.toArray(new String[0]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

