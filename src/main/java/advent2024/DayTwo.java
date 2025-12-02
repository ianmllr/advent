package advent2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DayTwo {
    public static void main() {


        Path path = Path.of(("src/main/resources/2024/day2input.txt"));
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            String regex = " ";
            ArrayList<String> safeReports = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] parsedLine = line.split(regex);
                boolean isSafe = true;
                boolean isIncreasing = Integer.parseInt(parsedLine[1]) > Integer.parseInt(parsedLine[0]);

                for (int i = 0; i < parsedLine.length - 1; i++) {
                    int pastNumber = Integer.parseInt(parsedLine[i]);
                    int nextNumber = Integer.parseInt(parsedLine[i + 1]);
                    int diff = Math.abs(pastNumber - nextNumber);

                    if (diff < 1 || diff > 3) {
                        isSafe = false;
                        break;
                    }
                    if (isIncreasing && (pastNumber >= nextNumber)) {
                        isSafe = false;
                        break;
                    }
                    if (!isIncreasing && (pastNumber <= nextNumber)) {
                        isSafe = false;
                        break;
                    }
                }
                if (isSafe) {
                    safeReports.add(line);
                }
            }
            System.out.println(safeReports.size());
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
