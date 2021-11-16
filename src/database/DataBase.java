package database;

import java.util.*;
import java.util.stream.Collectors;

public class DataBase {
    public static Map<String, Object> db = new LinkedHashMap<>();

    public static void addString(String key, String value) {
        db.put(key, value);
    }

    public static void addList(String key, String value) {
        List<String> listValue = new ArrayList<>();
        listValue.add(value);
        db.put(key, listValue);
    }

    public static void updateList(String key, String value) {
        List<String> listValue = (List<String>) db.get(key);
        listValue.add(value);
        db.put(key, listValue);
    }

    public static void addMap(String key, String innerMapKey, String innerMapValue) {
        Map<String, String> mapValue = new HashMap<>();
        mapValue.put(innerMapKey, innerMapValue);
        db.put(key, mapValue);
    }

    public static void updateMap(String key, String innerMapKey, String innerMapValue) {
        Map<String, String> mapValue = (Map<String, String>) db.get(key);
        mapValue.put(innerMapKey, innerMapValue);
        db.put(key, mapValue);
    }

    public static void addSet(String key, String value) {
        Set<String> setValue = new HashSet<>();
        setValue.add(value);
        db.put(key, setValue);
    }

    public static void updateSet(String key, String value) {
        Set<String> setValue = (Set<String>) db.get(key);
        setValue.add(value);
        db.put(key, setValue);
    }

    public static String getDB() {
        return db.keySet().stream()
                .map(key -> key + " = " + db.get(key))
                .collect(Collectors.joining("\n"));
    }

    public static String showKey(String key) {
        if (db.containsKey(key)) {
            return db.get(key).toString();
        }
        return "Nothing found\n";
    }
}
