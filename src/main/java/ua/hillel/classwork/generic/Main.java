package ua.hillel.classwork.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*Item[] items = new Item[10];

        Item[] newItems = new Item[items.length + 10];

        System.arraycopy(items, 0, newItems, 0, items.length);

        items = newItems;

        findByName(items, "example");*/

        Box<Box<String>> stringsBoxesBox = new Box<>();

        stringsBoxesBox.add(new Box<>());

        Box<String> stringsBox = new Box<>();

        stringsBox.add("str");
        //stringsBox.add(134); // ERROR

        Box<Item> itemBox = new Box<>();

        itemBox.add(new Item());
        //itemBox.add("magic"); // ERROR
        //itemBox.add(1000); // ERROR

        //itemBox.contains()

        Box<Object> box = new Box<>();

        box.add("");
        box.add(1);

    }

    public static class Item {

        private String name;
        private double price;
        private String description;

    }

    public static class Box<T> {

        protected int size;

        protected Object[] objects;

        public Box() {
            this(10);
        }

        public Box(int count) {
            this.objects = new Object[count];
        }

        public int size() {
            return size;
        }

        public T get(int index) {
            if (index < 0 || index > (objects.length - 1)) {
                throw new IndexOutOfBoundsException();
            }
            return (T) objects[index];
        }

        public boolean add(T toAdd) {

            if (size > objects.length / 2) {
                expandArray();
            }

            for (int i = 0; i < objects.length; i++) {
                if (objects[i] == null) {
                    objects[i] = toAdd;
                    size++;
                    return true;
                }
            }

            return false;

        }

        public boolean contains(T obj) {
            for (Object object : objects) {
                if (object.equals(obj)) {
                    return true;
                }
            }
            return false;
        }

        public void remove(Object toRemove) {
            for (int i = 0; i < objects.length; i++) {
                if (objects[i].equals(toRemove)) {
                    objects[i] = null;
                    size--;
                }
            }
        }

        protected void expandArray() {
            Object[] newSrc = new Object[objects.length * 2];
            System.arraycopy(objects, 0, newSrc, 0, objects.length);
            objects = newSrc;
        }

    }

}
