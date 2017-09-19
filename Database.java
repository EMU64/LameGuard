package com.lameguard;

public class Database {
    private static final Database _instance = new Database();

    public static final Database getInstance() {
        return _instance;
    }
}
