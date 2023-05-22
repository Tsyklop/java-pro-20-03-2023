package ua.hillel.classwork.stream;

import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {

    private static Random random = new Random();

    public static void main(String[] args) {

        Optional<String> stringOptional = getRansomString();

        System.out.println(stringOptional);
        System.out.println(stringOptional.orElse("Plan B"));

        stringOptional.ifPresent(new Consumer<String>() {
            @Override
            public void accept(String s) {

            }
        });

        stringOptional.orElseThrow(new Supplier<RuntimeException>() {
            @Override
            public RuntimeException get() {
                return new IllegalArgumentException("test");
            }
        });

    }

    /**
     *
     * @return string or null
     */
    public static Optional<String> getRansomString() {
        if (random.nextInt(10) > 5) {
            return Optional.of("some string");
        }
        return Optional.empty();
    }

}
