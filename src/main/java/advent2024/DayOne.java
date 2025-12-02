package advent2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DayOne {
    public static void main() {
        Path path = Path.of(("src/main/resources/2024/day1input.txt"));
        List<String> parsedNumbers = splitNumbers(path);
        List<Integer> leftNumbers = new ArrayList<>();
        List<Integer> rightNumbers = new ArrayList<>();
        int totalDifference = 0;
        int similarityScore = 0;

        for (int i = 0; i < Objects.requireNonNull(parsedNumbers).size(); i+= 2) {
            leftNumbers.add(Integer.parseInt(parsedNumbers.get(i)));
            rightNumbers.add(Integer.parseInt(parsedNumbers.get(i + 1)));
        }

        Collections.sort(leftNumbers);
        Collections.sort(rightNumbers);

        // part one
        for (int i = 0; i < leftNumbers.size(); i++) {
            int diff = Math.abs(leftNumbers.get(i) - rightNumbers.get(i));
            totalDifference += diff;
        }

        // part two
        for (Integer leftNumber : leftNumbers) {
            int occurences = 0;
            int currentLeftNumber = leftNumber;
            for (Integer rightNumber : rightNumbers) {
                if (rightNumber.equals(currentLeftNumber)) occurences++;
            }
            similarityScore += (currentLeftNumber * occurences);
        }
        System.out.println(totalDifference);
        System.out.println(similarityScore);
    }

    public static List<String> splitNumbers(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            List<String> allNumbers = new ArrayList<>();
            String regex = " {3}";
            while ((line = br.readLine()) != null) {
                String[] parsedLine = line.split(regex);
                allNumbers.addAll(List.of(parsedLine));
            }
            return allNumbers;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
