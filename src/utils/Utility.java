package utils;

import database.Commands;
import database.DataBase;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Utility {
    public static boolean isCommand(String commandName) {
        return commandName.equals(Commands.ADD_LIST.value) || commandName.equals(Commands.ADD_MAP.value) ||
                commandName.equals(Commands.ADD_SET.value) || commandName.equals(Commands.ADD_STRING.value) ||
                commandName.equals(Commands.SHOW_DB.value) || commandName.equals(Commands.DELETE_STRING.value) ||
                commandName.equals(Commands.DELETE_LIST.value) || commandName.equals(Commands.DELETE_MAP.value) ||
                commandName.equals(Commands.DELETE_SET.value);
    }

    public static boolean isShowCommand(String commandName) {
        return commandName.equals(Commands.SHOW_STRING.value) || commandName.equals(Commands.SHOW_LIST.value) ||
                commandName.equals(Commands.SHOW_MAP.value) || commandName.equals(Commands.SHOW_SET.value);
    }

    public static String toEnumValue(String commandName) {
        for (int i = 1; i < commandName.length(); i++) {
            if (Character.isUpperCase(commandName.charAt(i)) && Character.isLowerCase(commandName.charAt(i - 1))) {
                commandName = commandName.substring(0, i).concat("_").concat(commandName.substring(i));
                ++i;
            }
        }
        return commandName.toUpperCase();
    }

    public static boolean isString(String key) {
        return !DataBase.db.containsKey(key) || DataBase.db.get(key) instanceof String;
    }

    public static boolean isList(String key) {
        return !DataBase.db.containsKey(key) || DataBase.db.get(key) instanceof List;
    }

    public static boolean isMap(String key) {
        return !DataBase.db.containsKey(key) || DataBase.db.get(key) instanceof Map;
    }

    public static boolean isSet(String key) {
        return !DataBase.db.containsKey(key) || DataBase.db.get(key) instanceof Set;
    }

    public static boolean isTreeMap(String key) {
        return !DataBase.db.containsKey(key) || DataBase.db.get(key) instanceof TreeMap;
    }
}
