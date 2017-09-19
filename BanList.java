package com.lameguard;

import com.lameguard.utils.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class BanList {
    private static final BanList _instance = new BanList();
    private final File BANLIST_FILE = new File(Config.LAMEGUARD_BANLIST_FILE);
    private List<byte[]> bannedHWIDs = new ArrayList();
    private final AtomicLong lastModified = new AtomicLong(this.BANLIST_FILE.lastModified());

    public static final BanList getInstance() {
        return _instance;
    }

    private BanList() {
        load();
    }

    private void load() {
    }

    public void reload() {
        long modified = this.BANLIST_FILE.lastModified();
        if (this.lastModified.getAndSet(modified) != modified) {
            load();
        }
    }

    public void addHWID(byte[] hwid, String ip, String account, String comment) {
        if (hwid != null && hwid.length == 16) {
            synchronized (this) {
                if (this.bannedHWIDs.add(hwid)) {
                    addBan(Utils.asHex(hwid), ip, account, comment);
                }
            }
        }
    }

    public void addHWID(String hwid, String ip, String account, String comment) {
        if (hwid != null && hwid.length() == 32) {
            synchronized (this) {
                if (this.bannedHWIDs.add(Utils.asByteArray(hwid))) {
                    addBan(hwid, ip, account, comment);
                }
            }
        }
    }

    public boolean isHWIDBanned(String hwid) {
        if (hwid == null || hwid.length() != 32) {
            return false;
        }
        return isHWIDBanned(Utils.asByteArray(hwid));
    }

    public boolean isHWIDBanned(byte[] hwid) {
        return false;
    }

    private void addBan(String hwid, String ip, String account, String reason) {
    }
}
