package ua.ithillel;

public class Main {

    public static void main(String[] args) {

        Cat cat = new Cat("Кот", "Тузик");

        cat.type = "H";

        //((SecondInterface) cat).test(1);
        ((SecondInterface) cat).testDef();

        String str = "T";
        String str1 = new String("T");

        System.out.println(cat.getType());
        System.out.println(cat.toStringCustom());

        //str.equals(str1)

        //System.gc();

        //cat = null;

        int a = 10;

        test1(a);
        test2(cat);

        //new Integer(10);

    }

    public static void test1(int g) {

    }

    public static void test2(Cat cat) {

    }

}