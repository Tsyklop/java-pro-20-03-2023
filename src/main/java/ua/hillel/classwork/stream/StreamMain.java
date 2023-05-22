package ua.hillel.classwork.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamMain {

    private static final Random random = new Random();

    private static final String alphabet = "qwertyuiplkjhgfdsazxcvbnmQWERTYUIPLKJHGFDSAZXCVBNM123456789";

    public static void main(String[] args) {

        List<String> randomStrings = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            randomStrings.add(randomString());
        }

        System.out.println("randomStrings:");
        System.out.println(randomStrings);

        List<String> filteredStrings = new ArrayList<>();

        for (String str : randomStrings) {
            if (str.contains("a")) {
                filteredStrings.add(str);
            }
        }

        System.out.println("filteredStrings(foreach):");
        System.out.println(filteredStrings);

        Map<String, Integer> strLengthByStr = new HashMap<>();

        for (String filteredString : filteredStrings) {
            strLengthByStr.put(filteredString, filteredString.length());
        }

        System.out.println("strLengthByStr(foreach):");
        System.out.println(strLengthByStr);

        Map<String, Integer> strLengthByStrWithStreamApi = randomStrings.stream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String randomString) {
                        return randomString.contains("a");
                    }
                })
                .collect(
                        Collectors.toMap(
                                new Function<String, String>() {
                                    @Override
                                    public String apply(String value) {
                                        return value;
                                    }
                                },
                                new Function<String, Integer>() {
                                    @Override
                                    public Integer apply(String value) {
                                        return value.length();
                                    }
                                }
                        )
                );

        System.out.println("strLengthByStrWithStreamApi:");
        System.out.println(strLengthByStrWithStreamApi);

        Map<String, Integer> strLengthByStrWithStreamApiShort = randomStrings.stream()
                .filter(randomString -> randomString.contains("a"))
                .collect(Collectors.toMap(value -> value, value -> value.length()));

        System.out.println("strLengthByStrWithStreamApiShort:");
        System.out.println(strLengthByStrWithStreamApiShort);

        Map<Integer, List<String>> stringsByLength = randomStrings.stream()
                .collect(Collectors.groupingBy(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String value) {
                        return value.length();
                    }
                }));

        System.out.println("stringsByLength:");
        System.out.println(stringsByLength);

    }

    private static String randomString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < random.nextInt(30); i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return sb.toString();

    }

}
