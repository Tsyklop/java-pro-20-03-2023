package ua.hillel.classwork.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MapMain {

    public static void main(String[] args) {

        //new Hashtable<>()
        Map<String, Integer> map = new HashMap<>();

        map.put("srt", 10);

        Map<RoleType, List<User>> usersByRole = new HashMap<>();

        usersByRole.put(RoleType.USER, List.of(new User(2), new User(5)));
        usersByRole.put(RoleType.ADMIN, List.of(new User(3), new User(4)));

        usersByRole.get(RoleType.ADMIN);
        usersByRole.getOrDefault(RoleType.ADMIN, List.of());

        usersByRole.keySet();
        usersByRole.values();

        usersByRole.isEmpty();

        System.out.println(usersByRole.get(RoleType.USER));
        //usersByRole.putAll(Map.of(RoleType.USER, List.of()));
        //System.out.println(usersByRole.get(RoleType.USER));

        System.out.println(usersByRole.get(RoleType.USER));
        usersByRole.putIfAbsent(RoleType.ADMIN, List.of());
        System.out.println(usersByRole.get(RoleType.USER));

        usersByRole.compute(RoleType.USER, new BiFunction<RoleType, List<User>, List<User>>() {
            @Override
            public List<User> apply(RoleType roleType, List<User> users) {
                return users == null ? new ArrayList<>() : users;
            }
        });

        usersByRole.computeIfAbsent(RoleType.ADMIN, new Function<RoleType, List<User>>() {
            @Override
            public List<User> apply(RoleType roleType) {
                return null;
            }
        });

        if (!usersByRole.containsKey(RoleType.ADMIN)) {
            usersByRole.put(RoleType.ADMIN, new ArrayList<>());
        }

        usersByRole.merge(RoleType.USER, List.of(new User(777)), new BiFunction<List<User>, List<User>, List<User>>() {
            @Override
            public List<User> apply(List<User> users, List<User> users2) {
                List<User> list = new ArrayList<>(users);
                list.addAll(users2);
                return list;
            }
        });

        // without entries
        for (RoleType roleType : usersByRole.keySet()) {
            usersByRole.get(roleType);
        }

        Set<Map.Entry<RoleType, List<User>>> entries = usersByRole.entrySet();

        for (Map.Entry<RoleType, List<User>> entry : entries) {
            entry.getKey();
            entry.getValue();
        }

        /*

        1. get key hashcode
        2. get bucket index ((n - 1) & hash) where n = buckets count
        3. if bucket element is null - create new node and put in bucket.
        3.1 if bucket already have node - iterate on all nodes and try find similar node by equals (by key)
        3.2 if similar node doesn't exists - create new node

         */

        Map<User, List<RoleType>> rolesByUser = new HashMap<>();

        User user = new User(1);
        user.setName("test");

        rolesByUser.put(user, List.of(RoleType.USER, RoleType.ADMIN));

        System.out.println(rolesByUser.get(user));

        user.setName("test1");

        System.out.println(rolesByUser.get(user));

    }

    public static enum RoleType {
        USER,
        ADMIN;
    }

    public static class User {

        private final int id;

        private String name;

        private RoleType role;

        public User(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public RoleType getRole() {
            return role;
        }

        public void setRole(RoleType role) {
            this.role = role;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

}
