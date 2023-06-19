package ua.hillel.classwork.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

//        Film.class
//        Class<Film> clazz;
//        new Film().getClass().

        System.out.println(Arrays.toString(Film.class.getDeclaredFields()));

        Field field = Film.class.getDeclaredField("filmId");

        //field.

        Film film = Film.class.getDeclaredConstructor().newInstance();

        System.out.println(film);

        field.setAccessible(true);
        field.set(film, 13);
        field.setAccessible(false);

        System.out.println(film);

    }

    public static void refMethod(Class<?> clazz) {



    }

    public static class Film {

        //@Column("film_id")
        private int filmId;

        private String title;

        private String description;

        private Date releaseYear;

        private int languageId;

        @Override
        public String toString() {
            return "Film{" +
                    "filmId=" + filmId +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", releaseYear=" + releaseYear +
                    ", languageId=" + languageId +
                    '}';
        }
    }

    public static class FilmExtended extends Film {

        private String languageName;

    }

}
