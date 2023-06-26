package ua.hillel.classwork.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class StringToSetConverterTest {

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    void beforeEach() {



    }

    @AfterEach
    void afterEach() {



    }

    @Test
    void should_return_correct_string_with_numbers() {

        List<Integer> integers = new ArrayList<>();//Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        integers.add(0);

        String result = StringToSetConverter.toString(integers);

        Assertions.assertEquals("[1][2][3][4][5][6][7][8][9][0]", result);

        Assertions.assertEquals("", StringToSetConverter.toString(List.of()));
        Assertions.assertEquals("[1]", StringToSetConverter.toString(List.of(1)));

        List<Integer> integers2 = new ArrayList<>();
        integers2.add(1);
        integers2.add(null);
        integers2.add(3);

        Assertions.assertEquals("[1][3]", StringToSetConverter.toString(integers2));

    }

    @Test
    void should_return_or_throw_exception_with_incorrect_numbers() {
        Assertions.assertNull(StringToSetConverter.toString(null));
    }

    @Test
    void should_return_correct_list_with_given_string() {

        List<Integer> numbers = StringToSetConverter.toSet("[1][2][3][4][5][6][7][8][9][0]");

        Assertions.assertTrue(numbers.contains(1));
        Assertions.assertTrue(numbers.contains(2));
        Assertions.assertTrue(numbers.contains(3));
        Assertions.assertTrue(numbers.contains(4));
        Assertions.assertTrue(numbers.contains(5));
        Assertions.assertTrue(numbers.contains(6));
        Assertions.assertTrue(numbers.contains(7));
        Assertions.assertTrue(numbers.contains(8));
        Assertions.assertTrue(numbers.contains(9));
        Assertions.assertTrue(numbers.contains(0));

        /*for (int i = 0; i < 10; i++) {
            Assertions.assertTrue(numbers.contains(i), i + " do not contains");
        }*/

        Assertions.assertNull(StringToSetConverter.toSet(""));
        Assertions.assertNull(StringToSetConverter.toSet(null));
        Assertions.assertNull(StringToSetConverter.toSet("null"));

        Assertions.assertThrows(NumberFormatException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                StringToSetConverter.toSet("[1][2a]");
            }
        });

    }

    @AfterAll
    static void afterAll() {

    }

}