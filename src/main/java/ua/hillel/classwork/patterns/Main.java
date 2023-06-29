package ua.hillel.classwork.patterns;

public class Main {

    public static void main(String[] args) {

        User user1 = new User();
        user1.setEmail("...");

        //User user2 = new User(1, "");

        User user3 = User.builder()
                .id(1)
                .email("test@gmail.com")
                .login("test")
                .password("qwerty")
                .build();

    }

}
