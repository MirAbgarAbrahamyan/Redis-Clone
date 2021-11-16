package helper;

import database.Commands;
import database.DataBase;
import utils.Utility;

import static database.Commands.valueOf;

public class DataBaseAdder {
    public static String add(String commandName, String key, String value) {
        if (!Utility.isCommand(commandName)) {
            return "No such command\n";
        }
        Commands command = valueOf(Utility.toEnumValue(commandName));
        switch (command) {
            case ADD_STRING:
                if (Utility.isString(key)) {
                    if (DataBase.db.containsKey(key)) {
                        DataBase.addString(key, value);
                        return "Updated an existing key-value pair\n";
                    } else {
                        DataBase.addString(key, value);
                        return "Updated a new key-value pair\n";
                    }
                }
                return "Cannot change the structure\n";
            case ADD_LIST:
                if (Utility.isList(key)) {
                    if (DataBase.db.containsKey(key)) {
                        DataBase.updateList(key, value);
                        return "Updated an existing key-value pair for list\n";
                    } else {
                        DataBase.addList(key, value);
                        return "Updated a new key-value pair for list\n";
                    }
                }
                return "Cannot change the structure\n";
            case ADD_SET:
                if (Utility.isSet(key)) {
                    if (DataBase.db.containsKey(key)) {
                        DataBase.updateSet(key, value);
                        return "Updated an existing key-value pair for set\n";
                    } else {
                        DataBase.addSet(key, value);
                        return "Updated a new key-value pair for set\n";
                    }
                }
                return "Cannot change the structure\n";
            default:
                break;
        }
        return null;
    }
}
