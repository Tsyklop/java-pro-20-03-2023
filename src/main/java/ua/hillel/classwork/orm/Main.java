package ua.hillel.classwork.orm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {

    private static final Map<Class<?>, Map<String, TypeFieldData>> CACHED_TYPES = new HashMap<>();

    public static void main(String[] args) throws SQLException {

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dvdrental", "postgres", "1")) {
            findAll(connection, Film.class, "SELECT * FROM \"film\";").forEach(film -> {
                System.out.println(film);
            });
        }

    }

    private static <T> List<T> findAll(Connection connection, Class<T> type, String sql, Object... params) throws SQLException {

        List<T> result = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            if (params != null) {
                for (int i = 0; i < params.length; i++) {

                    Object param = params[i];

                    Class<?> paramType = param.getClass();

                    final int parameterIndex = i + 1;

                    if (paramType == String.class) {
                        ps.setString(parameterIndex, (String) param);
                    } else if (paramType == Byte.TYPE) {
                        ps.setByte(parameterIndex, (Byte) param);
                    } else if (paramType == Short.TYPE) {
                        ps.setShort(parameterIndex, (Short) param);
                    } else if (paramType == Integer.TYPE) {
                        ps.setInt(parameterIndex, (Integer) param);
                    } else if (paramType == Long.TYPE) {
                        ps.setLong(parameterIndex, (Long) param);
                    } else if (paramType == Float.TYPE) {
                        ps.setFloat(parameterIndex, (Float) param);
                    } else if (paramType == Double.TYPE) {
                        ps.setDouble(parameterIndex, (Double) param);
                    } else if (paramType == Boolean.TYPE) {
                        ps.setBoolean(parameterIndex, (Boolean) param);
                    } else {
                        ps.setObject(parameterIndex, param);
                    }

                }
            }

            try (ResultSet rs = ps.executeQuery()) {

                ResultSetMetaData metaData = rs.getMetaData();

                while (rs.next()) {

                    try {

                        T obj = type.getDeclaredConstructor().newInstance();

                        Field[] fields = type.getDeclaredFields();

                        Map<String, TypeFieldData> cachedTypeFieldDataByName = CACHED_TYPES.getOrDefault(type, new HashMap<>());

                        for (Field field : fields) {

                            TypeFieldData typeFieldData = cachedTypeFieldDataByName.getOrDefault(field.getName(), new TypeFieldData());

                            if (typeFieldData.isSkip() || field.getType().isArray() || Modifier.isStatic(field.getModifiers())) {
                                typeFieldData.setSkip(true);
                                continue;
                            }

                            String fieldName = field.getName();

                            String columnName = Optional.ofNullable(field.getAnnotation(Column.class))
                                    .map(column -> column.name())
                                    .filter(name -> name != null && !name.isEmpty())
                                    .orElse(fieldName);

                            /*Column column = field.getAnnotation(Column.class);

                            if (column != null && column.name() != null && !column.name().isEmpty()) {
                                columnName = column.name();
                            } else {
                                columnName = fieldName;
                            }*/

                            Method fieldSetter = null;

                            try {
                                fieldSetter = type.getDeclaredMethod(
                                        "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1),
                                        field.getType()
                                );
                            } catch (Exception ignored) {}

                            try {

                                if (fieldSetter != null && Modifier.isPublic(fieldSetter.getModifiers())) {
                                    fieldSetter.invoke(obj, getFieldValueFromResultSet(field.getType(), columnName, rs));
                                } else {

                                    field.setAccessible(true);

                                    try {
                                        field.set(obj, getFieldValueFromResultSet(field.getType(), columnName, rs));
                                    } finally {
                                        field.setAccessible(false);
                                    }

                                }

                            } catch (Exception e) {
                                throw new RuntimeException("Cannot set value for field " + fieldName, e);
                            }

                            cachedTypeFieldDataByName.put(fieldName, typeFieldData);

                        }

                        CACHED_TYPES.put(type, cachedTypeFieldDataByName);

                        result.add(obj);

                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException("Default constructor not found", e);
                    } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                        throw new RuntimeException("Error creating object", e);
                    }

                }

            }

        }

        return result;

    }

    private static Object getFieldValueFromResultSet(Class<?> fieldType, String fieldName, ResultSet rs) throws SQLException {
        if (fieldType == String.class) {
            return rs.getString(fieldName);
        } else if (fieldType == Byte.TYPE) {
            return rs.getByte(fieldName);
        } else if (fieldType == Short.TYPE) {
            return rs.getShort(fieldName);
        } else if (fieldType == Integer.TYPE) {
            return rs.getInt(fieldName);
        } else if (fieldType == Long.TYPE) {
            return rs.getLong(fieldName);
        } else if (fieldType == Float.TYPE) {
            return rs.getFloat(fieldName);
        } else if (fieldType == Double.TYPE) {
            return rs.getDouble(fieldName);
        } else if (fieldType == Boolean.TYPE) {
            return rs.getBoolean(fieldName);
        } else if (fieldType == Date.class) {
            return rs.getDate(fieldName);
        } else {
            return rs.getObject(fieldName);
        }

        //rs.findColumn(fieldName)

    }

    private static class TypeFieldData {

        private boolean skip;

        private Method setter;

        private String columnName;

        public TypeFieldData() {
        }

        public TypeFieldData(boolean skip, Method setter, String columnName) {
            this.skip = skip;
            this.setter = setter;
            this.columnName = columnName;
        }

        public boolean isSkip() {
            return skip;
        }

        public void setSkip(boolean skip) {
            this.skip = skip;
        }

        public Method getSetter() {
            return setter;
        }

        public void setSetter(Method setter) {
            this.setter = setter;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }
    }

    private static class Film {

        @Column(name = "film_id")
        private int filmId;

        private String title;

        private String description;

        @Column(name = "release_year")
        private int releaseYear;

        @Column(name = "language_id")
        private int languageId;

        @Column(name = "rental_duration")
        private int rentalDuration;

        @Column(name = "rental_rate")
        private int rentalRate;

        private int length;

        @Column(name = "replacement_cost")
        private int replacementCost;

        private String rating;

        @Column(name = "last_update")
        private Date lastUpdate;

        @Column(name = "special_features")
        private String specialFeatures;

        @Override
        public String toString() {
            return "Film{" +
                    "film_id=" + filmId +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", release_year=" + releaseYear +
                    ", language_id=" + languageId +
                    ", rental_duration=" + rentalDuration +
                    ", rental_rate=" + rentalRate +
                    ", length=" + length +
                    ", replacement_cost=" + replacementCost +
                    ", rating='" + rating + '\'' +
                    ", last_update=" + lastUpdate +
                    ", special_features='" + specialFeatures + '\'' +
                    '}';
        }

        public void setTitle(String title) {
            System.out.println("setTitle");
            this.title = title;
        }

    }

}
