package ua.hillel.classwork.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();

        int[] arr = new int[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }

        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));

        List<String> strings = new ArrayList<>();

        strings.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        strings.sort(Comparator.naturalOrder());

        List<User> users = new ArrayList<>();

        users.add(new User(1, "cest"));
        users.add(new User(1, "best"));
        users.add(new User(1, "aest"));
        users.add(new User(2, "best"));
        users.add(new User(2, "dest"));
        users.add(new User(2, "aest"));
        users.add(new User(2, "cest"));

        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.id - o1.id;
            }
        }.thenComparing(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.name.compareTo(o2.name);
            }
        }));

        System.out.println(users);

    }

    public static class User {
        private Integer id;
        private String name;

        public User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
