package ua.hillel.classwork.junit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class StringToSetConverter {

    /**
     *
     * 1, 2, 3, 4, 5, 6, 7, 8, 9, 0
     *
     * [1][2][3][3][4][5][6][7][8][9][0]
     *
     * @param numbers
     * @return
     */
    public static String toString(List<Integer> numbers) {

        if (numbers == null) {
            return null;
        }

        /*StringBuilder sb = new StringBuilder();
        for (Integer number: numbers) {
            if (number == null) {
                continue;
            }
            sb.append("[").append(number).append("]");
        }
        return sb.toString();*/

        return numbers.stream()
                .filter(number -> number != null)
                .map(number -> "[" + number + "]")
                .collect(Collectors.joining());

    }

    /**
     *
     * [1][2][3][3][4][5][6][7][8][9][0]
     *
     * 1, 2, 3, 4, 5, 6, 7, 8, 9, 0
     *
     * @param value
     * @return
     */
    public static List<Integer> toSet(String value) {

        if (value == null || value.isEmpty() || "null".equals(value)) {
            return null;
        }

        String[] parts = value.split("]");

        List<Integer> numbers = new ArrayList<>();

        for (String part: parts) {
            part = part.replace("[", "");
            numbers.add(Integer.parseInt(part));
        }

        return numbers;

    }

}
