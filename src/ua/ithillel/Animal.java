package ua.ithillel;

import java.util.Arrays;

public abstract class Animal implements MyInterface, SecondInterface {

    private final String type;

    protected String name;

    private final char[] passport;

    public Animal(String type, String name) {
        this(type, name, null);
    }

    public Animal(String type, String name, char[] passport) {
        this.type = type;
        this.name = name;
        this.passport = passport;
    }

    public CharSequence getType() {
        return new String(type);
    }

    public char[] getPassport() {
        return Arrays.copyOf(this.passport, this.passport.length);
    }

    /*public void setType(String type) {

        if (type == null) {

        }

        this.type = type;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void test(int a) {

        MyInterface myInterface = new MyInterface() {
            @Override
            public void test(int a) {

            }
        };

        //MyInterface myInterface1 = a1 -> { };

    }

    @Override
    public void testDef() {
        SecondInterface.super.testDef();
    }

    @Override
    public String toString() {
        return "ua.ithillel.Animal{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
