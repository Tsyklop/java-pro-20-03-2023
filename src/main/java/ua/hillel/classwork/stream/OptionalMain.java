package ua.hillel.classwork.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

public class OptionalMain {

    private static Random random = new Random();

    public static void main(String[] args) {

        ExampleInterface exampleInterface = () -> {

        };

        Optional<String> stringOptional = getRansomString();

        System.out.println(stringOptional);
        System.out.println(stringOptional.orElse("Plan B"));

        stringOptional.ifPresent(new Consumer<String>() {
            @Override
            public void accept(String value) {
                System.out.println("value is:");
                System.out.println(value);
            }
        });

        stringOptional.ifPresent(value -> {
            System.out.println("value is:");
            System.out.println(value);
        });

        stringOptional.ifPresent(new Consumer<String>() {
            @Override
            public void accept(String value) {
                System.out.println(value);
            }
        });
        stringOptional.ifPresent(value -> System.out.println(value)); // lambda
        stringOptional.ifPresent(System.out::println); // method reference

        stringOptional.ifPresent(value -> {

            String otherValue = OptionalMain.methodForMethodReference(value);

        }); // lambda
        stringOptional.ifPresent(OptionalMain::methodForMethodReference); // method reference

        stringOptional.map(new Function<String, Integer>() { // usual
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        });

        stringOptional.map(s -> s.length()); // lambda
        stringOptional.map(String::length); // method reference

        /*stringOptional.orElseThrow(new Supplier<RuntimeException>() {
            @Override
            public RuntimeException get() {
                return new IllegalArgumentException("test");
            }
        });*/

        /*String nullableString = getRansomStringNullable();

        if (nullableString == null) {
            return;
        }

        Integer strLength = nullableString.length();*/

        String weekdayName = "sdjklghdfjighfdlk";

        Weekday weekday;
        try {
            weekday = Weekday.valueOf(weekdayName);
        } catch (IllegalArgumentException e) {
            weekday = Weekday.FRIDAY;
        }

        weekday = Weekday.valueOfOptional(weekdayName).orElse(Weekday.FRIDAY);


    }

    public static Optional<String> getRansomString() {
        if (random.nextInt(10) > 5) {
            return Optional.of("some string");
        }
        return Optional.empty();
    }

    /**
     *
     * @return string or null
     */
    public static String getRansomStringNullable() {
        if (random.nextInt(10) > 5) {
            return "some string";
        }
        return null;
    }

    public static String methodForMethodReference(String value) {
        return "examepl";
    }

    public static enum Weekday {
        MONDAY,
        THUSDAY,
        SUNDAY,
        FRIDAY;

        public static Optional<Weekday> valueOfOptional(String value) {
            try {
                return Optional.of(valueOf(value));
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        }

    }

    public static interface ExampleInterface {
        void doThing();
    }

}
