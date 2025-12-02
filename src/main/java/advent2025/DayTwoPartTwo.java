package advent2025;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class DayTwoPartTwo {

    public static void main(String[] args) {
        Path path = args.length > 0 ? Paths.get(args[0]) : Paths.get("src/main/resources/2025/day2input.txt");
        String[] providedNumbers = splitNumbersByComma(path);
        ArrayList<String> invalidIDs = new ArrayList<>();

        for (int i = 0; i < Objects.requireNonNull(providedNumbers).length; i++) {
            String[] numberRanges = providedNumbers[i].split("-");
            long rangeStart = Long.parseLong(numberRanges[0]);
            long rangeEnd = Long.parseLong(numberRanges[1]);

            for (long num = rangeStart; num <= rangeEnd; num++) {
                String numberString = Long.toString(num);
                int len = numberString.length();

                // Check all possible pattern lengths (from 1 to len/2)
                for (int patternLen = 1; patternLen <= len / 2; patternLen++) {
                    // Only check if the pattern can divide the string evenly
                    if (len % patternLen == 0) {
                        String pattern = numberString.substring(0, patternLen);
                        boolean matches = true;

                        // Check if the entire string is made of repetitions of this pattern
                        for (int pos = patternLen; pos < len; pos += patternLen) {
                            String chunk = numberString.substring(pos, pos + patternLen);
                            if (!chunk.equals(pattern)) {
                                matches = false;
                                break;
                            }
                        }

                        if (matches) {
                            invalidIDs.add(numberString);
                            break;
                        }
                    }
                }
            }
        }
        long sum = 0;

        for (String invalidID : invalidIDs) {
            System.out.println(invalidID);
            sum += Long.parseLong(invalidID);
        }
        System.out.println("Sum: " + sum);

    }

    public static String[] splitNumbersByComma(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            String[] parsedLines = new String[0];
            String regex = ",";
            while ((line = br.readLine()) != null) {
                parsedLines = line.split(regex);
            }
            return parsedLines;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
