package helper;

import database.Commands;
import database.DataBase;
import utils.Utility;

import static database.Commands.valueOf;

public class DataBaseDeleter {
    public static String delete(String commandName, String key) {
        if (!Utility.isCommand(commandName)) {
            return "No such command\n";
        }
        Commands command = valueOf(Utility.toEnumValue(commandName));
        switch (command) {
            case DELETE_STRING:
                if (Utility.isString(key)) {
                    if (DataBase.db.containsKey(key)) {
                        DataBase.db.remove(key);
                        return "String key-value pair has been deleted from database\n";
                    }
                }
                return "Nothing found to delete\n";
            case DELETE_LIST:
                if (Utility.isList(key)) {
                    if (DataBase.db.containsKey(key)) {
                        DataBase.db.remove(key);
                        return "List key-value pair has been deleted from database\n";
                    }
                }
                return "Nothing found to delete";
            case DELETE_MAP:
                if (Utility.isMap(key)) {
                    if (DataBase.db.containsKey(key)) {
                        DataBase.db.remove(key);
                        return "Map key-value pair has been deleted from database\n";
                    }
                }
                return "Nothing found to delete\n";
            case DELETE_SET:
                if (Utility.isSet(key)) {
                    if (DataBase.db.containsKey(key)) {
                        DataBase.db.remove(key);
                        return "Set key-value pair has been deleted from database\n";
                    }
                }
                return "Nothing found to delete\n";
            default:
                break;
        }
        return null;
    }
}
