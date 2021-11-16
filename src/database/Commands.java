package database;

public enum Commands {
    ADD_STRING("addString"), ADD_LIST("addList"), ADD_MAP("addMap"),
    ADD_SET("addSet"), SHOW_DB("showDB"), DELETE_STRING("deleteString"),
    DELETE_LIST("deleteList"), DELETE_MAP("deleteMap"), DELETE_SET("deleteSet"),
    SHOW_STRING("showString"), SHOW_LIST("showList"), SHOW_MAP("showMap"),
    SHOW_SET("showSet");
    public String value;

    Commands(String value) {
        this.value = value;
    }
}
