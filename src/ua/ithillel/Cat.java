package ua.ithillel;

public class Cat extends Animal {

    protected String type;

    public Cat(String type, String name) {
        super(type, name);
        this.type = "ua.ithillel.Cat-" + type;
    }

    public void method() {

    }

    public void method(int a) {

    }

    @Override
    public void test(int a) {

    }

    @Override
    public String getType() {
        return this.name;
    }

    @Override
    public String toString() {
        return "ua.ithillel.Cat{" +
                "type='" + type + '\'' +
                "} " + super.toString();
    }

    public String toStringCustom() {
        return "...";
    }

}


