package ua.ithillel;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Strings {

    public static void main(String[] args) {

        System.out.println(new String("\uD83D\uDE20".getBytes(StandardCharsets.UTF_8)));

        // print all chars in utf-8
        /*for (int i = 0; i < 65000; i++) {
            System.out.println((char) i);
        }*/

        char c = 'f';

        String f = "f";

        //int a, b, h, d;

        String str = "abc"; // create string in string pool
        String str1 = "abc"; // get string reference from string pool
        String str2 = "abc"; // create string in heap

        int a = 10;
        int b = 10;
        System.out.println("a == b: " + (a == b));

        System.out.println("a + b: " + a + b);
        System.out.println(a + b + ": a + b");

        System.out.println("str == str1: " + (str == str1)); // true
        System.out.println("str == str2: " + (str == str2)); // false
        System.out.println("str1 == str2: " + (str1 == str2));

        System.out.println("str.equals(str1): " + (str.equals(str1)));
        System.out.println("str.equals(str2): " + (str.equals(str2)));
        System.out.println("str1.equals(str2): " + (str1.equals(str2)));

        System.out.println("\"ABC\".equalsIgnoreCase(str): " + ("ABC".equalsIgnoreCase(str)));

        System.out.println("str.length(): " + str.length());
        System.out.println("str.toUpperCase(): " + str.toUpperCase());
        System.out.println("str.toLowerCase(): " + str.toLowerCase());

        System.out.println("str.startsWith() 1: " + str.startsWith("ab"));
        System.out.println("str.startsWith() 2: " + str.startsWith("ab", 1));

        System.out.println("str.startsWith(): " + str.endsWith("bc"));
        System.out.println("str.startsWith(): " + str.endsWith("cb"));

        String newStr = str.concat("cb");

        System.out.println("str.concat(): " + newStr);

        System.out.println("str: " + str);
        System.out.println("str == str1: " + (str == newStr));
        System.out.println("str == str2: " + (str == newStr));
        System.out.println("str1 == str2: " + (str1 == newStr));

        String strForFormatting = "number %d";

        System.out.println("str.formatted(): " + strForFormatting.formatted(1));
        System.out.println("str.formatted(): " + strForFormatting.formatted(2));

        System.out.println("str.indent(): " + str.indent(10));
        System.out.println("str.repeat(): " + "=".repeat(10));

        System.out.println("str.replace(): " + str.replace('a', 'A'));
        System.out.println("str.replace(): " + str.replace("ab", "ba"));

        String qwerty = "   qwerty   ";

        System.out.println("qwerty: " + qwerty);
        System.out.println("str.trim(): " + qwerty.trim());
        System.out.println("str.strip(): " + qwerty.strip());
        System.out.println("str.stripIndent(): " + qwerty.stripIndent());

        System.out.println("str.substring(): " + qwerty.substring(3));
        System.out.println("str.substring(): " + qwerty.substring(3, 7));

        System.out.println("str.indexOf(): " + "   qwertyw   ".indexOf('w'));
        System.out.println("str.lastIndexOf(): " + "   qwertyw   ".lastIndexOf('w'));

        System.out.println("str.substring(): " + qwerty.substring(qwerty.indexOf('q'), qwerty.lastIndexOf('y') + 1));

        System.out.println("str.charAt(): " + qwerty.charAt(3));

        System.out.println("str.contains(): " + qwerty.contains("er"));
        System.out.println("str.contains(): " + qwerty.contains("erfsdfg"));

        System.out.println("str.isEmpty(): " + "".isEmpty());
        System.out.println("str.isEmpty(): " + "afghsd".isEmpty());

        System.out.println("str.isEmpty(): " + "    ".isEmpty());
        System.out.println("str.isBlank(): " + "    ".isBlank());

        String numbers = "10,2,3,4,5,6,7,8,9";

        for (int i = 0; i < numbers.length(); i++) {
            char nc = numbers.charAt(i);
            if (nc == ',') {
                continue;
            }
            System.out.println(
                    Integer.valueOf(
                            String.valueOf(nc)
                    )
            );
        }

        String[] numbersParts = numbers.split(",");

        for (int i = 0; i < numbersParts.length; i++) {
            System.out.println(Integer.parseInt(numbersParts[i]));
        }

        System.out.println("str.split(): " + Arrays.toString(numbersParts));

        StringBuilder sb = new StringBuilder("start string");

        sb.append(' ').append(1).append(' ').append(2).append(' ').insert(4, "sgjkskjrsa g");

        System.out.println(sb);

    }

}
