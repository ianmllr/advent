package org.advent2025;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class DayTwo {

    public static void main(String[] args) {
        Path path = args.length > 0 ? Paths.get(args[0]) : Paths.get("C:\\Users\\ianx3\\IdeaProjects\\advent\\src\\main\\resources\\day2input.txt");
        String[] providedNumbers = splitNumbersByComma(path);
        ArrayList<String> invalidIDs = new ArrayList<>();

        for (int i = 0; i < Objects.requireNonNull(providedNumbers).length; i++) {
            String[] numberRanges = providedNumbers[i].split("-");
            long rangeStart = Long.parseLong(numberRanges[0]);
            long rangeEnd = Long.parseLong(numberRanges[1]);

            for (long num = rangeStart; num <= rangeEnd; num++) {
                String numberString = Long.toString(num);
                int len = numberString.length();

                // Only even-length numbers can be split into equal halves
                if (len % 2 == 0) {
                    int mid = len / 2;
                    String firstHalf = numberString.substring(0, mid);
                    String secondHalf = numberString.substring(mid);

                    if (firstHalf.equals(secondHalf)) {
                        invalidIDs.add(numberString);
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
