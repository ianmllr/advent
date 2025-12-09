package advent2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Day5 {
    public static void main() {
        Path freshRangesPath = Path.of(("src/main/resources/2025/day5input1.txt"));
        Path ingredientPath = Path.of(("src/main/resources/2025/day5input2.txt"));

        String[] freshRanges = readLines(freshRangesPath);
        String[] ingredientIDs = readLines(ingredientPath);

        assert freshRanges != null;

        // Set because no duplicate counting
        Set<Long> freshIngredients = getFreshIngredients(freshRanges, ingredientIDs);

        System.out.println(freshIngredients.size() + " ingredients are fresh.");
    }

    private static Set<Long> getFreshIngredients(String[] freshRanges, String[] ingredientIDs) {
        Set<Long> freshIngredients = new HashSet<>();

        for (String freshRange : freshRanges) {
            String[] rangeParts = freshRange.split("-");

            for (String id : ingredientIDs) {
                long startRange = Long.parseLong(rangeParts[0]);
                long endRange = Long.parseLong(rangeParts[1]);

                long ingredientID = Long.parseLong(id);
                if (ingredientID >= startRange && ingredientID <= endRange) {
                    freshIngredients.add(ingredientID);
                }
            }


        }
        return freshIngredients;
    }

    public static String[] readLines(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
