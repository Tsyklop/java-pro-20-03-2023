package ua.hillel.classwork.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

//        Class.forName("org.postgresql.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dvdrental", "postgres", "1")) {

            /*try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM film;")) {

                try (ResultSet rs = ps.executeQuery()) {

                    ResultSetMetaData metaData = rs.getMetaData();

                    for (int i = 0; i < metaData.getColumnCount(); i++) {
                        System.out.print(metaData.getColumnName(i + 1) + "|");
                    }

                    System.out.println();

                    while (rs.next()) {

                        *//*IntStream.range(0, metaData.getColumnCount() + 1)
                                .boxed()
                                .map(column -> {
                                    try {
                                        return rs.getString(metaData.getColumnName(column + 1));
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                                .collect(Collectors.joining("|"))*//*

                        for (int i = 0; i < metaData.getColumnCount(); i++) {
                            System.out.print(rs.getString(metaData.getColumnName(i + 1)) + "|");
                        }

                        System.out.println();

                    }

                }

            }*/

            // sql injection example. USE ? as parameter
            String search = "film_id > 0; UPDATE film SET title='sql injection 5' WHERE film_id = 3; SELECT 1 FROM film WHERE 'any'";

            // film_id > 0; UPDATE film SET title='sql injection' WHERE film_id = 1; SELECT 1 FROM film WHERE 'any'
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement("SELECT film_id, title FROM film WHERE " + search + " = ANY(special_features);", PreparedStatement.RETURN_GENERATED_KEYS)) {

                //ps.setString(1, "Trailers");

                /*ps.executeUpdate();

                ps.getGeneratedKeys()*/

                try (ResultSet rs = ps.executeQuery()) {

                    ResultSetMetaData metaData = rs.getMetaData();

                    for (int i = 0; i < metaData.getColumnCount(); i++) {
                        System.out.print(metaData.getColumnName(i + 1) + "|");
                    }

                    System.out.println();

                    while (rs.next()) {

                        /*IntStream.range(0, metaData.getColumnCount() + 1)
                                .boxed()
                                .map(column -> {
                                    try {
                                        return rs.getString(metaData.getColumnName(column + 1));
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                                .collect(Collectors.joining("|"))*/

                        for (int i = 0; i < metaData.getColumnCount(); i++) {
                            System.out.print(rs.getString(metaData.getColumnName(i + 1)) + "|");
                        }

                        System.out.println();

                    }

                }

                connection.commit();

            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
            }

            connection.setAutoCommit(true);

            try (PreparedStatement ps = connection.prepareStatement("SELECT film_id, title FROM film WHERE ? = ANY(special_features);", PreparedStatement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, "Trailers");

                try (ResultSet rs = ps.executeQuery()) {

                    ResultSetMetaData metaData = rs.getMetaData();

                    while (rs.next()) {

                        Film film = new Film();

                        film.filmId = rs.getInt("film_id");

                        //films.add(film);

                    }

                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static class Film {

        //@Column("film_id")
        private int filmId;

        private String title;

        private String description;

        private Date releaseYear;

        private int languageId;

    }

    private static class FilmExtended extends Film {

        private String languageName;

    }

}
