package com.lameguard;

import java.io.File;
import java.util.concurrent.atomic.AtomicLong;

public class Config {
    private static final File CONFIG_FILE = new File(LAMEGUARD_CONFIG_FILE);
    public static boolean DEBUG = false;
    public static final String LAMEGUARD_BANLIST_FILE = "lameguard/banned_hwid.txt";
    public static final String LAMEGUARD_CONFIG_FILE = "lameguard/lameguard.properties";
    public static final String LAMEGUARD_LOG_FILE = "lameguard/lameguard.log";
    private static final AtomicLong lastModified = new AtomicLong(CONFIG_FILE.lastModified());

    static {
        loadInformation();
        load();
    }

    public static final void loadInformation() {
    }

    public static final void load() {
    }

    public static void reload() {
        long modified = CONFIG_FILE.lastModified();
        if (lastModified.getAndSet(modified) != modified) {
            load();
        }
    }
}
