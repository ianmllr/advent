package advent2025;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

public class Day2 {
    public static void main() {
        Path path = Path.of(("src/main/resources/2025/day2input.txt"));
        String[] providedNumbers = readAndSplitBy(path, ",");
        ArrayList<String> invalidIDs = new ArrayList<>();
        ArrayList<String> invalidIDsPt2 = new ArrayList<>();

        for (int i = 0; i < Objects.requireNonNull(providedNumbers).length; i++) {
            String[] numberRanges = providedNumbers[i].split("-");
            long rangeStart = Long.parseLong(numberRanges[0]);
            long rangeEnd = Long.parseLong(numberRanges[1]);

            for (long num = rangeStart; num <= rangeEnd; num++) {
                 String numberString = Long.toString(num);
                 int len = numberString.length();
                 if (len % 2 == 0) {
                     int mid = len / 2;
                     String firstHalf = numberString.substring(0, mid);
                     String secondHalf = numberString.substring(mid);

                     if (firstHalf.equals(secondHalf)) {
                         invalidIDs.add(numberString);
                     }
                 }

                 // part 2
                 for (int patternLen = 1; patternLen <= len / 2; patternLen++) {
                     if (len % patternLen == 0) {
                         String pattern = numberString.substring(0, patternLen);
                         boolean matches = true;

                        // checks if the entire string is made of repetitions of this pattern

                         for (int pos = patternLen; pos < len; pos += patternLen) {
                             String chunk = numberString.substring(pos, pos + patternLen);
                             if (!chunk.equals(pattern)) {
                                 matches = false;
                                 break;
                             }
                         }
                         if (matches) {
                            invalidIDsPt2.add(numberString);
                            break;
                         }
                    }
                }
            }
        }

        long sum = 0;
        long sum2 = 0;
        // pt 1
        for (String invalidID : invalidIDs) {
            System.out.println(invalidID);
            sum += Long.parseLong(invalidID);
        }
        // pt 2
        for (String invalidIDPt2 : invalidIDsPt2) {
            System.out.println(invalidIDPt2);
            sum2 += Long.parseLong(invalidIDPt2);
        }
        System.out.println("Part 1 sum: " + sum);
        System.out.println("Part 2 sum: " + sum2);

    }

    public static String[] readAndSplitBy(Path path, String regex) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            String[] parsedLines = new String[0];
            while ((line = br.readLine()) != null) {
                parsedLines = line.split(regex);
            }
            return parsedLines;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
