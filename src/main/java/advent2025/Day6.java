package advent2025;

import java.nio.file.Path;

public class Day6 {
    public static void main() {
        Path path = Path.of("src/main/resources/2025/day6input.txt");
        String[] homeworkLines = Day5.readLines(path);
        assert homeworkLines != null;

        // Trim the lines before splitting to handle leading/trailing whitespace
        String[] line1 = homeworkLines[0].trim().split("\\s+");
        String[] line2 = homeworkLines[1].trim().split("\\s+");
        String[] line3 = homeworkLines[2].trim().split("\\s+");
        String[] line4 = homeworkLines[3].trim().split("\\s+");
        String[] multiplyOrAdd = homeworkLines[4].trim().split("\\s+");

        long total = 0;

        for (int i = 0; i < multiplyOrAdd.length; i++) {
            long rowResult;
            if (multiplyOrAdd[i].equals("*")) {
                // Include the fourth line in the multiplication
                rowResult = (long) Integer.parseInt(line1[i]) * Integer.parseInt(line2[i]) * Integer.parseInt(line3[i]) * Integer.parseInt(line4[i]);
            } else {
                // Include the fourth line in the addition
                rowResult = (long) Integer.parseInt(line1[i]) + Integer.parseInt(line2[i]) + Integer.parseInt(line3[i]) + Integer.parseInt(line4[i]);
            }
            total += rowResult;
        }
        System.out.println(total);
    }
}
