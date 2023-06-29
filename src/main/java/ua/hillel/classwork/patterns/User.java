package ua.hillel.classwork.patterns;

import java.time.Instant;

//@Builder
public class User {

    private int id;

    private String lastName;
    private String firstName;
    private String login;
    private String password;
    private String email;
    private String phone;
    private String address;

    private Instant birthday;

    public User() {
    }

    public User(int id, String lastName, String firstName, String login, String password, String email, String phone, String address, Instant birthday) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {

        private int id;

        private String lastName;

        private String firstName;

        private String login;

        private String password;

        private String email;

        private String phone;

        private String address;

        private Instant birthday;

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder birthday(Instant birthday) {
            this.birthday = birthday;
            return this;
        }

        public User build() {
            return new User(
                    id,
                    lastName,
                    firstName,
                    login,
                    password,
                    email,
                    phone,
                    address,
                    birthday
            );
        }

    }

}
