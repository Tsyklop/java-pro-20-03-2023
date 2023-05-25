package ua.hillel.classwork.stream;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StreamMain {

    private static final Random random = new Random();

    private static final String alphabet = "qwertyuiplkjhgfdsazxcvbnmQWERTYUIPLKJHGFDSAZXCVBNM123456789";

    public static void main(String[] args) {

        task();

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

        String str = "";

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

    private static void task() {

        String input = "<dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-validation</artifactId>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>com.fasterxml.jackson.core</groupId>\n" +
                "            <artifactId>jackson-annotations</artifactId>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.data</groupId>\n" +
                "            <artifactId>spring-data-commons</artifactId>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>org.checkerframework</groupId>\n" +
                "            <artifactId>checker-qual</artifactId>\n" +
                "            <version>3.28.0</version>\n" +
                "        </dependency>";

        List<String> values = new ArrayList<>();
        for (String str : input.split("\n")) {
            if (!str.contains("groupId")) {
                continue;
            }
            str = str.trim();
            values.add(str.substring(str.indexOf('>') + 1, str.lastIndexOf('<')));
        }

        System.out.println(values);

        List<String> groupValues = Arrays.stream(input.split("\n"))
                .filter(str -> str.contains("groupId"))
                .map(str -> str.trim())
                .map(str -> {
                    return str.substring(str.indexOf('>') + 1, str.lastIndexOf('<'));
                })
                .collect(Collectors.toList());

        System.out.println(groupValues);

        int symbolsCountSum = Arrays.stream(input.split("\n"))
                .map(str -> str.trim())
                .map(str -> str.length())
                .reduce(0, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
                        return Integer.sum(integer, integer2);
                    }
                });

        System.out.println(symbolsCountSum);

        List<Client> clients = new ArrayList<>();

        clients.add(
                new Client(1, "", "", List.of(
                        new Phone("12", PhoneType.MOBILE)
                ))
        );

        clients.add(
                new Client(2, "", "", List.of(
                        new Phone("12", PhoneType.MOBILE),
                        new Phone("666", PhoneType.STATIONARY)
                ))
        );

        clients.add(
                new Client(3, "", "", List.of(
                        new Phone("12", PhoneType.MOBILE)
                ))
        );

        clients.add(
                new Client(4, "", "", List.of(
                        new Phone("12", PhoneType.MOBILE),
                        new Phone("125", PhoneType.STATIONARY)
                ))
        );

        clients.add(
                new Client(5, "", "", List.of(
                        new Phone("12", PhoneType.MOBILE)
                ))
        );

        /*.filter(client -> {
                    for (Phone phone: client.getPhones()) {
                        if (phone.type == PhoneType.STATIONARY) {
                            return true;
                        }
                    }
                    return false;
                })*/
        Client result = clients.stream()
                .filter(client -> client.getPhones().stream().anyMatch(phone -> phone.type == PhoneType.STATIONARY))
                .min((o1, o2) -> o2.age - o1.age)
                .orElseThrow();

        System.out.println(result);

        File dir = new File("E:\\IDEA\\hillel\\java-20-03-23\\class-work");

        Optional.ofNullable(dir.listFiles())
                .filter(files -> files != null)
                .ifPresent(files -> {

                    System.out.println(
                            Arrays.stream(files)
                                    .filter(file -> file.getName().endsWith(".txt"))
                                    .collect(Collectors.toList())
                    );

                });

        new ArrayList<List<String>>()
                .stream()
                .flatMap(strings -> strings.stream())
                .collect(Collectors.toList());

    }

    public static class Client {

        private int age;

        private String inn;
        private String name;

        private List<Phone> phones;

        public Client(int age, String inn, String name, List<Phone> phones) {
            this.age = age;
            this.inn = inn;
            this.name = name;
            this.phones = phones;
        }

        public String getInn() {
            return inn;
        }

        public void setInn(String inn) {
            this.inn = inn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Phone> getPhones() {
            return phones;
        }

        public void setPhones(List<Phone> phones) {
            this.phones = phones;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Client{" +
                    "age=" + age +
                    ", inn='" + inn + '\'' +
                    ", name='" + name + '\'' +
                    ", phones=" + phones +
                    '}';
        }
    }

    public static class Phone {

        private String value;

        private PhoneType type;

        public Phone(String value, PhoneType type) {
            this.value = value;
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public PhoneType getType() {
            return type;
        }

        public void setType(PhoneType type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Phone{" +
                    "value='" + value + '\'' +
                    ", type=" + type +
                    '}';
        }
    }

    public static enum PhoneType {
        MOBILE, STATIONARY;
    }

}
