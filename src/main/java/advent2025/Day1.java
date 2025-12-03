package advent2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day1 {

    static int currentValue;
    static int crossedZero;
    static int numberOfZeroes;

    public static void main(String[] args) throws IOException {
        Path path = args.length > 0 ? Paths.get(args[0]) : Paths.get("src/main/resources/2025/day1input.txt");
        handleNumbers(path);
        System.out.println("Pointed at zero " + numberOfZeroes + " times");
        System.out.println("Crossed zero "+ crossedZero + " times");
    }

    private static void handleNumbers(Path path) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            currentValue = 50;
            while ((line = br.readLine()) != null) {

                char direction = line.charAt(0);
                String numberString = line.substring(1);

                int value;
                value = Integer.parseInt(numberString);
                int previousValue = currentValue;
                int newDialNumber;

                switch (direction) {
                    case 'L':
                        currentValue = ((currentValue - value) % 100 + 100) % 100;
                        System.out.println("moved " + value + " places Left (L) from " + previousValue + " to " + currentValue);
                        for (int i = 0; i < value; i++) {
                            newDialNumber = ((previousValue - i) % 100 + 100) % 100;
                            if (newDialNumber == 0) {
                                System.out.println("Crossed zero");
                                crossedZero++;
                            }
                        }
                        break;
                    case 'R':
                        currentValue = (currentValue + value) % 100;
                        System.out.println("moved " + value + " places Right (R) from " + previousValue + " to " + currentValue);
                        for (int i = 0; i < value; i++) {
                            newDialNumber = (previousValue + i) % 100;
                            if (newDialNumber == 0) {
                                System.out.println("Crossed zero");
                                crossedZero++;
                            }
                        }
                        break;
                }
                if (currentValue == 0) {
                    numberOfZeroes++;
                }
            }
        }
    }
}