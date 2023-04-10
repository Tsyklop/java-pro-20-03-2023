package ua.ithillel;

public interface SecondInterface {

    void test(int a);

    default void testDef() {
        System.out.println("SecondInterface");
    }

}
