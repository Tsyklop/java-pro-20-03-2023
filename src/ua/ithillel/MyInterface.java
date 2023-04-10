package ua.ithillel;

public interface MyInterface {

    String TEST = "";

    void test(int a);

    default void testDef() {
        System.out.println("MyInterface");
    }

    /*String getName();

    static void testStatic() {

    }

    default void testDefault() {

        String name = getName();

    }*/

}
