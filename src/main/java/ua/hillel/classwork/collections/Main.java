package ua.hillel.classwork.collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();
        //strings.toArray(new String[0]);

        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        strings.add("e");
        
        strings.retainAll(List.of("b", "c", "d"));

        for (String str : strings) {
            System.out.println(str);
        }

        Iterator<String> iterator = strings.iterator();

        while (iterator.hasNext()) {
            String value = iterator.next();
            System.out.println(value);
            //iterator.remove();
        }

        Set<String> set = new HashSet<>(strings);

    }

}
